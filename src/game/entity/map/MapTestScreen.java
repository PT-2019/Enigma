package game.entity.map;

import api.entity.GameObject;
import api.entity.types.ContainersManager;
import api.entity.types.Living;
import api.entity.types.NeedContainer;
import api.entity.types.NeedContainerManager;
import api.enums.AnsiiColor;
import api.enums.EntitiesCategories;
import api.enums.Layer;
import api.enums.TmxProperties;
import api.hud.components.CustomWindow;
import api.utils.Bounds;
import api.utils.PrintColor;
import api.utils.SortEntitiesPropertiesById;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import editor.entity.actions.GameExit;
import editor.view.cases.CasePopUp;
import editor.view.cases.CaseView;
import editor.view.listeners.CaseListener;
import game.hud.Border;
import game.hud.CategoriesMenu;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import starter.EditorLauncher;

import javax.swing.JComponent;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static api.MapsNameUtils.HEIGHT_P;
import static api.MapsNameUtils.TILE_HEIGHT_P;
import static api.MapsNameUtils.TILE_WIDTH_P;
import static api.MapsNameUtils.WIDTH_P;

/**
 * Map de la libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.5
 * @since 2.0 5 décembre 2019
 */
public class MapTestScreen extends AbstractMap {

	/**
	 * Dimension d'un tile
	 */
	private final int tileWidth, tileHeight;
	/**
	 * Dessinateur de la map
	 */
	private final OrthogonalTiledMapRenderer map;
	/**
	 * Caméra de la map
	 */
	private final OrthographicCamera camera;

	/**
	 * Fenetre parent qui contient la map
	 */
	private final CustomWindow window;

	/**
	 * Bordure des cases de la map
	 */
	private final Border border;
	/**
	 * Dimension de la map
	 */
	private int mapWidth, mapHeight;
	/**
	 * Les limites de la map dans l'espace
	 *
	 * @since 3.0
	 */
	private Bounds mapBounds;

	/**
	 * Les entités de la maps
	 */
	private HashMap<Vector2, GameObject> added;

	private boolean showGrid;

	/**
	 * Indique si une sortie est présente
	 * @since 5.5
	 */
	private boolean hasExit;

	/**
	 * Crée une map depuis un fichier tmx
	 *
	 * @param path      fichier .tmx
	 * @param unitScale zoom
	 * @since 2.0
	 */
	public MapTestScreen(@NotNull final String path, float unitScale) {
		super(path, unitScale);
		//load the map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

		//save needed properties
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		int width = properties.get(WIDTH_P.value, Integer.class);
		int height = properties.get(HEIGHT_P.value, Integer.class);

		this.showGrid = false;

		//bordures
		this.border = new Border(width, height, this.tileHeight);

		//dimension de la map
		this.mapWidth = width * tileWidth;
		this.mapHeight = height * tileHeight;

		//cache le niveau de collision
		MapLayer collision = this.map.getMap().getLayers().get(Layer.COLLISION.name());
		if (collision != null)
			collision.setVisible(false);

		//setup camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.set(
				Gdx.graphics.getWidth() / 2f - this.mapWidth / 2f - CategoriesMenu.WIDTH,
				Gdx.graphics.getHeight() / 2f - this.mapHeight / 2f, 0);
		this.camera.update();

		this.window = EditorLauncher.getInstance().getWindow();

		init();
		createCell(this.window.getContentPane());

		this.added = new HashMap<>();

		//bounds
		this.setMapBounds();

		this.initEntities();
	}

	/**
	 * Re-instancie les entités
	 * @since 5.3
	 */
	private void initEntities() {
		ArrayList<MapProperties> entities = getProperty(TmxProperties.TMX_PROP_ENTITY);
		float x, y;
		int width, height;
		Integer id;
		String className;
		EntitySerializable e;
		for (MapProperties prop : entities) {
			//PROPRIETES
			width = Math.round(prop.get(TmxProperties.TMX_WIDTH, Float.class));
			height = Math.round(prop.get(TmxProperties.TMX_HEIGHT, Float.class));
			className = prop.get(TmxProperties.TMX_PROP_ENTITY_CLASS, String.class);
			x = prop.get(TmxProperties.TMX_X, Float.class);
			y = Float.parseFloat(prop.get(TmxProperties.TMX_Y, String.class));
			id = prop.get(TmxProperties.TMX_ID, Integer.class);
			if(id == null) id = 0;
			Vector2 start = new Vector2(x, y);
			if(start.x >= 0 && start.y >= 0) {
				//TILES
				HashMap<String, Array<Float>> tiles = new HashMap<>();
				for (Layer layer : Layer.values()) {
					String sizeS = prop.get(layer.toString(), String.class);
					if (sizeS == null || sizeS.length() == 0) continue;
					int size = Integer.parseInt(sizeS);
					Array<Float> tile = new Array<>();
					for (int i = 0; i < size; i++) {
						tile.add(0f);
					}
					tiles.put(layer.toString(), tile);
				}
				e = new EntitySerializable(width, height, className, tiles);
				GameObject object = EntityFactory.createEntity(e, id, start);

				Utility.printDebug("MapTestScreen#initEntities", object + " " + start);

				//ajout à la liste des entités de la map
				this.added.put(start, object);

				// on place les tiles
				this.setFromSave(object, start);
			} else {
				//entitée temporaire, pas sur la map
				e = new EntitySerializable(width, height, className, new HashMap<>());
				GameObject object = EntityFactory.createEntity(e, id, start);

				//ajout à la liste des entités de la map
				this.added.put(start, object);

				Utility.printDebug("(tmp) MapTestScreen#initEntities", object + " " + start);
			}
		}
	}

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 5.3
	 */
	private void setFromSave(GameObject entity, Vector2 start) {
		boolean noForce =  (entity instanceof api.entity.types.Container);

		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (entities == null) continue;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth() && index < entities.size; j++, index++) {
					MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
					if (c == null) continue;
					if(noForce && c.getEntity() instanceof NeedContainerManager) continue;
					if(c.getEntity() != null){
						//System.out.println("écrasé"+c.getEntity()+" par "+entity);
					}
					c.setEntity(entity);
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	/**
	 * Retourne une liste des propriétés contenant name
	 *
	 * @param name un name (tag name d'une property d'un .tmx)
	 * @return une liste des propriétés contenant name
	 *
	 * @since 5.0
	 */
	private ArrayList<MapProperties> getProperty(String name) {
		ArrayList<MapProperties> props = new ArrayList<>();
		for (MapLayer layer : this.map.getMap().getLayers()) {
			for (MapObject mapObject : layer.getObjects()) {
				//object contient entité ?
				if (name != null && name.equals(mapObject.getName())) {
					props.add(mapObject.getProperties());
				}
			}
		}
		return props;
	}

	/**
	 * Charge une entité sur la map a un position si elle est sur la map
	 *
	 * @param entity l'entité à charger
	 * @param pos    la position o&#249; charger
	 * @return true si l'entité a étée chargée
	 * @since 3.0
	 */
	public GameObject loadEntity(EntitySerializable entity, @Nullable Vector2 pos) {
		Vector2 start = null;
		//récupère la position sur la map depuis pos, s'il y a une pos
		if(pos != null) {//TODO: CALCUL ULTRA FOIREUX SI ZOOM CHANGE !!
			//calcules les 4 coins de la map
			Rectangle bounds = this.getMapSize();
			bounds.setPosition((Gdx.graphics.getWidth() / 2f - camera.position.x) * 1,
					(Gdx.graphics.getHeight() / 2f - camera.position.y) * 1);
			this.mapBounds = new Bounds(bounds);

			//Utility.printDebug("MapLibgdx - placement", mapBounds + " pos=" + pos + " " + mapBounds.contains(pos));

			//si pas dans la map
			if (!mapBounds.contains(pos)) return null;

        /*
            retire l'offset de l'espace

            la position x commence du clic est à 350 et la map à 300
            donc on x=50
         */
			pos.x -= this.mapBounds.left;
			pos.y -= this.mapBounds.bot;

			//obtient le coin supérieur gauche ou commencer a placer des tiles
			start = swingPosToIndex(pos.x, pos.y, this);
		}

		if (entity.getCategory().name.equals(EntitiesCategories.ACTIONS.name)) {
			if(entity.getClassName() == null) {
				// TODO: ajout des actions doit créer une énigme ou pas. (dépends de l'action)
				//  en l'occurence start, exit doivent juste être ajoutés dans la sauvegarde.
				//  une exit, [1 à x] start.
				//PrintColor.println("Ajout des actions non codé", AnsiiColor.YELLOW);
				return null;
			} else if(entity.getClassName().equals(GameExit.class.getName())) {
				if(hasExit){
					return null;
				} else {
					this.hasExit = true;
				}
			}
		}

		GameObject object;

		//si l'entité doit être placée sur la map
		if(start != null) {
			//instancie l'entité
			object = EntityFactory.createEntity(entity, this.added.size(), start);
			Utility.printDebug("loadEntity", object.toString() + " " + object.getID());

			//ajout à la liste des entités de la map
			this.added.put(start, object);

			// on place les tiles
			this.set(object, start);
		} else {
			//sinon c'est une entitée dite temporaire donc pas sur la map mais que l'on garde
			//par exemple pour les énigmes, on peut sélectionner des entitées depuis le menu
			int id = this.added.size();
			start = new Vector2(-1*id,-1*id);

			//instancie l'entité
			object = EntityFactory.createEntity(entity, id, start);
			Utility.printDebug("loadEntity", object.toString() + " " + object.getID());

			//ajout à la liste des entités de la map
			this.added.put(start, object);
		}

		return object;
	}

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	private void set(GameObject entity, Vector2 start) {
		//TODO : interdire si pas big boss ou si déjà big boss pour big boss
		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (entities == null) continue;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth() && index < entities.size; j++, index++) {
					MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
					c.setEntity(entity);

					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	/**
	 * Supprime une entité de la map
	 *
	 * @param entity l'entité
	 * @return true si entité supprimée sinon false
	 * @since 5.1
	 */
	public boolean removeEntity(GameObject entity) {
		Utility.printDebug("deleteEntity", entity+" ("+
				entity.getGameObjectWidth()+" "+entity.getGameObjectHeight()+")"
		);
		//System.out.println(this.added);
		if (this.added.containsValue(entity)) {//peut la supprimer
			Vector2 pos = (Vector2) Utility.getKeyFromValue(this.added, entity);
			this.added.remove(pos);
			if(pos.x >= 0 && pos.y >= 0) this.delete(entity, pos);
			if(entity instanceof GameExit) hasExit = false;
			return true;
		}
		return false;
	}

	/**
	 * Supprime une entité
	 *
	 * @param entity l'entité à supprimé
	 * @param start  sa position
	 * @since 5.1
	 */
	private void delete(GameObject entity, Vector2 start) {
		HashMap<Vector2, GameObject> parents = this.getParentObject(start, entity);
		Array<Array<Float>> entities = null;
		Array<GameObject> objects = null;
		if(parents != null && !parents.isEmpty()) {
			objects = new Array<>();
			for (GameObject obj :parents.values()) {
				objects.add(obj);
			}
			entities = new Array<>();
		}

		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> ent = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));
			//Array<Float> entities = new Array<>();
			if(objects != null) {
				entities.clear();
				for (GameObject o : new Array.ArrayIterator<>(objects)) {
					entities.add(o.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values())));
				}
			}

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (ent == null) continue;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth() && index < ent.size; j++, index++) {
					MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
					int ind = 0;
					c.setEntity(null);
					//si on a des tiles a mettre
					if(entities != null) {
						//regarde parmi tous les objets depuis lequels on peut prendre des tiles
						for (Array<Float> entitiesArray : new Array.ArrayIterator<>(entities)) {
							//si possible
							if (entitiesArray != null && index < entitiesArray.size) {
								//Parcours de toutes les entités ...
								for (Map.Entry<Vector2, GameObject> entry : parents.entrySet()) {
									//... et regarde quelle entité on prends les tiles
									if (Utility.containsBottomLeftOrigin(entry.getValue(), entry.getKey(), j, i)) {
										int indexR = Utility.calculatesOffset(new Vector2(j, i+1),
												entry.getKey(), entry.getValue());
										/*if(Layer.FLOOR1.name().equals(tileLayer.getName())){
											Utility.printDebug("Ré-ajoutTiles",
													index+" "
													+new Vector2(j,i+1)
													+entry.getKey()
													+"id="+indexR
													);
										}*/
										ind = MathUtils.ceil(entitiesArray.get(indexR));
										c.setEntity(entry.getValue());
										break;
									}
								}
							}
						}
					}
					c.setTile(this.map.getMap().getTileSets().getTile(ind));
					tileLayer.setCell(j, i, c);
				}
			}
		}

	}

	/**
	 * Re-instancie les entités
	 * @since 5.2
	 */
	private HashMap<Vector2, GameObject> getParentObject(Vector2 start, GameObject entity) {
		ArrayList<Vector2> delete = new ArrayList<>();
		HashMap<Vector2, GameObject> obj = new HashMap<>();
		Rectangle ent = new Rectangle(start.x, start.y, entity.getGameObjectWidth(), entity.getGameObjectHeight());
		for (Map.Entry<Vector2, GameObject> item :getEntities().entrySet()) {
			Rectangle other = new Rectangle(item.getKey().x, item.getKey().y, item.getValue().getGameObjectWidth(), item.getValue().getGameObjectHeight());
			//check collision
			if (Utility.overlapsBottomLeftOrigin(ent,other)){
				//si c'est un conteneur
				if(entity instanceof ContainersManager){
					//si item est un conteneur, alors on va le reconstruire ses tiles après suppression
					if(item.getValue() instanceof ContainersManager){
						obj.put(item.getKey(), item.getValue());
					}
					//sinon on supprime le contenu
					else {
						delete.add(item.getKey());
					}
				} else {
					//sinon on renvoi le conteneur
					Utility.printDebug("DeleteGetParent", item.toString()+"("+ent+" overlaps "+other+")");
					obj.put(item.getKey(), item.getValue());
					return obj;
				}
			}
		}

		//suppression des contenus
		for (Vector2 v : delete) {
			added.remove(v);
		}

		return obj;
	}


	/**
	 * Cette méthode transforme toutes les cellules de la map en MapLibgdxCell
	 *
	 * @see MapTestScreenCell
	 */
	private void init() {
		MapLayers layers = map.getMap().getLayers();
		for (int i = 0; i < 5; i++) {
			TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
			for (int y = 0; y < layer.getHeight(); y++) {
				for (int x = 0; x < layer.getWidth(); x++) {
					MapTestScreenCell cell = new MapTestScreenCell(layer, y * layer.getWidth() + x);

					TiledMapTileLayer.Cell tmp = layer.getCell(x, y);

					if (tmp != null)
						cell.setTile(tmp.getTile());

					layer.setCell(x, y, cell);
				}
			}
		}
	}

	/**
	 * Permet de créer tout les listeners sur les cases
	 *
	 * @param component component swing
	 */
	private void createCell(Container component) {
		JComponent jcomponent = (JComponent) component;
		CasePopUp popUp = new CasePopUp(jcomponent, this.map.getMap());
		CaseListener listenerCase = new CaseListener(popUp);
		MapLayers layers = map.getMap().getLayers();

		TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);
		for (int y = 0; y < layer.getHeight(); y++) {
			for (int x = 0; x < layer.getWidth(); x++) {
				MapTestScreenCell cell = (MapTestScreenCell) layer.getCell(x, y);

				CaseView actor = new CaseView(cell);

				actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(),
						layer.getTileWidth(), layer.getTileHeight());

				addActor(actor);

				layer.setCell(x, y, cell);

				actor.addListener(listenerCase);
			}
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update camera
		//update map's camera from stage's camera
		Camera c = this.getStage().getCamera();
		this.camera.position.x = c.position.x;
		this.camera.position.y = c.position.y;
		this.camera.update();

		//update borders
		this.border.setProjectionMatrix(this.camera.combined);
	}

	/**
	 * Dessine la map
	 *
	 * @param batch       batch openGL de dessin
	 * @param parentAlpha transparence du parent
	 * @since 2.0
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		//Setup camera
		this.map.setView(this.camera);

		//render map
		this.map.render();

		//render borders
		if (this.showGrid)
			this.border.draw();
	}

	/**
	 * Définit les bounds de la map
	 *
	 * @since 3.0
	 */
	protected void setMapBounds() {
		float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
		float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
		float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
		float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
		this.mapBounds = new Bounds(left, right, top, bot);
	}


	@Override
	protected Rectangle getMapSize() {
		Rectangle r = new Rectangle();
		/*
            Inverse le zoom, avant avec un zoom de 0.95 la map était plus grande et 1.05
            donnait une map plus petite

            zoom contient l'inverse : 1.05 contient une plus grande map, 0.95 une plus petite
         */
		float zoom = camera.zoom;
		if (zoom < 1) {
			zoom = 1 + (1 - camera.zoom);
		} else if (zoom > 1) {
			zoom = 1 + (1 - camera.zoom);
		}

		//mapSize according to zoom
		r.width = Math.round(this.getMapWidth() * zoom);
		r.height = Math.round(this.getMapHeight() * zoom);

		return r;
	}

	@Override
	public TiledMap getTiledMap() {
		return this.map.getMap();
	}

	/**
	 * Affiche la grille de la map
	 *
	 * @param show affiche la grille de la map
	 */
	@Override
	public void showGrid(boolean show) {
		this.showGrid = show;
	}

	@Override
	public float getMapHeight() {
		return mapHeight;
	}

	@Override
	public float getMapWidth() {
		return mapWidth;
	}

	@Override
	public float getUnitScale() {
		return this.map.getUnitScale();
	}

	@Override
	public int getTileWidth() {
		return tileWidth;
	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public Bounds getMapBounds() {
		return mapBounds;
	}

	/**
	 * Retourne les entités de la map et leur position
	 *
	 * @return les entités de la map et leur position
	 */
	public HashMap<Vector2, GameObject> getEntities() {
		return added;
	}

	public OrthogonalTiledMapRenderer getMap() {
		return map;
	}

	/**
	 * Met a jour les bounds de la map selon zoom
	 *
	 * @param zoom de combien le zoom est augmenté ou diminué
	 * @since 3.0
	 * @deprecated since 4.0
	 */
	@Deprecated
	@MagicConstant
	protected void updateMapBounds(int zoom) {
		float left = this.mapBounds.left, right = this.mapBounds.right;
		float top = this.mapBounds.top, bot = this.mapBounds.bot;

		left -= zoom * 27;
		right += zoom * 27;
		top -= zoom * 18;
		bot += zoom * 18;

		this.mapBounds = new Bounds(left, right, top, bot);
	}
}

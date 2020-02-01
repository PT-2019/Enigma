package common.map;

import api.libgdx.utils.Border;
import api.libgdx.utils.Bounds;
import api.libgdx.utils.LibgdxUtility;
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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import common.entities.GameObject;
import common.entities.types.ContainersManager;
import common.save.TmxProperties;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import common.utils.Logger;
import data.Layer;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static common.save.MapsNameUtils.HEIGHT_P;
import static common.save.MapsNameUtils.TILE_HEIGHT_P;
import static common.save.MapsNameUtils.TILE_WIDTH_P;
import static common.save.MapsNameUtils.WIDTH_P;

/**
 * Représentation d'une map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 */
public abstract class AbstractMap extends Group {

	/**
	 * Les entités de la map
	 * @since 6.0
	 */
	protected MapObjects objects;

	/**
	 * Dimensions d'un tile
	 * @since 3.0
	 */
	protected final int tileHeight, tileWidth;

	/**
	 * Dimensions de la map
	 * @since 3.0
	 */
	protected final int mapWidth, mapHeight;

	/**
	 * Dessinateur de la map
	 * @since 3.0
	 */
	protected final OrthogonalTiledMapRenderer map;

	/**
	 * Affichage de la grille.
	 * @since 3.0
	 */
	protected final Border border;

	/**
	 * Les limites de la map dans l'espace
	 * @since 3.0
	 */
	protected Bounds mapBounds;

	/**
	 * Caméra de la map
	 * @since 3.0
	 */
	protected final OrthographicCamera camera;

	/**
	 * Boolean pour activer l'affichage de la grille
	 * @since 4.0
	 */
	protected boolean showGrid;

	/**
	 * Le seul constructeur possible d'une map, ne fait rien
	 *
	 * @param path      chemin d'une map
	 * @param unitScale taux de distortion
	 *
	 * @since 3.0
	 */
	AbstractMap(@NotNull final String path, final float unitScale) {
		// charge la map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

		//sauvegarde des propriétés de la map
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		int width = properties.get(WIDTH_P.value, Integer.class);
		int height = properties.get(HEIGHT_P.value, Integer.class);
		this.mapWidth = width * tileWidth;
		this.mapHeight = height * tileHeight;

		//Caméra
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//cache le niveau de collision
		this.showLayer(Layer.COLLISION, false);

		//bordures
		this.border = new Border(width, height, this.tileHeight);
		this.showGrid = false;

		this.objects = new MapObjects();

		//initialise la map
		this.init();
	}

	/**
	 * Initialisation de la map.
	 *
	 * @since 6.0
	 */
	private void init() {
		//change les cellules de la map
		this.initCells();

		//recharge les entités depuis sauvegarde
		this.initEntities();

		//bounds
		this.setMapBounds();
	}

	// utils (static)

	/**
	 * Retourne la case (indices) dans la map depuis une positon x,y dans l'espace.
	 * <p>
	 * Attention! La position  x,y est considérée comme étant toujours dans la map.
	 *
	 * @param posX position x
	 * @param posY position y
	 * @param map  la map
	 * @return la case (indices) dans la map depuis une positon x,y dans l'espace.
	 * @since 3.0 14 décembre 2019
	 * <p>
	 * <p>
	 * VERSION SWING TO LIBGDX
	 */
	public static Vector2 posToIndex(float posX, float posY, final AbstractMap map) {
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapBounds().right);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapBounds().top);

		index.x = column;
		index.y = row;

		return index;
	}

	//initialisations

	/**
	 * Cette méthode transforme toutes les cellules de la map en MapLibgdxCell
	 *
	 * @see MapTestScreenCell
	 * @since 4.0
	 */
	private void initCells() {
		MapLayers layers = this.map.getMap().getLayers();
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
	 * Re-instancie les entités contenues dans le .tmx
	 *
	 * @since 5.3
	 */
	private void initEntities() {
		ArrayList<MapProperties> entities = getProperty(TmxProperties.TMX_PROP_ENTITY, this);
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
			if (id == null) id = 0;
			Vector2 start = new Vector2(x, y);
			if (start.x >= 0 && start.y >= 0) {
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
				object.load(prop);

				Logger.printDebug("MapTestScreen#initEntities", object + " " + start);

				//ajout à la liste des entités de la map
				this.objects.put(start, object);
				//this.added.put(start, object);

				// on place les tiles
				this.setFromSave(object, start);
			} else {
				//entitée temporaire, pas sur la map
				e = new EntitySerializable(width, height, className, new HashMap<>());
				GameObject object = EntityFactory.createEntity(e, id, start);

				//ajout à la liste des entités de la map
				//this.added.put(start, object);
				this.objects.put(start, object);

				Logger.printDebug("(tmp) MapTestScreen#initEntities", object + " " + start);
			}
		}
	}

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	public void set(GameObject entity, Vector2 start) {
		if(!this.objects.contains(entity))
			this.objects.put(start, entity);

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
	 * Place une entité lue par {@link #initEntities()} ie depuis la sauvegarde
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 5.3
	 */
	private void setFromSave(GameObject entity, Vector2 start) {
		//un manager ne peut pas être déposé sauf s'il l'emplacement est vide.
		boolean manager = entity instanceof ContainersManager;

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
					//si l'entité contient une salle
					GameObject ent = c.getEntity();
					if (ent == null  //vide : je peux placer sans problèmes
							|| !manager) {//sinon cela dépends de ce que je veux placer
						c.setEntity(entity);
						tileLayer.setCell(j, i, c);
						//PrintColor.println("Placement de "+entity+" sur "+ent, AnsiiColor.CYAN);
					}
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
		Logger.printDebug("deleteEntity", entity + " (" +
				entity.getGameObjectWidth() + " " + entity.getGameObjectHeight() + ")"
		);
		//System.out.println(this.added);
		if (this.objects.contains(entity)) {//peut la supprimer
			Vector2 pos = this.objects.getVectorByObject(entity);
			this.objects.remove(pos);
			if (pos.x >= 0 && pos.y >= 0) this.delete(entity, pos);
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
		if (parents != null && !parents.isEmpty()) {
			objects = new Array<>();
			for (GameObject obj : parents.values()) {
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
			if (objects != null) {
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
					if (entities != null) {
						//regarde parmi tous les objets depuis lequels on peut prendre des tiles
						for (Array<Float> entitiesArray : new Array.ArrayIterator<>(entities)) {
							//si possible
							if (entitiesArray != null && index < entitiesArray.size) {
								//Parcours de toutes les entités ...
								for (Map.Entry<Vector2, GameObject> entry : parents.entrySet()) {
									//... et regarde quelle entité on prends les tiles
									if (LibgdxUtility.containsBottomLeftOrigin(entry.getValue(), entry.getKey(), j, i)) {
										int indexR = LibgdxUtility.calculatesOffset(new Vector2(j, i + 1),
												entry.getKey(), entry.getValue());
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
	 *
	 * @since 5.2
	 */
	protected HashMap<Vector2, GameObject> getParentObject(Vector2 start, GameObject entity) {
		ArrayList<Vector2> delete = new ArrayList<>();
		HashMap<Vector2, GameObject> obj = new HashMap<>();
		Rectangle ent = new Rectangle(start.x, start.y, entity.getGameObjectWidth(), entity.getGameObjectHeight());
		for (Map.Entry<Vector2, ArrayList<GameObject>> item : this.objects.getAll().entrySet()) {
			for (GameObject object : item.getValue()) {
				Rectangle other = new Rectangle(item.getKey().x, item.getKey().y,
						object.getGameObjectWidth(), object.getGameObjectHeight());
				//check collision
				if (LibgdxUtility.overlapsBottomLeftOrigin(ent, other)) {
					//si c'est un conteneur
					if (entity instanceof ContainersManager) {
						//si item est un conteneur, alors on va le reconstruire ses tiles après suppression
						if (item.getValue() instanceof ContainersManager) {
							//je garde une salle
							obj.put(item.getKey(), object);
						}
						//sinon on supprime le contenu
						else {
							delete.add(item.getKey());
						}
					} else {
						//sinon on renvoi le conteneur
						Logger.printDebug("DeleteGetParent", item.toString() + "(" + ent + " overlaps " + other + ")");
						obj.put(item.getKey(), object);
						return obj;
					}
				}
			}
		}

		//suppression des contenus
		for (Vector2 v : delete) {
			this.objects.remove(v);
		}

		return obj;
	}

	// libgdx

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

	//utils

	/**
	 * Affiche ou cache un niveau
	 *
	 * @param layer niveau
	 * @param show  true pour afficher sinon false
	 */
	@SuppressWarnings("WeakerAccess")
	public void showLayer(Layer layer, boolean show) {
		MapLayer mapLayer = this.map.getMap().getLayers().get(layer.name());
		if (mapLayer != null) mapLayer.setVisible(show);
	}

	/**
	 * Retourne une liste des propriétés contenant name
	 *
	 * @param name un name (tag name d'une property d'un .tmx)
	 * @return une liste des propriétés contenant name
	 * @since 5.0
	 */
	@SuppressWarnings({"", "WeakerAccess", "SameParameterValue"})
	protected static ArrayList<MapProperties> getProperty(String name, AbstractMap map) {
		ArrayList<MapProperties> props = new ArrayList<>();
		for (MapLayer layer : map.getTiledMap().getLayers()) {
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
	 * Affiche la grille de la map
	 *
	 * @param show affiche la grille de la map
	 */
	public void showGrid(boolean show) {
		this.showGrid = show;
	}

	/**
	 * Renvoi la hauteur de la map
	 *
	 * @return la hauteur de la map
	 */
	public float getMapHeight() {
		return this.mapHeight;
	}

	/**
	 * Renvoi la largeur de la map
	 *
	 * @return la largeur de la map
	 */
	public float getMapWidth() {
		return this.mapWidth;
	}

	/**
	 * Retourne le taux de distorsion/zoom de la taille d'un tile
	 *
	 * @return le taux de distorsion de la taille d'un tile
	 */
	public float getUnitScale() {
		return this.map.getUnitScale();
	}

	/**
	 * Retourne la largeur d'un tile
	 *
	 * @return la largeur d'un tile
	 */
	public int getTileWidth() {
		return this.tileWidth;
	}

	/**
	 * Retourne la hauteur d'un tile
	 *
	 * @return la hauteur d'un tile
	 */
	public int getTileHeight() {
		return this.tileHeight;
	}

	/**
	 * Retourne la map tiled
	 *
	 * @return la map tiled
	 */
	public TiledMap getTiledMap() {
		return this.map.getMap();
	}

	/**
	 * Retourne la caméra de la map
	 * @return la caméra de la map
	 */
	public OrthographicCamera getCamera() {
		return this.camera;
	}

	/**
	 * Retourne les limites de la map
	 * @return les limites de la map
	 */
	public Bounds getMapBounds() {
		return this.mapBounds;
	}

	/**
	 * Définit les limites de la map
	 * @since 3.0
	 */
	protected void setMapBounds() {
		float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
		float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
		float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
		float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
		this.mapBounds = new Bounds(left, right, top, bot);
	}

	/**
	 * Retourne le dessinateur de la map
	 * @return le dessinateur de la map
	 */
	public OrthogonalTiledMapRenderer getMap() {
		return this.map;
	}

	/**
	 * Retourne la taille de la map, correction avec la caméra
	 * @return la taille de la map
	 */
	protected Rectangle getMapSize() {
		Rectangle r = new Rectangle();
		/*
            Inverse le zoom, avant avec un zoom de 0.95 la map était plus grande et 1.05
            donnait une map plus petite

            zoom contient l'inverse : 1.05 contient une plus grande map, 0.95 une plus petite
         */
		float zoom = this.camera.zoom;
		if (zoom < 1) {
			zoom = 1 + (1 - this.camera.zoom);
		} else if (zoom > 1) {
			zoom = 1 + (1 - this.camera.zoom);
		}

		//mapSize according to zoom
		r.width = Math.round(this.getMapWidth() * zoom);
		r.height = Math.round(this.getMapHeight() * zoom);

		return r;
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

	/**
	 * Retourne les entités de la map
	 * @return les entités de la map
	 *
	 * @since 6.0
	 */
	public MapObjects getEntities(){
		return this.objects;
	}
}

package common.map;

import api.libgdx.actor.GameActor;
import api.libgdx.utils.Border;
import api.libgdx.utils.Bounds;
import api.ui.CustomWindow;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.GameObject;
import common.entities.players.*;
import common.save.entities.serialization.*;
import data.Layer;
import editor.EditorLauncher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static common.save.MapsNameUtils.HEIGHT_P;
import static common.save.MapsNameUtils.TILE_HEIGHT_P;
import static common.save.MapsNameUtils.TILE_WIDTH_P;
import static common.save.MapsNameUtils.WIDTH_P;

/**
 * Map de la libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0.0
 * @since 1.0 28 janvier 2019
 */
public class GameMap extends AbstractMap {
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
	 * Tableau des entités
	 */
	private ArrayList<GameActor> entities;
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

	public GameMap(final String path, float unitScale) {
		//CE CONSTRUCTEUR NE FAIT RIEN
		//c'est uniquement pour que votre constructeur soit de cette forme
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

		//bordures
		this.border = new Border(width, height, this.tileHeight);

		//dimension de la map
		this.mapWidth = width * tileWidth;
		this.mapHeight = height * tileHeight;

		//cache le niveau de collision
		MapLayer collision = this.map.getMap().getLayers().get(Layer.COLLISION.name());
		if (collision != null)
			collision.setVisible(false);

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.window = EditorLauncher.getInstance().getWindow();

		//createCell(this.window.getContentPane());

		this.added = new HashMap<>();
		this.entities = new ArrayList<>();

		init();
		//bounds
		this.setMapBounds();

		this.initEntities();
	}

	public static Vector2 posToIndex(float posX, float posY, GameMap map) {
		//ICI CODER
		//AbstractMap#swingPosToIndex(float posX, float posY, final AbstractMap map) si besoin
		//throw new UnsupportedOperationException("non codé");
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapBounds().right);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapBounds().top);

		index.x = column;
		index.y = row;

		return index;
	}

	public boolean isWalkable(float posX, float posY, GameActor actor) {
		//ICI CODER, gameActor est pas obligé, la classe peut être changé (GameActorAnimation..., PlayerGame)...
		//throw new UnsupportedOperationException("non codé");
		return true;
	}

	//boolean doAction(float posX, float posY, GameActor actor, ???  action)

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

	/**
	 * Retourne une liste des propriétés contenant name
	 *
	 * @param name un name (tag name d'une property d'un .tmx)
	 * @return une liste des propriétés contenant name
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

	private void initEntities() {
		ArrayList<MapProperties> entities = getProperty("entity");
		float x, y;
		int width, height;
		String className;
		String isheros;
		EntitySerializable e;
		for (MapProperties prop : entities) {
			width = Math.round(prop.get("width", Float.class));
			height = Math.round(prop.get("height", Float.class));
			className = prop.get("className", String.class);
			x = prop.get("x", Float.class);
			//obligé de faire ce truc sale y2 car y renvoi truc bizarres y=789 renvoi y=0...
			y = Float.parseFloat(prop.get("y2", String.class));
			Vector2 start = new Vector2(x, y);
			e = new EntitySerializable(width, height, className, new HashMap<>());
			GameObject object = EntityFactory.createEntity(e, this.added.size(), start);

			Utility.printDebug("GameScreen#initEntities", object + " " + start);

			//on place pas les entités "vivantes" dans les tiles de la map
			if (object instanceof NPC){
				isheros = prop.get("HERO", String.class);
				String name = prop.get("KEY", String.class);
				String path = prop.get("JSON", String.class);
				GameActor entity;

				if (isheros.equals("true")){
					entity = PlayerFactory.createPlayerGame(name, path, this);
					((NPC)object).setHero(true);
				}else{
					//c'est un npc
					entity = NpcFactory.createNpcGame(name,path);
				}
				this.addEntity(entity);
				//on soustrait et augmente pour que ce soit à la bonne position
				entity.setPosition((x+0.5f)*this.tileWidth*this.getUnitScale(),
						(y-2)*this.tileHeight*this.getUnitScale());
				this.setEntityFromSave(object,start);
			}else if(object instanceof Monster){
				String path = prop.get("JSON", String.class);
				String name = prop.get("KEY", String.class);

				GameActor monster = MonsterFactory.createMonsterGame(name, path);
				this.addEntity(monster);
				monster.setPosition((x+0.5f)*this.tileWidth*this.getUnitScale(),
						(y-2)*this.tileHeight*this.getUnitScale());
				this.setEntityFromSave(object,start);
			}else{
				// on place les tiles
				this.setFromSave(object, start);
			}

			//ajout à la liste des entités de la map
			this.added.put(start, object);
		}
	}

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	private void setFromSave(GameObject entity, Vector2 start) {
		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth(); j++, index++) {
					MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
					if (c == null) continue;
					c.setEntity(entity);
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	/**
	 * Enlève l'entités vivante de la map
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	private void setEntityFromSave(GameObject entity, Vector2 start) {

		//on prends le layer où sont afficher les entités
		MapLayer mapLayer = this.map.getMap().getLayers().get(Layer.FLOOR2.name());
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

		//calcul pour placer les tiles depuis x et y
		//sachant que y est inversé, on part de la dernière tile et on remonte
		//pas de problème pour x
		for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
			for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth(); j++, index++) {
				MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
				if (c==null) continue;
				c.setTile(null);

				tileLayer.setCell(j, i, c);
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
	private void set(GameObject entity, Vector2 start) {
		if (!this.added.containsKey(start)) this.added.put(start, entity);
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
	 * Permet de savoir si c'est possible de bouger un personnage en fonction des autres GameActor
	 * @param actor On va tester en fonction de cet actor
	 * @return boolean true si collision false sinon
	 */
	public boolean collision(GameActor actor){
		boolean result = false;
		//on va itérer tout les actors connu sur la map
		//todo : peut être un autre système
		ArrayList<GameActor> actors = (ArrayList<GameActor>) this.entities.clone();
		actors.remove(actor);
		for (GameActor act : actors) {
			if (actor.overlaps(act)){
				result = true;
				break;
			}
		}
		//pour vider au cas où l'arrayList temporaire
		actors = null;
		return result;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update map
		Camera c = this.getStage().getCamera();
		this.camera.position.x = c.position.x;
		this.camera.position.y = c.position.y;
		this.camera.update();

		//update borders
		this.border.setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);//cette ligne dessine les composant ajoutés à la map

		//dessin de la map
		//....
		//Setup camera
		this.map.setView(this.camera);

		for (Layer layer : Layer.values()) {
			if (layer == Layer.COLLISION) {
				continue;
			}
			for (GameActor entite : this.entities) {
				if (entite.getLayer() == layer) {
					batch.end();
					batch.begin();
					entite.draw(batch, parentAlpha);
					batch.end();
					batch.begin();
				}
			}
			//render map
			this.map.render(new int[]{layer.ordinal()});
		}

		//render borders
		if (this.showGrid)
			this.border.draw();

	}

	/**
	 * Ajoute une entité à l'array d'entités
	 *
	 * @param actor entité à ajouter
	 */
	public void addEntity(@NotNull GameActor actor) {
		if (actor instanceof PlayerGame) this.addActor(actor);
		this.entities.add(actor);
	}

	/**
	 * Supprime une entité de l'array d'entités
	 *
	 * @param actor entité à supprimer
	 */
	public void removeEntity(@NotNull GameActor actor) {
		if (this.entities.contains(actor)) {
			this.entities.remove(actor);
		} else {
			throw new IllegalArgumentException("MapGame#remove : actor not in mapEntities");
		}
	}

	/**
	 * Cette méthode transforme toutes les cellules de la map en MapLibgdxCell
	 *
	 * @see MapTestScreenCell
	 */
	private void init() {
		//pour ne pas que les personnages fassent parti de la map
		//position des entités
		/*ArrayList<Vector2> position = new ArrayList<>();
		for (Map.Entry<Vector2,GameObject> object: this.added.entrySet()) {
			if(object.getValue() instanceof EntityGame){
				position.add(object.getKey());
			}
		}					//Pour éviter d'afficher les entités en tant que map
		if(layer.getName().equals(Layer.FLOOR2)){
			for (Vector2 vec: position) {
				if (vec.x == x && vec.y == y){
					tmp = null;
				}
			}
		}*/

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
	//A COMPLETER TOUS LES GETTERS ET SETTERS... (voir MapTestScreen)

	@Override
	protected Bounds getMapBounds() {
		return mapBounds;
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

	@Override
	public TiledMap getTiledMap() {
		return this.map.getMap();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public ArrayList<GameActor> getEntities() {
		return entities;
	}
}

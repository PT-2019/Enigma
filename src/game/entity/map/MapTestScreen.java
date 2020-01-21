package game.entity.map;

import api.entity.GameObject;
import api.enums.AnsiiColor;
import api.enums.EntitiesCategories;
import api.enums.Layer;
import api.hud.components.CustomWindow;
import api.utils.Bounds;
import api.utils.PrintColor;
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
import editor.view.cases.CasePopUp;
import editor.view.cases.CaseView;
import editor.view.listeners.CaseListener;
import game.hud.Border;
import game.hud.CategoriesMenu;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import starter.EditorLauncher;

import javax.swing.JComponent;
import java.awt.Container;
import java.util.HashMap;

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
 * @version 4.4
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

	private void initEntities() {
		for (MapLayer layer : this.map.getMap().getLayers()) {
			for (MapObject mapObject : layer.getObjects()) {
				/*MapProperties prop = mapObject.getProperties();
				if(prop.containsKey("entity")){
					System.out.println("instancie!");
				} else {
					Iterator<String> values = prop.getKeys();
					while (values.hasNext()) {
						System.out.println(values.next());
					}
				}*/
			}
		}
	}

	@Override
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

	@Override
	public boolean loadEntity(EntitySerializable entity, Vector2 pos) {
		//calcules les 4 coins de la map
		Rectangle bounds = this.getMapSize();
		bounds.setPosition((Gdx.graphics.getWidth() / 2f - camera.position.x) * 1,
				(Gdx.graphics.getHeight() / 2f - camera.position.y) * 1);
		this.mapBounds = new Bounds(bounds);

		Gdx.app.debug("MapLibgdx - placement", mapBounds + " pos=" + pos + " " + mapBounds.contains(pos));

		//si pas dans la map
		if (!mapBounds.contains(pos)) return false;

        /*
            retire l'offset de l'espace

            la position x commence du clic est à 350 et la map à 300
            donc on x=50
         */
		pos.x -= this.mapBounds.left;
		pos.y -= this.mapBounds.bot;

		//obtient le coin supérieur gauche ou commencer a placer des tiles
		Vector2 start = posToIndex(pos.x, pos.y, this);

		if(entity.getCategory().name.equals(EntitiesCategories.ACTIONS.name)){
			// TODO: ajout des actions doit créer une énigme ou pas. (dépends de l'action)
			//  en l'occurence start, exit doivent juste être ajoutés dans la sauvegarde.
			//  une exit, [1 à x] start.
			PrintColor.println("Ajout des actions non codé", AnsiiColor.YELLOW);
			return false;
		}

		//instancie l'entité
		GameObject object = EntityFactory.createEntity(entity, this.added.size(), start);

		//ajout à la liste des entités de la map
		this.added.put(start, object);

		// on place les tiles
		this.set(object, start);

		return false;
	}

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	private void set(GameObject entity, Vector2 start) {
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

	@Override
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
}

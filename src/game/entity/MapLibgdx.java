package game.entity;

import api.enums.Layer;
import api.utils.Bounds;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
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
import editor.entity.EntitySerializable;
import game.ui.Border;
import game.ui.CategoriesMenu;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;

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
 * @version 4.3 22/12/2019
 * @since 2.0 5 décembre 2019
 */
public class MapLibgdx extends Group{

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
	private HashMap<Vector2 ,EntitySerializable> added;

	/**
	 * Crée une map depuis un fichier tmx
	 *
	 * @param path fichier .tmx
	 * @since 2.0
	 */
	public MapLibgdx(@NotNull final String path) {
		//load the map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap, 1f);

		//save needed properties
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		int width = properties.get(WIDTH_P.value, Integer.class);
		int height = properties.get(HEIGHT_P.value, Integer.class);

		//bordures
		this.border = new Border(width,
				(height * (int) this.map.getUnitScale()),
				this.tileHeight);

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
		//centre map dans l'écran
		float x = Gdx.graphics.getWidth()/2f - this.mapWidth/2f - CategoriesMenu.WIDTH;
		float y = Gdx.graphics.getHeight()/2f - this.mapHeight/2f;
		if(x < 0) x*= -1;
		if(y < 0) y*=-1;

		this.camera.position.set(x, y, 0);
		this.camera.update();

		this.added = new HashMap<>();

		//bounds
		this.setMapBounds();
	}

	/**
	 * Retourne la case (indices) dans la map depuis une positon x,y dans l'espace.
	 *
	 * Attention! La position  x,y est considérée comme étant toujours dans la map.
	 *
	 * @param posX position x
	 * @param posY position y
	 * @param map  la map
	 * @return la case (indices) dans la map depuis une positon x,y dans l'espace.
	 * @since 3.0 14 décembre 2019
	 */
	private static Vector2 posToIndex(float posX, float posY, final MapLibgdx map) {
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.mapBounds.right);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.mapBounds.top);

		index.x = column;
		index.y = row;

		return index;
	}

	/**
	 * Charge une entité sur la map a un position si elle est sur la map
	 *
	 * @param entity l'entité à charger
	 * @param pos    la position o&#249; charger
	 * @return true si l'entité a étée chargée
	 * @since 3.0
	 */
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

		// obtient le coin supérieur gauche ou commencer a placer des tiles
		Vector2 start = posToIndex(pos.x, pos.y, this);

		//ajout à la liste des entités de la map
		this.added.put(start,entity);

		// on place les tiles
		this.set(entity, start);

		return true;
	}

	private Rectangle getMapSize(){
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

	/**
	 * Place une entité
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 *
	 * @since 4.0
	 */
	private void set(EntitySerializable entity, Vector2 start) {
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
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getWidth() && index < entities.size; j++, index++) {
					MapLibgdxCell c = new MapLibgdxCell(tileLayer, index);
					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
					c.setEntity(entity);
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update camera
		//update map's camera from stage's camera
		//Camera c = this.getStage().getCamera();
		//this.camera.position.x = c.position.x;
		//this.camera.position.y = c.position.y;
		//this.camera.update();
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
		this.border.draw();
	}

	/**
	 * Définit les bounds de la map
	 *
	 * @since 3.0
	 */
	private void setMapBounds() {
		float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
		float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
		float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
		float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
		this.mapBounds = new Bounds(left, right, top, bot);
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
	private void updateMapBounds(int zoom) {
		float left = this.mapBounds.left, right = this.mapBounds.right;
		float top = this.mapBounds.top, bot = this.mapBounds.bot;

		left -= zoom * 27;
		right += zoom * 27;
		top -= zoom * 18;
		bot += zoom * 18;

		this.mapBounds = new Bounds(left, right, top, bot);
	}

	public float getMapHeight() {
		return mapHeight;
	}

	public float getMapWidth() {
		return mapWidth;
	}

	public float getUnitScale() {
		return this.map.getUnitScale();
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	/**
	 * Retourne les entités de la map et leur position
	 *
	 * @return les entités de la map et leur position
	 */
	public HashMap<Vector2, EntitySerializable> getEntities() {
		return added;
	}

	public TiledMap getTiledMap() { return this.map.getMap(); }
}


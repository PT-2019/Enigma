package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import editor.datas.Layer;
import editor.entity.EntitySerializable;
import editor.utils.Utility;
import game.ui.Border;
import game.utils.Bounds;
import game.utils.InputListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static game.api.MapsNameUtils.HEIGHT_P;
import static game.api.MapsNameUtils.TILE_HEIGHT_P;
import static game.api.MapsNameUtils.TILE_WIDTH_P;
import static game.api.MapsNameUtils.WIDTH_P;

/**
 * Map de la libgdx
 *
 * @version 3.0 14 décembre 2019
 * @since 2.0 5 décembre 2019
 */
public class MapLibgdx extends Group implements InputListener {

	/**
	 * info principales
	 */
	private final int tileWidth, tileHeight, width, height;
	/**
	 * Dessinateur de la map
	 */
	private OrthogonalTiledMapRenderer map;
	/**
	 * Caméra de la map
	 */
	private OrthographicCamera camera;
	/**
	 * Bordure des cases de la map
	 */
	private Border border;

	private Bounds mapBounds;

	/**
	 * Crée une map depuis un fichier tmx
	 *
	 * @param path fichier .tmx
	 */
	public MapLibgdx(@NotNull String path) {
		//load the map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap, 1f);

		//save needed properties
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		this.width = properties.get(WIDTH_P.value, Integer.class);
		this.height = properties.get(HEIGHT_P.value, Integer.class);

		this.border = new Border(this.width, this.height, this.tileHeight);

		this.border = new Border(this.width,
				(this.height * (int) this.map.getUnitScale()),
				this.tileHeight);

		MapLayer collision = this.map.getMap().getLayers().get(Layer.COLLISION.name());
		if (collision != null)
			collision.setVisible(false);

		//setup camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.set(getMapWidth() / 2, getMapHeight() / 2, 0);
		this.camera.update();

		//bounds
		this.setMapBounds();

		System.out.println(mapBounds);
	}

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
	 */
	private static Vector2 posToIndex(float posX, float posY, MapLibgdx map) {
		Vector2 index = new Vector2();

		//convert coordinates to row and column
		posX = (posX / map.getUnitScale()) - map.mapBounds.left;
		posY = (posY / map.getUnitScale()) - map.mapBounds.top;

		float mapW = (map.mapBounds.right - map.mapBounds.left) / map.tileWidth;
		float mapH = (map.mapBounds.bot - map.mapBounds.top) / map.tileHeight;

		//and clamp to the map
		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, mapW);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, mapH);

		index.x = column;
		index.y = mapH - row;

		return index;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update caméra
		camera.update();
		//update borders
		border.setProjectionMatrix(camera.combined);
	}

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

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.LEFT) {
			camera.translate(-32, 0);
			return true;
		}
		if (keycode == Input.Keys.RIGHT) {
			camera.translate(32, 0);
			return true;
		}
		if (keycode == Input.Keys.UP) {
			camera.translate(0, 32);
			return true;
		}
		if (keycode == Input.Keys.DOWN) {
			camera.translate(0, -32);
			return true;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			if (amount == 1) {
				camera.zoom += 0.1;
				updateMapBounds(-1);
			} else {
				camera.zoom -= 0.1;
				updateMapBounds(1);
			}
			return true;
		}
		return false;
	}

	private void updateMapBounds(float zoom){
		float left = this.mapBounds.left, right = this.mapBounds.right;
		float top =  this.mapBounds.top, bot =  this.mapBounds.bot;

		left -= zoom * 27;
		right += zoom * 27;
		top -= zoom * 18;
		bot += zoom * 18;

		this.mapBounds = new Bounds(left, right, top, bot);

		System.out.println(this.mapBounds);
	}

	private void setMapBounds() {
		float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
		float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
		float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
		float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
		this.mapBounds = new Bounds(left, right, top, bot);
	}

	/**
	 * Charge une entité dans la map depuis un point
	 *
	 * @param entity   entité a placer
	 * @param location coordonnés x,y ou placer l'entité
	 * @since 3.0 14 décembre 2019
	 */
	public void load(EntitySerializable entity, Point location) {
		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (entities == null) continue;

			System.out.println(location+" "+camera.zoom);

			float maxW = this.mapBounds.right ;//- entity.getWidth() * tileWidth;
			float maxH = this.mapBounds.bot ;//- entity.getHeight() * tileHeight;

			//on regarde si l'entités est dans la map
			Vector2 start = null; //start contient le x, y ou on commence a placer les tiles
			if (location.x >= this.mapBounds.left && location.x <= maxW) {//Axe x
				if (location.y >= this.mapBounds.top && location.y <= maxH) {//Axe y
					start = posToIndex(location.x, location.y, this);
				}
			}

			//si pas dans la map
			if (start == null) return;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getHeight()); i--) {
				for (int j = (int) start.x; j < start.x + entity.getWidth() && index < entities.size; j++, index++) {
					MapLibgdxCell c = new MapLibgdxCell();
					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
					c.setEntity(entity);
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	private float getMapHeight() {
		return height * tileHeight;
	}

	public float getMapWidth() {
		return width * tileWidth;
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
}


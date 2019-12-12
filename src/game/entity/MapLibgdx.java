package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import editor.datas.Direction;
import editor.datas.Layer;
import editor.entity.EntityFactory;
import editor.entity.interfaces.Entity;
import editor.utils.Utility;
import game.ui.Border;
import game.utils.InputListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Set;

import static game.api.MapsNameUtils.*;

public class MapLibgdx extends Actor implements InputListener {

	/**
	 * The .tmx map
	 */
	private OrthogonalTiledMapRenderer map;
	/**
	 * Map's camera
	 */
	private OrthographicCamera camera;

	private Border border;

	/**
	 * Main map infos
	 */
	private final int tileWidth, tileHeight, width, height;

	/**
	 * Create a map from a .tmx file.
	 *
	 * @param path the .tmx file
	 */
	public MapLibgdx(@NotNull String path) {
		//load the map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap);//TODO: scale ?

		//save needed properties
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		this.width = properties.get(WIDTH_P.value, Integer.class);
		this.height = properties.get(HEIGHT_P.value, Integer.class);

		border = new Border(width, height, tileHeight);

		//setup camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.update();

		this.camera.position.set(getMapWidth()/2,getMapHeight()/2,0);
	}

	private float getMapHeight() {
		return height * tileHeight;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		camera.update();
		border.setProjectionMatrix(camera.combined);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		//Setup camera
		this.map.setView(this.camera);

		this.map.render();

		this.border.draw();
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT) {
			camera.translate(-32, 0);
			return true;
		}
		if(keycode == Input.Keys.RIGHT){
			camera.translate(32,0);
			return true;
		}
		if(keycode == Input.Keys.UP) {
			camera.translate(0, 32);
			return true;
		}
		if(keycode == Input.Keys.DOWN) {
			camera.translate(0, -32);
			return true;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
			if (amount==1){
				camera.zoom += 0.05;
			}else{
				camera.zoom -= 0.05;
			}
			return true;
		}
		return false;
	}

	public float getMapWidth() {
		return width * tileWidth;
	}

	//TODO: refaire, illisible et deg
	public void load(EntityFactory.EntitySerializable entity, Point location) {
		for (MapLayer mapLayer: this.map.getMap().getLayers()) {
			if(!(mapLayer instanceof TiledMapTileLayer)) continue;
			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

			if(entities == null) continue;

			//...
			float dCamD = this.getStage().getWidth()/2 - this.getMapWidth()  /2;
			float dCamE = this.getStage().getWidth()/2 + this.getMapWidth()/2 - entity.getWidth()*tileWidth;

			System.out.println(location+" "+dCamD);

			float dCamT = this.getStage().getHeight()/2 - this.getMapHeight() / 2;
			float dCamB = this.getStage().getHeight()/2 + this.getMapHeight() / 2 - entity.getHeight()*tileHeight;

			Vector2 deb;
			if(location.x >= dCamD && location.x <= dCamE){
				if(location.y >= dCamT && location.y <= dCamB){
					deb = posToIndex(location.x, location.y, this);
				} else {
					return;
				}
			} else {
				return;
			}

			for (int i = (int) deb.y -1 , index = 0; i >= (deb.y - entity.getHeight()) ; i--) {
				for (int j = (int) deb.x; j < deb.x + entity.getWidth() && index < entities.size ; j++, index++) {
					TiledMapTileLayer.Cell c = new TiledMapTileLayer.Cell();
					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	private static Vector2 posToIndex(float posX, float posY, MapLibgdx map) {
		Vector2 index = new Vector2();

		//convert coordinates to row and column
		//and clamp to the map
		float dCamD = map.getStage().getWidth()/2 - map.getMapWidth() /2;
		float dCamT = map.getStage().getHeight()/2 - map.getMapHeight() / 2;

		index.x = MathUtils.round((posX - dCamD) / map.tileWidth);
		index.y = map.height - Math.round((posY - dCamT) / map.tileHeight);

		return index;
	}

	public float getUnitScale(){ return this.map.getUnitScale(); }

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
}


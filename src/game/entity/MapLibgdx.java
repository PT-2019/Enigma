package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.ui.Border;
import game.utils.InputListener;
import org.jetbrains.annotations.NotNull;

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

	public int getMapWidth() {
		return width * tileWidth;
	}
}


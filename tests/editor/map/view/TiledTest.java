package editor.map.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import editor.map.MapLoader;

import javax.swing.*;

public class TiledTest extends Game implements InputProcessor {
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	JComponent component;
	int[] layers;
	Border border;

	public TiledTest(JComponent c){
		component = c;
		layers = new int[]{0,1,2,3};
	}

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.update();
		tiledMap = new TmxMapLoader().load("assets/map/Loadtest.tmx");
		MapLoader gameMap = new MapLoader();
		gameMap.load("assets/map/Loadtest.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		TileMap map = new TileMap(tiledMap,component,gameMap.getMap());
		MapProperties prop = tiledMap.getProperties();

		border = new Border((int)prop.get("width"),(int)prop.get("height"),(int)prop.get("tileheight"));

		InputMultiplexer multi = new InputMultiplexer();
		multi.addProcessor(this);
		multi.addProcessor(map);
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		border.setProjectionMatrix(camera.combined);

		border.draw();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layers);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT)
			camera.translate(-32,0);
		if(keycode == Input.Keys.RIGHT)
			camera.translate(-32,0);
		if(keycode == Input.Keys.UP)
			camera.translate(0,32);
		if(keycode == Input.Keys.DOWN)
			camera.translate(0,32);
		if(keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
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
		}
		return false;
	}
}
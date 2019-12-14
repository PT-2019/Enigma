package editor.map.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import editor.map.Map;
import editor.map.MapLoader;

import javax.swing.*;

public class TiledTest extends Game {

	OrthographicCamera camera;

	TiledMapRenderer tiledMapRenderer;

	JComponent component;

	int[] layers;

	Border border;

	RoomView room;

	public TiledTest(JComponent c){
		component = c;
		layers = new int[]{0,1,2,3};
	}

	@Override
	public void create () {
		int midx,midy;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		TiledMap tiledMap = new TmxMapLoader().load("assets/map/Loadtest.tmx");
		Map tmpMap;
		camera = new OrthographicCamera();
		MapLoader gameMap = new MapLoader();
		InputMultiplexer multi = new InputMultiplexer();
		MapControlleur control = new MapControlleur(camera,tiledMap);

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		MapProperties prop = tiledMap.getProperties();

		camera.setToOrtho(false,w,h);
		camera.update();

		gameMap.load("assets/map/Loadtest.tmx");
		tmpMap = gameMap.getMap();

		midx = tmpMap.getCol()*(int)prop.get("tileheight")/2;
		midy = tmpMap.getRow()*(int)prop.get("tileheight")/2;
		camera.position.set(midx,midy,0);

		border = new Border((int)prop.get("width"),(int)prop.get("height"),(int)prop.get("tileheight"));
		room = new RoomView(tmpMap.getRooms(),(int)prop.get("tileheight"),tmpMap.getRow());

		TileMap map = new TileMap(tiledMap,component,tmpMap,room,camera);

		multi.addProcessor(control);
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
		room.setProjectionMatrix(camera.combined);
		room.draw();
		border.draw();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layers);
	}
}
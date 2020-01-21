package editor.entity.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import editor.utils.map.Map;
import editor.utils.save.MapLoader;
import game.hud.Border;

import javax.swing.JComponent;

@Deprecated
public class TiledTest extends Game {

	OrthographicCamera camera;

	TiledMapRenderer tiledMapRenderer;

	JComponent component;

	int[] layers;

	Border border;

	RoomView room;

	CollisionView collisionView;

	TileMap tilemap;

	public TiledTest(JComponent c) {
		component = c;
		layers = new int[]{0, 1, 2, 3};
	}

	@Override
	public void create() {
		int midx, midy;
		int width, heigth, tile;

		TiledMap tiledMap = new TmxMapLoader().load("assets/map/old/Loadtest.tmx");
		Map tmpMap;
		camera = new OrthographicCamera();
		MapLoader gameMap = new MapLoader();
		InputMultiplexer multi = new InputMultiplexer();

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		MapProperties prop = tiledMap.getProperties();
		width = (int) prop.get("width");
		heigth = (int) prop.get("height");
		tile = (int) prop.get("tileheight");

		gameMap.load("assets/map/old/Loadtest.tmx");
		tmpMap = gameMap.getMap();

		midx = tmpMap.getCol() * (int) prop.get("tileheight") / 2;
		midy = tmpMap.getRow() * (int) prop.get("tileheight") / 2;
		camera.position.set(midx, midy, 0);

		border = new Border(width, heigth, tile);
		room = new RoomView(tmpMap.getRooms(), tile, heigth, width);
		collisionView = new CollisionView(tmpMap.getCases(), tile, width, heigth);

		MapControl control = new MapControl(camera, component, room, collisionView);
		tilemap = new TileMap(tiledMap, component, tmpMap);

		camera.setToOrtho(false, width * tile, heigth * tile);
		camera.update();

		tilemap.getViewport().setCamera(camera);

		multi.addProcessor(control);
		multi.addProcessor(tilemap);
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		border.setProjectionMatrix(camera.combined);
		room.setProjectionMatrix(camera.combined);
		collisionView.setProjectionMatrix(camera.combined);
		room.draw();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		collisionView.draw();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		border.draw();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(layers);
	}
}
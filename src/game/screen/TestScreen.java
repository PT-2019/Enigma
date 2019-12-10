package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.map.Map;
import game.api.LibgdxScreen;
import game.screen.test.MapLibgdx;

public class TestScreen extends LibgdxScreen {

	private Stage m;

	@Override
	public void init() {
		m = new Stage();
		MapLibgdx map = new MapLibgdx("assets/map/Loadtest.tmx");

		m.addActor(map);

		InputMultiplexer multi = new InputMultiplexer();
		multi.addProcessor(map);
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void input() {}

	@Override
	public void update(float dt) {
		m.act(dt);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(255,255,255,255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		m.draw();
	}

	@Override
	public void dispose() {
		m.dispose();
	}

	@Override
	public void resize(int width, int height) {}

}

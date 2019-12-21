package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import game.EnigmaGame;
import game.api.LibgdxScreen;
import game.entity.MapLibgdx;

public class TestScreen extends LibgdxScreen {

	private Stage main;

	private MapLibgdx map;

	@Override
	public void init() {
		try {
			Gdx.gl.glClearColor(255, 255, 255, 255);

			this.main = new Stage();

			this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

			this.map = new MapLibgdx("assets/map/test.tmx");

			this.main.addActor(map);

			this.listen(map);
			this.listen(this.main);
		} catch (Exception e) {
			Gdx.app.error(this.getClass().toString(), e.toString());
		}
	}

	@Override
	public void input() {
	}

	@Override
	public void update(float dt) {
		this.main.act(dt);
	}

	@Override
	public void render() {
		this.main.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	public MapLibgdx getMap() {
		return map;
	}

	@Override
	public void display(boolean display) {
	}

	@Override
	public void dispose() {
		this.main.dispose();
	}
}

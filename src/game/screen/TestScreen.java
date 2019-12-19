package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import editor.enigma.Enigma;
import editor.map.view.TestMapControl;
import game.EnigmaGame;
import game.api.LibgdxScreen;
import game.entity.MapLibgdx;

import java.awt.*;

public class TestScreen extends LibgdxScreen {

	private Stage main;
	private MapLibgdx map;
	private Container container;

	public TestScreen(Container container){
		this.container= container;
	}

	@Override
	public void init() {
		this.main = new Stage();

		this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		map = new MapLibgdx("assets/map/Empty.tmx",container);

		this.main.addActor(map);

		this.listen(new TestMapControl(map));
		this.listen(this.main);
	}

	@Override
	public void input() {}

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

	public MapLibgdx getMap() { return map;	}

	@Override
	public void display(boolean display) {}

	@Override
	public void dispose() {
		this.main.dispose();
	}
}

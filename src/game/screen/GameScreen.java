package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import editor.utils.save.view.TestMapControl;
import game.EnigmaGame;
import game.entity.MapLibgdx;

public class GameScreen extends LibgdxScreen {

	private Stage hud, main;

	@Override
	public void init() {
		this.main = new Stage();
		this.hud = new Stage();

		MapLibgdx map = new MapLibgdx(TestScreen.getMapPath(), 2.5f);
		this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		this.main.addActor(map);

		this.listen(new TestMapControl(map));
		this.listen(this.hud);
		this.listen(this.main);
	}

	@Override
	public void input() {
	}

	@Override
	public void update(float dt) {
		this.hud.act(dt);
		this.main.act(dt);
	}

	@Override
	public void render() {
		this.main.draw();
		this.hud.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.hud.getViewport().setScreenSize(width, height);
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	@Override
	public void dispose() {
		this.main.dispose();
		this.hud.dispose();
	}

	@Override
	public void display(boolean display) {

	}
}

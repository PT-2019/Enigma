package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TestScreen extends LibgdxScreen {

	public boolean setMap(String path) {
		return false;
	}

	@Override
	public void display(boolean display) {
	}

	@Override
	public void init() {
	}

	@Override
	public void input() {
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 255, 0, 255);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}

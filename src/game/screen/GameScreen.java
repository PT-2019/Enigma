package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends LibgdxScreen {

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
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void show() {
		Gdx.gl20.glClearColor(255, 0, 0, 255);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render() {
	}

	@Override
	public void display(boolean display) {

	}


}

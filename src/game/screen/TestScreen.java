package game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.api.LibgdxScreen;

public class TestScreen extends LibgdxScreen {

	SpriteBatch batch;
	Texture img;

	@Override
	public void init() {
		batch = new SpriteBatch();
		img = new Texture("assets/badlogic.jpg");
	}

	@Override
	public void input() {}

	@Override
	public void update(float dt) {}

	@Override
	public void render() {
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height) {}

}

package game.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import game.api.LibgdxScreen;
import game.entity.MapLibgdx;

public class TestScreen extends LibgdxScreen {

	private Stage main;

	@Override
	public void init() {
		this.main = new Stage();

		MapLibgdx map = new MapLibgdx("assets/map/Empty.tmx");

		this.main.addActor(map);

		this.listen(map);
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
	public void resize(int width, int height) {}

	@Override
	public void display(boolean display) {}

	@Override
	public void dispose() {
		this.main.dispose();
	}
}

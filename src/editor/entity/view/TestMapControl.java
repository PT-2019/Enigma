package editor.entity.view;

import api.hud.components.CustomWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import game.entity.map.MapTestScreen;
import starter.EditorLauncher;

public class TestMapControl implements InputProcessor {

	private static final float ZOOM_VALUE = 0.05f;

	private static final int CAMERA_OFFSET = 32;

	private boolean ispush;

	private EntityPopMenu menu;

	//@Deprecated
	//private JComponent component;

	private CustomWindow window;

	private OrthographicCamera camera;

	private MapTestScreen map;

	public TestMapControl(MapTestScreen map) {
		camera = map.getCamera();
		ispush = false;
		this.map = map;
		//changed to window
		this.window = EditorLauncher.getInstance().getWindow();

		this.menu = new EntityPopMenu(map.getMap().getMap(), camera);
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.CONTROL_LEFT)
			ispush = true;
		if (keycode == Input.Keys.PLUS && ispush)
			this.plusCamera();
		if (keycode == Input.Keys.MINUS && ispush)
			this.minCamera();

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			camera.translate(-CAMERA_OFFSET, 0);
			camera.update();
			return true;
		}
		if (keycode == Input.Keys.RIGHT) {
			camera.translate(CAMERA_OFFSET, 0);
			camera.update();
			return true;
		}
		if (keycode == Input.Keys.UP) {
			camera.translate(0, CAMERA_OFFSET);
			camera.update();
			return true;
		}
		if (keycode == Input.Keys.DOWN) {
			camera.translate(0, -CAMERA_OFFSET);
			camera.update();
			return true;
		}
		if (keycode == Input.Keys.CONTROL_LEFT) {
			ispush = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.RIGHT) {
			menu.show(this.window, Gdx.input.getX(), Gdx.input.getY());
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			if (amount == 1) {
				this.camera.zoom += ZOOM_VALUE;
			} else {
				this.camera.zoom -= ZOOM_VALUE;
			}
			this.camera.update();
			return true;
		}
		return false;
	}


	private void plusCamera() {
		camera.zoom -= ZOOM_VALUE;
	}

	private void minCamera() {
		camera.zoom += ZOOM_VALUE;
	}
}

package editor.popup;

import api.libgdx.utils.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import data.EditorState;
import data.config.Config;
import editor.EditorLauncher;

/**
 * Contrôleur des différents évènement sur la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 2.0
 */
public class TestMapControl implements InputAdapter {

	/**
	 * True si touche control appuyée
	 */
	private boolean isPush;

	/**
	 * Menu popup
	 */
	private EntityPopMenu menu;

	/**
	 * Caméra
	 */
	private OrthographicCamera camera;

	/**
	 * Contrôleur des différents évènement sur la map
	 *
	 * @param map map
	 */
	public TestMapControl(MapTestScreen map) {
		this.camera = map.getCamera();
		this.isPush = false;
		//changed to window
		this.menu = new EntityPopMenu(map, (EnigmaWindow) EditorLauncher.getInstance().getWindow());
	}

	@Override
	public boolean keyDown(int keycode) {
		//control activé
		if (keycode == Input.Keys.CONTROL_LEFT) {
			this.isPush = true;
			return true;
		}
		//zoom ou de-zoom
		if (keycode == Input.Keys.PLUS && this.isPush) {
			this.plusCamera();
			return true;
		}
		if (keycode == Input.Keys.MINUS && this.isPush) {
			this.minCamera();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			this.camera.translate(-Config.CAMERA_OFFSET, 0);
			this.camera.update();
			return true;
		}
		if (keycode == Input.Keys.RIGHT) {
			this.camera.translate(Config.CAMERA_OFFSET, 0);
			this.camera.update();
			return true;
		}
		if (keycode == Input.Keys.UP) {
			this.camera.translate(0, Config.CAMERA_OFFSET);
			this.camera.update();
			return true;
		}
		if (keycode == Input.Keys.DOWN) {
			this.camera.translate(0, -Config.CAMERA_OFFSET);
			this.camera.update();
			return true;
		}
		//dés-activation control
		if (keycode == Input.Keys.CONTROL_LEFT) {
			this.isPush = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.RIGHT) {
			this.menu.show(EditorLauncher.getInstance().getMapPanel(), Gdx.input.getX(), Gdx.input.getY());
			return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			if (amount == 1) {
				this.minCamera();
			} else {
				this.plusCamera();
			}

			if (this.camera.zoom == Config.BASE_ZOOM_VALUE) {
				//mode normal
				EditorLauncher.removeState(EditorState.ZOOM);
			} else {
				//sinon zoom
				if (EditorLauncher.containsState(EditorState.NORMAL)) {
					EditorLauncher.clearStates();
				}
				EditorLauncher.addState(EditorState.ZOOM);
			}

			this.camera.update();

			return true; //géré
		}

		return false;
	}

	/**
	 * Augmente le zoom
	 */
	private void plusCamera() {
		this.camera.zoom -= Config.ZOOM_CHANGE_VALUE;
	}

	/**
	 * Diminue le zoom
	 */
	private void minCamera() {
		this.camera.zoom += Config.ZOOM_CHANGE_VALUE;
	}
}

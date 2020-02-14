package editor.popup;

import api.libgdx.utils.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import data.config.Config;
import data.EditorState;
import editor.EditorLauncher;
import editor.bar.listeners.RedoListener;
import editor.bar.listeners.SaveAsListener;
import editor.bar.listeners.SaveListener;
import editor.bar.listeners.UndoListener;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

/**
 * Contrôleur des différents évènement sur la map de l'éditeur
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
	 * Pour savoir si la touche ctrl est enfoncé
	 * @since 2.0
	 */
	private boolean ctrlPush;

	/**
	 * Pour savoir si la touche alt est enfoncé
	 * @since 6.0
	 */
	private boolean altPush;

	/**
	 * Menu popup
	 * @since 2.0
	 */
	private EntityPopMenu menu;

	/**
	 * Caméra
	 * @since 2.0
	 */
	private OrthographicCamera camera;

	//pour les raccourcis
	/**
	 * Sauvegarde
	 * @since 6.0
	 */
	private SaveListener save;
	/**
	 * Sauvegarder sous
	 * @since 6.0
	 */
	private SaveAsListener saveAs;
	/**
	 * Annuler retour arrière
	 * @since 6.0
	 */
	private RedoListener redo;
	/**
	 * Retour arrière
	 * @since 6.0
	 */
	private UndoListener undo;

	/**
	 * Contrôleur des différents évènement sur la map
	 *
	 * @param map map
	 */
	public TestMapControl(MapTestScreen map) {
		this.camera = map.getCamera();
		this.ctrlPush = false;
		//changed to window
		EnigmaWindow window = (EnigmaWindow) EditorLauncher.getInstance().getWindow();

		//raccourcis
		this.save = new SaveListener(window, new JButton());
		this.saveAs = new SaveAsListener(window, new JButton());
		this.undo = new UndoListener(window, new JButton());
		this.redo = new RedoListener(window, new JButton());

		//menu clic droit
		this.menu = new EntityPopMenu(map, (EnigmaWindow) EditorLauncher.getInstance().getWindow());
	}

	@Override
	public boolean keyDown(int keycode) {
		//alt activé
		if (keycode == Input.Keys.ALT_LEFT)
			this.altPush = true;
		//control activé
		else if (keycode == Input.Keys.CONTROL_LEFT)
			this.ctrlPush = true;
		//zoom ou de-zoom
		else if (keycode == Input.Keys.PLUS && this.ctrlPush)
			this.plusCamera();
		else if (keycode == Input.Keys.MINUS && this.ctrlPush)
			this.minCamera();
		else if(keycode == Config.SAVE.getMain() &&this. ctrlPush && !this.altPush){
			//on met un actionEvent sans importance car pas utilisé dans les actionPerformed
			this.save.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(keycode == Config.UNDO.getMain() && this.ctrlPush){
			this.undo.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(keycode == Config.REDO.getMain() && this.ctrlPush){
			this.redo.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(this.ctrlPush && this.altPush && keycode == Config.SAVE_AS.getMain()){
			this.saveAs.actionPerformed(new ActionEvent(new Object(),0,""));
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			this.camera.translate(-Config.CAMERA_OFFSET, 0);
			this.camera.update();
			return true;
		}else if (keycode == Input.Keys.RIGHT) {
			this.camera.translate(Config.CAMERA_OFFSET, 0);
			this.camera.update();
			return true;
		} else if (keycode == Input.Keys.UP) {
			this.camera.translate(0, Config.CAMERA_OFFSET);
			this.camera.update();
			return true;
		}else if (keycode == Input.Keys.DOWN) {
			this.camera.translate(0, -Config.CAMERA_OFFSET);
			this.camera.update();
			return true;
		}//dés-activation control
		else if (keycode == Input.Keys.CONTROL_LEFT) {
			this.ctrlPush = false;
			return true;
		//dés-activation alt
		}else if (keycode == Input.Keys.ALT_LEFT){
			this.altPush = false;
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

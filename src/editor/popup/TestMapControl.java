package editor.popup;

import api.libgdx.utils.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import data.config.Config;
import data.EditorState;
import data.config.Config;
import editor.EditorLauncher;
import editor.bar.listeners.RedoListener;
import editor.bar.listeners.SaveAsListener;
import editor.bar.listeners.SaveListener;
import editor.bar.listeners.UndoListener;

import javax.swing.*;
import java.awt.*;
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
 * Controlleur des différents évènement sur la map
 */
public class TestMapControl implements InputProcessor {

	//racourcis pour l'enregistrement
	private static final int SAVE = Input.Keys.S;

	private static final int UNDO = Input.Keys.Z;

	private static final int REDO = Input.Keys.Y;

	/**
	 * Pour savoir si la touche ctrl est enfoncé
	 */
	private boolean ctrlpush;

	/**
	 * Pour savoir si la touche alt est enfoncé
	 */
	private boolean altpush;

	/**
	 * Menu popup
	 */
	private EntityPopMenu menu;

	/**
	 * Caméra
	 */
	private OrthographicCamera camera;

	private MapTestScreen map;

	//pour les racourcis
	private SaveListener save;

	private SaveAsListener saveAs;

	private RedoListener redo;

	private UndoListener undo;

	/**
	 * Contrôleur des différents évènement sur la map
	 *
	 * @param map map
	 */
	public TestMapControl(MapTestScreen map) {
		this.camera = map.getCamera();
		this.ctrlpush = false;
		this.map = map;
		//changed to window
		EnigmaWindow window = EditorLauncher.getInstance().getWindow();

		Container contain = EditorLauncher.getInstance().getWindow().getContentPane();

		save = new SaveListener((EnigmaWindow) this.window,contain);
		saveAs = new SaveAsListener((EnigmaWindow) this.window,(JComponent)contain);
		undo = new UndoListener((EnigmaWindow) this.window,contain);
		redo = new RedoListener((EnigmaWindow) this.window,contain);;

		//changed to window
		this.menu = new EntityPopMenu(map, (EnigmaWindow) EditorLauncher.getInstance().getWindow());
	}

	@Override
	public boolean keyDown(int keycode) {

		//alt activé
		if (keycode == Input.Keys.ALT_LEFT)
			altpush = true;
		//control activé
		else if (keycode == Input.Keys.CONTROL_LEFT)
			ctrlpush = true;
		//zoom ou de-zoom
		else if (keycode == Input.Keys.PLUS && ctrlpush)
			this.plusCamera();
		else if (keycode == Input.Keys.MINUS && ctrlpush)
			this.minCamera();
		else if(keycode == Config.SAVE.getMain() && ctrlpush && !altpush){
			//on met un actionEvent sans importance car pas utilisé dans les actionPerformed
			save.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(keycode == Config.UNDO.getMain() && ctrlpush){
			undo.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(keycode == Config.REDO.getMain() && ctrlpush){
			redo.actionPerformed(new ActionEvent(new Object(),0,""));
		}else if(ctrlpush && altpush && keycode == Config.SAVE_AS.getMain()){
			saveAs.actionPerformed(new ActionEvent(new Object(),0,""));
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
			this.camera.translate(CAMERA_OFFSET, 0);
			this.camera.update();
			return true;
		} else if (keycode == Input.Keys.UP) {
			this.camera.translate(0, CAMERA_OFFSET);
			this.camera.update();
			return true;
		}else if (keycode == Input.Keys.DOWN) {
			this.camera.translate(0, -CAMERA_OFFSET);
			this.camera.update();
			return true;
		}//dés-activation control
		else if (keycode == Input.Keys.CONTROL_LEFT) {
			this.ctrlpush = false;
			return true;
		//dés-activation alt
		}else if (keycode == Input.Keys.ALT_LEFT){
			this.altpush = false;
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

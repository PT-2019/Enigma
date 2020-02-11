package editor.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
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
 * Controleur des différents évènement sur la map de l'éditeur
 */
public class TestMapControl implements InputProcessor {

	private static final float ZOOM_VALUE = 0.05f;

	private static final int CAMERA_OFFSET = 32;

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

	private EntityPopMenu menu;

	//@Deprecated
	//private JComponent component;

	private Window window;

	private OrthographicCamera camera;

	private MapTestScreen map;

	//pour les racourcis
	private SaveListener save;

	private SaveAsListener saveAs;

	private RedoListener redo;

	private UndoListener undo;

	public TestMapControl(MapTestScreen map) {
		camera = map.getCamera();
		ctrlpush = false;
		this.map = map;
		//changed to window
		this.window = EditorLauncher.getInstance().getWindow();

		Container contain = EditorLauncher.getInstance().getWindow().getContentPane();

		save = new SaveListener((EnigmaWindow) this.window,contain);
		saveAs = new SaveAsListener((EnigmaWindow) this.window,(JComponent)contain);
		undo = new UndoListener((EnigmaWindow) this.window,contain);
		redo = new RedoListener((EnigmaWindow) this.window,contain);;

		this.menu = new EntityPopMenu(map.getMap().getMap(), camera);
		//TODO: test pour vérifier que cela marche avec une window
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.ALT_LEFT)
			altpush = true;
		else if (keycode == Input.Keys.CONTROL_LEFT)
			ctrlpush = true;
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
			camera.translate(-CAMERA_OFFSET, 0);
			camera.update();
			return true;
		}else if (keycode == Input.Keys.RIGHT) {
			camera.translate(CAMERA_OFFSET, 0);
			camera.update();
			return true;
		} else if (keycode == Input.Keys.UP) {
			camera.translate(0, CAMERA_OFFSET);
			camera.update();
			return true;
		}else if (keycode == Input.Keys.DOWN) {
			camera.translate(0, -CAMERA_OFFSET);
			camera.update();
			return true;
		}else if (keycode == Input.Keys.CONTROL_LEFT) {
			ctrlpush = false;
			return true;
		}else if (keycode == Input.Keys.ALT_LEFT){
			altpush = false;
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

package editor.popup;

import api.ui.CustomOptionPane;
import api.ui.CustomWindow;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.config.Config;
import editor.EditorLauncher;
import editor.bar.edition.ActionsManager;
import editor.bar.listeners.SaveAsListener;
import editor.bar.listeners.SaveListener;
import game.EnigmaGame;
import game.screens.TestScreen;

import java.awt.Window;
import java.io.IOException;
import java.util.HashMap;

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
	private boolean ispush;

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

	public TestMapControl(MapTestScreen map) {
		camera = map.getCamera();
		ispush = false;
		this.map = map;
		//changed to window
		this.window = EditorLauncher.getInstance().getWindow();


		this.menu = new EntityPopMenu(map.getMap().getMap(), camera);
		//TODO: test pour vérifier que cela marche avec une window
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.ALT_LEFT)
			altpush = true;
		else if (keycode == Input.Keys.CONTROL_LEFT)
			ispush = true;
		else if (keycode == Input.Keys.PLUS && ispush)
			this.plusCamera();
		else if (keycode == Input.Keys.MINUS && ispush)
			this.minCamera();
		else if(keycode == SAVE && ispush && !altpush){
			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(TestScreen.getMapPath().replace(Config.MAP_EXTENSION,""), map.getTiledMap(), map.getEntities());
			EnigmaGame.getCurrentScreen().showToast(SaveListener.SAVE_ENDED);
		}else if(keycode == UNDO && ispush){
			ActionsManager manager = ActionsManager.getInstance();
			manager.undo();
		}else if(keycode == REDO && ispush){
			ActionsManager manager = ActionsManager.getInstance();
			manager.redo();
		}else if(ispush && altpush && keycode == SAVE){
			//enregistrer sous
			String mapName = EnigmaOptionPane.showInputDialog((CustomWindow) this.window, SaveAsListener.MAP_NAME);
			mapName = Utility.normalize(mapName);

			for(String s : Utility.getAllMapName()) {
				if (s.equals(mapName)){
					if(!EnigmaOptionPane.showConfirmDialog((CustomWindow) this.window,SaveAsListener.REPLACE_MAP)){
						return true;
					}
				}
			}
			HashMap<String,String> data = EnigmaGame.getCurrentScreen().getMap().getMapData().getData();
			data.replace(MapData.MAP_NAME,mapName);

			if (!mapName.equals(CustomOptionPane.CANCEL) && !mapName.equals("")) {
				try {
					DataSave.writeMapData(new MapData(data));
				}catch (IOException e){
					EnigmaGame.getCurrentScreen().showToast(SaveAsListener.SAVE_FAILED);
					Logger.printError("SavAsListener.java","dave data : " + e.getMessage());
				}

				MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
				EmptyMapGenerator.save(Config.MAP_FOLDER + mapName, map.getTiledMap(), map.getEntities());
				EnigmaGame.getCurrentScreen().showToast(SaveAsListener.SAVE_ENDED);
			}else{
				EnigmaGame.getCurrentScreen().showToast(SaveAsListener.SAVE_CANCELED);
			}
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
			ispush = false;
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

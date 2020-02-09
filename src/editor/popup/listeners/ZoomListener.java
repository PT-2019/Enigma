package editor.popup.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import common.map.AbstractMap;
import data.EditorState;
import data.NeedToBeTranslated;
import data.config.Config;
import editor.EditorLauncher;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener du zoom
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class ZoomListener implements ActionListener {

	/**
	 * Map
	 */
	private final AbstractMap map;

	/**
	 * Listener du zoom
	 * @param map la map
	 */
	public ZoomListener(AbstractMap map) {
		this.map = map;
	}

	@Override
	public void actionPerformed(@NotNull ActionEvent e) {
		String selected = e.getActionCommand();
		//valeur de base du zoom
		float baseZoomValue = Config.BASE_ZOOM_VALUE;

		switch (selected) {
			//valeur de base
			case NeedToBeTranslated.ZOOM_BASE: {
				OrthographicCamera camera = this.map.getCamera();
				camera.zoom = baseZoomValue;
				EditorLauncher.removeState(EditorState.ZOOM);
				break;
			}
			//prends tout l'écran
			case NeedToBeTranslated.ZOOM_FIT: {
				float mapHeight = this.map.getMapHeight();
				//calcul du ratio
				float ratio = Config.ZOOM_RATIO_BY_HEIGHT / (Gdx.graphics.getHeight() / Config.RATIO_HEIGHT);
				float value = ratio * (mapHeight / this.map.getTileHeight()) / Config.RATIO_UNIT;
				OrthographicCamera camera = this.map.getCamera();
				camera.zoom = value;
				//ajout l'état zoom
				if (EditorLauncher.containsState(EditorState.NORMAL)) EditorLauncher.clearStates();
				EditorLauncher.addState(EditorState.ZOOM);
				break;
			//zoom en jeu
			} case NeedToBeTranslated.ZOOM_IN_GAME: {
				OrthographicCamera camera = this.map.getCamera();
				camera.zoom = Config.IN_GAME_ZOOM_VALUE;
				//ajout l'état zoom
				if(EditorLauncher.containsState(EditorState.NORMAL)) EditorLauncher.clearStates();
				EditorLauncher.addState(EditorState.ZOOM);
				break;
			}
			//on a envoyé une valeur
			default: {
				float zoom;
				OrthographicCamera camera = this.map.getCamera();
				try {
					if (selected.contains("%")) {
						selected = selected.replace("%", "");
						//conversion en pourcentage
						zoom = Integer.parseInt(selected) / 100f;
						if(zoom < baseZoomValue){
							zoom = baseZoomValue + (baseZoomValue - zoom);
						} else if (zoom > baseZoomValue) {
							zoom = baseZoomValue - (zoom - baseZoomValue);
						}
						if (zoom == 0) {
							zoom = 0.1f;
						}
					} else {
						zoom = Integer.parseInt(selected);
					}
				} catch (NumberFormatException ex) {
					zoom = baseZoomValue;
				}
				camera.zoom = zoom;
				if(zoom != baseZoomValue){
					//ajout l'état zoom
					if(EditorLauncher.containsState(EditorState.NORMAL)) EditorLauncher.clearStates();
					EditorLauncher.addState(EditorState.ZOOM);
				} else {
					//désactive zoom
					EditorLauncher.removeState(EditorState.ZOOM);
				}

			}
		}
	}
}

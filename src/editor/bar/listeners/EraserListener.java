package editor.bar.listeners;

import common.hud.EnigmaWindow;
import data.EditorState;
import editor.EditorLauncher;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'activation et dés-activation de la gomme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 23/01/2020
 * @since 5.0 23/01/2020
 */
public class EraserListener extends MenuListener {

	private static final String ERASER_CURSOR_PATH = "assets/icon/eraserMouse.png";

	private static final Cursor ERASER_CURSOR; //init plus bas dans un constructeur static

	/*init du cursor */
	static {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(ERASER_CURSOR_PATH);
		//init cursor
		ERASER_CURSOR = toolkit.createCustomCursor(image, new Point(5, 5), "eraser");
	}

	public EraserListener(EnigmaWindow window, @Nullable Component parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//si c'est déjà en erase, alors on reset
		if (EditorLauncher.containsState(EditorState.ERASE)) {
			setActive(false);
			//exception du zoom, passe en normal automatiquement si pas de zoom
			EditorLauncher.clearStates(EditorState.PERSISTANT);
		} else {
			//si on active la gomme
			setActive(true);
			//exception des persistants
			EditorLauncher.clearStates(EditorState.PERSISTANT);
			//passe en "gomme"
			EditorLauncher.addState(EditorState.ERASE);
		}

	}

	/**
	 * Définit si la gomme est active
	 *
	 * @param active true active sinon false
	 */
	private void setActive(boolean active) {
		if (active) {
			//change souris en gomme
			EditorLauncher.setCursor(EraserListener.ERASER_CURSOR);
		} else {
			EditorLauncher.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}

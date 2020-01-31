package editor.bar.listeners;

import data.EditorState;
import editor.EditorLauncher;
import game.screens.TestScreen;
import general.hud.EnigmaWindow;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'activation et dés-activation du déplacement
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 23/01/2020
 * @since 5.0 23/01/2020
 */
public class MoveListener extends MenuListener {

	private static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);

	public MoveListener(EnigmaWindow window) {
		super(window);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//si c'est déjà en move, alors on reset
		if (TestScreen.isState(EditorState.MOVE)) {
			setActive(false);
			TestScreen.setState(EditorState.NORMAL);
		} else {
			//si on active la gomme
			setActive(true);
			TestScreen.setState(EditorState.MOVE);
		}
	}

	/**
	 * Définit si move est active
	 *
	 * @param active true active sinon false
	 */
	private void setActive(boolean active) {
		if (active) {
			//change souris en move
			EditorLauncher.setCursor(MOVE_CURSOR);
		} else {
			EditorLauncher.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}

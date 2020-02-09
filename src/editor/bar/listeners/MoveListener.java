package editor.bar.listeners;

import common.hud.EnigmaWindow;
import data.EditorState;
import editor.EditorLauncher;
import game.screens.TestScreen;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
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

	public MoveListener(EnigmaWindow window, @Nullable Component parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//si c'est déjà en move, alors on reset
		if (EditorLauncher.containsState(EditorState.MOVE)) {
			setActive(false);
			EditorLauncher.clearStates(EditorState.ZOOM);
		} else {
			//si on active la gomme
			setActive(true);
			//si on est en mode zoom, alors on va pas en mode déplacement
			EditorLauncher.clearStates(EditorState.ZOOM);
			EditorLauncher.addState(EditorState.MOVE);
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

package editor.screens.menus.listeners;

import api.enums.EditorState;
import editor.hud.EnigmaWindow;
import game.screen.TestScreen;
import starter.EditorLauncher;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'activation et dés-activation du pinceau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 23/01/2020
 * @since 5.0 23/01/2020
 */
public class BrushListener extends MenuListener {

	public BrushListener(EnigmaWindow window) {
		super(window);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EditorLauncher.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		TestScreen.setState(EditorState.NORMAL);
	}
}

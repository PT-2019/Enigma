package editor.bar.listeners;

import common.hud.EnigmaWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Protoyupe de listener du menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 3.0
 */
public abstract class MenuListener implements ActionListener {

	protected EnigmaWindow window;

	MenuListener(EnigmaWindow window) {
		this.window = window;
	}

	public abstract void actionPerformed(ActionEvent e);
}

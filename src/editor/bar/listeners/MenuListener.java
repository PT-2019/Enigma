package editor.bar.listeners;

import common.hud.EnigmaWindow;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Prototype de listener du menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 3.0
 */
public abstract class MenuListener implements ActionListener {

	protected final EnigmaWindow window;
	protected final Component parent;

	/**
	 * Prototype de listener du menu
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	MenuListener(EnigmaWindow window, Component parent) {
		this.window = window;
		this.parent = parent;
	}

	/**
	 * Prototype de listener du menu
	 * @param window fenêtre
	 */
	MenuListener(EnigmaWindow window) {
		this.window = window;
		this.parent = null;
	}

	public abstract void actionPerformed(ActionEvent e);
}

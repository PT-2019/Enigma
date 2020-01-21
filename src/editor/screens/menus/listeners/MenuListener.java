package editor.screens.menus.listeners;

import editor.hud.EnigmaWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuListener implements ActionListener {

	protected EnigmaWindow window;

	MenuListener(EnigmaWindow window) {
		this.window = window;
	}

	public abstract void actionPerformed(ActionEvent e);
}

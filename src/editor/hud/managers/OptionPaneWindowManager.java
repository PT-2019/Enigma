package editor.hud.managers;

import editor.hud.EnigmaOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * TODO: comment OptionPaneWindowManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class OptionPaneWindowManager implements WindowListener {

	private EnigmaOptionPane optionPane;

	public OptionPaneWindowManager(EnigmaOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	@Override
	public void windowOpened(WindowEvent windowEvent) {
	}

	@Override
	public void windowClosing(WindowEvent windowEvent) {
	}

	@Override
	public void windowClosed(WindowEvent windowEvent) {
		this.optionPane.cancel();
	}

	@Override
	public void windowIconified(WindowEvent windowEvent) {
	}

	@Override
	public void windowDeiconified(WindowEvent windowEvent) {
	}

	@Override
	public void windowActivated(WindowEvent windowEvent) {
	}

	@Override
	public void windowDeactivated(WindowEvent windowEvent) {
	}
}

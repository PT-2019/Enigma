package api.ui.manager;

import api.ui.CustomOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Observateur de la fenêtre d'un optionPane
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomOptionPaneWindowManager extends WindowAdapter {

	private CustomOptionPane optionPane;

	public CustomOptionPaneWindowManager(CustomOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	@Override
	public void windowClosed(WindowEvent windowEvent) {
		this.optionPane.cancel();
	}
}

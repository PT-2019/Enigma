package api.ui.manager.window;

import api.ui.CustomWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ferme la fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Exit implements ActionListener {

	/**
	 * Fenêtre a fermer
	 */
	protected CustomWindow window;

	/**
	 * Appelée pour fermer la fenêtre
	 *
	 * @param window la fenêtre a fermer
	 */
	public Exit(CustomWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.window.dispose();
	}
}

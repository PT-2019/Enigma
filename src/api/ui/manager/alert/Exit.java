package api.ui.manager.alert;

import api.ui.CustomAlert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener de la fermeture de la fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class Exit implements ActionListener {

	private CustomAlert window;

	public Exit(CustomAlert window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.window.dispose();
	}
}

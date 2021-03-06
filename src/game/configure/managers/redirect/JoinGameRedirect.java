package game.configure.managers.redirect;

import api.ui.CustomTextArea;
import game.configure.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gère l'action de rejoindre une partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 * @deprecated
 */
@Deprecated
public class JoinGameRedirect implements ActionListener {
	private CustomTextArea textArea;

	public JoinGameRedirect(CustomTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println("ip = " + textArea.getText());
		//TODO: se connecter et modifier GameConfiguration en conséquence
		LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.WAIT_PLAYERS);
	}
}

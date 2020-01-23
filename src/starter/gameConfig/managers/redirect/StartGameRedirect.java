package starter.gameConfig.managers.redirect;

import game.GameConfiguration;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gère l'action de lancer une partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class StartGameRedirect implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (!GameConfiguration.getInstance().isMultiPlayer()) {
			//TODO: lancer la bonne partie en fonction de GameConfiguration (solo)
		} else if (!GameConfiguration.getInstance().isFull()) {
			//TODO: lancer un serveur
			LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.WAIT_PLAYERS_LEADER);
		} else {
			//TODO: lancer la bonne partie en fonction de GameConfiguration (multi)
		}
	}
}

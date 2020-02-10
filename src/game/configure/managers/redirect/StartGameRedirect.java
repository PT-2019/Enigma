package game.configure.managers.redirect;

import data.config.GameConfigurationDeprecated;
import game.configure.LaunchGameDisplay;

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
 * @deprecated
 */
public class StartGameRedirect implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (!GameConfigurationDeprecated.getInstance().isMultiPlayer()) {
			//TODO: lancer la bonne partie en fonction de GameConfiguration (solo)
		} else if (!GameConfigurationDeprecated.getInstance().isFull()) {
			//TODO: lancer un serveur
			LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.WAIT_PLAYERS_LEADER);
		} else {
			//TODO: lancer la bonne partie en fonction de GameConfiguration (multi)
		}
	}
}

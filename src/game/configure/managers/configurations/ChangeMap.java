package game.configure.managers.configurations;

import common.hud.EnigmaOptionPane;
import data.config.GameConfigurationDeprecated;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;

import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer la map de la partie
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
public class ChangeMap implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(), "Choisir une nouvelle map :");

		if (!value.equals(EnigmaOptionPane.CANCEL)) {
			GameConfigurationDeprecated.getInstance().setMap(value);
			LaunchGameDisplay.getInstance().refreshCurrentDisplay();
		}
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		this.onChange();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}

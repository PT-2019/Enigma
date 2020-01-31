package game.configure.managers.configurations;

import data.config.GameConfiguration;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;
import general.hud.EnigmaOptionPane;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer la durée de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class ChangeDuration implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(), "Nouvelle durée de la partie en minutes :");
		if (!value.equals(EnigmaOptionPane.CANCEL)) {
			try {
				int valueInt = Integer.parseInt(value);
				if (valueInt > 0) {
					GameConfiguration.getInstance().setDuration(valueInt);
					LaunchGameDisplay.getInstance().refreshCurrentDisplay();
				} else
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), new Dimension(1000, 250), "Impossible de changer la durée de la partie. Elle doit être supérieure à 0.");
			} catch (NumberFormatException e) {
				EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), new Dimension(1000, 250), "Impossible de changer la durée de la partie. Elle doit être strictement supérieure à 0.");
			}
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

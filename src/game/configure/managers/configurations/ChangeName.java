package game.configure.managers.configurations;

import common.hud.EnigmaOptionPane;
import data.config.GameConfiguration;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer le nom de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class ChangeName implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(), "Nouveau nom de la partie :");
		if (!value.equals(EnigmaOptionPane.CANCEL)) {
			if (value.length() > 0 && value.length() < 50) {
				GameConfiguration.getInstance().setName(value);
				LaunchGameDisplay.getInstance().refreshCurrentDisplay();
			} else
				EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), new Dimension(1000, 250), "Impossible de changer le nom de la partie. Il est soit vide, soit supérieur à 50 caractères.");
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

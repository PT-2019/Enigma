package game.configure.managers.configurations;

import api.ui.CustomButton;
import common.hud.EnigmaOptionPane;
import data.config.GameConfiguration;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;

import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer le type de la partie (solo ou multijoueurs)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class ChangeType implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		String yes = "Oui";
		String no = "Non";
		CustomButton[] opt = new CustomButton[2];
		opt[0] = EnigmaOptionPane.getClassicButton(yes);
		opt[1] = EnigmaOptionPane.getClassicButton(no);
		String value = EnigmaOptionPane.showChoicesDialog(EnigmaGameLauncher.getInstance().getWindow(), "Partie multijoueurs?", opt);

		if (!value.equals(EnigmaOptionPane.CANCEL)) {
			GameConfiguration.getInstance().setMultiPlayer(value.equals(yes));
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

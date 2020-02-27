package game.configure.managers.configurations;

import api.ui.CustomButton;
import common.hud.EnigmaOptionPane;
import data.config.GameConfigurationDeprecated;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;

import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer le type de la partie (solo ou multijoueur)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 * @deprecated
 */
@Deprecated
public class ChangeType implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		String yes = "Oui";
		String no = "Non";
		CustomButton[] opt = new CustomButton[2];
		opt[0] = EnigmaOptionPane.getStyle().getButtonStyle(yes);
		opt[1] = EnigmaOptionPane.getStyle().getButtonStyle(no);
		String value = EnigmaOptionPane.showChoicesDialog(EnigmaGameLauncher.getInstance().getWindow(), "Partie multijoueur?", opt);

		if (!value.equals(EnigmaOptionPane.CANCEL)) {
			GameConfigurationDeprecated.getInstance().setMultiPlayer(value.equals(yes));
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

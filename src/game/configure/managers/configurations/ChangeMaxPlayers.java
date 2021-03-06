package game.configure.managers.configurations;

import api.ui.CustomButton;
import common.hud.EnigmaOptionPane;
import data.config.GameConfigurationDeprecated;
import game.EnigmaGameLauncher;
import game.configure.LaunchGameDisplay;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer le nombre de joueurs maximum de la partie
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
public class ChangeMaxPlayers implements ChangeConfiguration {

	/**
	 * Si on souhaite changer
	 */
	@Override
	public void onChange() {
		if (GameConfigurationDeprecated.getInstance().isMultiPlayer()) {
			CustomButton[] opt = new CustomButton[GameConfigurationDeprecated.MAX_PLAYERS - 1];
			for (int i = 0; i < opt.length; i++)
				opt[i] = EnigmaOptionPane.getStyle().getButtonStyle(Integer.toString(i + 2));
			String value = EnigmaOptionPane.showChoicesDialog(EnigmaGameLauncher.getInstance().getWindow(), "Nombre de joueurs :", opt);

			if (!value.equals(EnigmaOptionPane.CANCEL)) {
				int valueInt = Integer.parseInt(value);
				GameConfigurationDeprecated.getInstance().setMaxGamePlayers(valueInt);
				LaunchGameDisplay.getInstance().refreshCurrentDisplay();
			}
		} else
			EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), new Dimension(1000, 250), "Impossible de changer le nombre de joueurs quand la partie est solo.");
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

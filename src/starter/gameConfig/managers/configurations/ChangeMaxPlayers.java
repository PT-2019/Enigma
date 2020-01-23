package starter.gameConfig.managers.configurations;

import api.hud.components.CustomButton;
import editor.hud.EnigmaOptionPane;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Gère l'action de changer le nombre de joueurs maximum de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class ChangeMaxPlayers implements ChangeConfiguration {

    /**
     * Si on souhaite changer
     */
    @Override
    public void onChange() {
        if(GameConfiguration.getInstance().isMultiPlayer()) {
            CustomButton[] opt = new CustomButton[GameConfiguration.MAX_PLAYERS - 1];
            for (int i = 0; i < opt.length; i++)
                opt[i] = EnigmaOptionPane.getClassicButton(Integer.toString(i + 2));
            String value = EnigmaOptionPane.showChoicesDialog(EnigmaGameLauncher.getInstance().getWindow(), "Nombre de joueurs :", opt);

            if(!value.equals(EnigmaOptionPane.CANCEL)) {
                int valueInt = Integer.parseInt(value);
                GameConfiguration.getInstance().setMaxGamePlayers(valueInt);
                LaunchGameDisplay.getInstance().refreshCurrentDisplay();
            }
        }else
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

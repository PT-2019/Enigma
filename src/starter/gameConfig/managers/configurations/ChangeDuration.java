package starter.gameConfig.managers.configurations;

import editor.hud.EnigmaOptionPane;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ChangeDuration implements ChangeConfiguration {
    @Override
    public void onChange() {
        String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Nouvelle durée de la partie en minutes :");
        if(!value.equals(EnigmaOptionPane.CANCEL)) {
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

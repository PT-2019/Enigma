package starter.gameConfig.managers.configurations;

import editor.hud.EnigmaOptionPane;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ChangeDescription implements ChangeConfiguration {
    @Override
    public void onChange() {
        String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(), "Nouvelle description de la partie :");

        if (!value.equals(EnigmaOptionPane.CANCEL)) {
            if (value.length() > 0 && value.length() < 50) {
                GameConfiguration.getInstance().setDescription(value);
                LaunchGameDisplay.getInstance().refreshCurrentDisplay();
            } else
                EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), new Dimension(1000, 250), "Impossible de changer la description. Elle est soit vide, soit supérieure à 50 caractères.");
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

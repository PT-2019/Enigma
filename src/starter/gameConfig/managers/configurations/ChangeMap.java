package starter.gameConfig.managers.configurations;

import editor.hud.EnigmaOptionPane;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.MouseEvent;

public class ChangeMap implements ChangeConfiguration {
    @Override
    public void onChange() {
        String value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Choisir une nouvelle map :");

        if(!value.equals(EnigmaOptionPane.CANCEL)) {
            GameConfiguration.getInstance().setMap(value);
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

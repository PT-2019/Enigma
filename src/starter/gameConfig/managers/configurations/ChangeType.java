package starter.gameConfig.managers.configurations;

import api.hud.components.CustomButton;
import editor.hud.EnigmaOptionPane;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.MouseEvent;

public class ChangeType implements ChangeConfiguration {
    @Override
    public void onChange() {
        String yes = "Oui";
        String no = "Non";
        CustomButton[] opt = new CustomButton[2];
        opt[0] = EnigmaOptionPane.getClassicButton(yes);
        opt[1] = EnigmaOptionPane.getClassicButton(no);
        String value = EnigmaOptionPane.showChoicesDialog(EnigmaGameLauncher.getInstance().getWindow(),"Partie multijoueurs?",opt);

        if(!value.equals(EnigmaOptionPane.CANCEL)) {
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

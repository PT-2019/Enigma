package starter.gameConfig.managers.redirect;

import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO: créer une partie selon les valeurs de GameConfiguration
        LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.SELECT_GAME);
    }
}

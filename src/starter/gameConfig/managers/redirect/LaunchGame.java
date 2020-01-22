package starter.gameConfig.managers.redirect;

import game.GameConfiguration;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!GameConfiguration.getInstance().isMultiPlayer()){
            //TODO: lancer la bonne partie en fonction de GameConfiguration (solo)
        }else if(!GameConfiguration.getInstance().isFull()) {
            //TODO: lancer un serveur
            LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.WAIT_PLAYERS_LEADER);
        }else{
            //TODO: lancer la bonne partie en fonction de GameConfiguration (multi)
        }
    }
}

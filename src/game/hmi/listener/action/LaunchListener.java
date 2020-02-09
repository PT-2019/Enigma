package game.hmi.listener.action;

import data.config.GameConfiguration;
import game.hmi.MHIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(GameConfiguration.getInstance().isFull())
            MHIManager.getInstance().refresh(MHIManager.GAME_STATE);
    }
}

package starter.gameConfig.managers.redirect;

import api.hud.components.CustomTextArea;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGame implements ActionListener {
    private CustomTextArea textArea;

    public JoinGame(CustomTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("ip = "+textArea.getText());
        //TODO: se connecter et modifier GameConfiguration en conséquence
        LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.WAIT_PLAYERS);
    }
}

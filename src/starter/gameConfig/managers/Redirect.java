package starter.gameConfig.managers;

import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Redirect implements ActionListener {
    private String redirect;

    public Redirect(String redirect){
        this.redirect = redirect;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        LaunchGameDisplay.getInstance().showDisplay(this.redirect);
    }
}

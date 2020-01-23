package starter;

import editor.hud.EnigmaWindow;
import game.GameConfiguration;
import game.UserConfiguration;
import starter.gameConfig.LaunchGameDisplay;

import javax.swing.*;
import java.awt.*;

public class MonMainParceQueQuelquunAToutNique {
    public static void main(String[] args){
        EnigmaWindow w = new EnigmaWindow();
        w.getContentSpace().setLayout(new BorderLayout());
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setIfAskBeforeClosing(false);
        GameConfiguration.getInstance().setOwner(UserConfiguration.getInstance().getUser());
        w.getContentSpace().add(LaunchGameDisplay.getInstance().getPanel(), BorderLayout.CENTER);
        w.setVisible(true);
    }
}

package game.display;

import editor.hud.EnigmaPanel;

import javax.swing.*;
import java.awt.*;
public class LaunchGameDisplay {
    private final static LaunchGameDisplay instance = new LaunchGameDisplay();
    private EnigmaPanel panel;

    public static final String SELECT_GAME = "select game";
    public static final String JOIN_GAME = "join game";
    public static final String CREATE_GAME = "create game";
    public static final String WAIT_PLAYERS_LEADER = "wait players leader";
    public static final String WAIT_PLAYERS = "wait players";

    private LaunchGameDisplay(){
        this.panel = new EnigmaPanel();
        this.panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 5;
        gbc.weighty = 1;
        this.panel.add(ContentDisplayManager.getInstance().getPanel(),gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.panel.add(RightBarDisplayManager.getInstance().getPanel(),gbc);
        this.showDisplay(JOIN_GAME);
    }

    public void showDisplay(String displayName){
        ContentDisplayManager.getInstance().showDisplay(displayName);
        RightBarDisplayManager.getInstance().showDisplay(displayName);
    }

    public static LaunchGameDisplay getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}


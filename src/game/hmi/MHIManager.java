package game.hmi;

import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.language.GameLanguage;

import javax.swing.*;
import java.awt.*;

public class MHIManager {
    private final static MHIManager instance = new MHIManager();
    private EnigmaPanel content;

    private MHIManager(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;

        this.content = new EnigmaPanel();
        this.content.setLayout(new GridBagLayout());

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weighty = 1;
        this.content.add(NavBar.getInstance().getContent(),gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weighty = 1;
        this.content.add(ActionBar.getInstance().getContent(),gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 3;
        gbc.weighty = 100;
        this.content.add(ContentManager.getInstance().getContent(),gbc);

        ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
        NavBar.getInstance().refresh(NavBar.SOLO_STATE);
        ActionBar.getInstance().refresh(Content.NO_PRECISED_STATE);
    }

    public EnigmaPanel getContent(){
        return this.content;
    }

    public static MHIManager getInstance(){
        return instance;
    }

    public static void main(String[] args){
        GameLanguage.init();
        EnigmaWindow w = new EnigmaWindow();
        w.setIfAskBeforeClosing(false);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MHIManager mm = new MHIManager();
        w.getContentSpace().setLayout(new BorderLayout());
        w.getContentSpace().add(mm.getContent(),BorderLayout.CENTER);
        w.setVisible(true);
    }
}

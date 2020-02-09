package game.hmi;

import api.ui.CustomPanel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.language.GameLanguage;
import data.config.GameConfiguration;
import data.config.UserConfiguration;

import javax.swing.*;
import java.awt.*;

public class MHIManager extends Content {
    private final static MHIManager instance = new MHIManager();

    /**
     * Etats
     */
    public final static int GAME_STATE = 0;
    public final static int CONFIGURE_STATE = 1;

    private MHIManager(){
        super(new EnigmaPanel());

        this.initContent();
        this.refresh(CONFIGURE_STATE);
    }

    @Override
    protected void initContent() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;

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
    }

    @Override
    public void refresh(int state) {
        switch (state){
            case GAME_STATE:
                break;
            case CONFIGURE_STATE:
            default:
                break;
        }
    }

    public CustomPanel getContent(){
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

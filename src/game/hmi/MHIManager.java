package game.hmi;

import api.libgdx.utils.LoadGameLibgdxApplication;
import api.ui.CustomPanel;
import com.badlogic.gdx.Gdx;
import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.language.GameLanguage;
import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import data.config.Config;
import data.config.GameConfiguration;
import game.EnigmaGame;
import game.EnigmaGameLauncher;
import game.screens.GameScreen;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Gestionnaire le plus haut
 * Gère l'affichage soit du jeu, soit des autres affichages (choisir partie, créer, ...)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MHIManager extends Content {
    /**
     * Instance
     */
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

    /**
     * Initialise le contenu
     * Doit être normalement appelé qu'une fois, dans le constructeur
     */
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

    /**
     * Rafraichi l'affichage
     * @param state Etat
     */
    @Override
    public void refresh(int state) {
        switch (state){
            case GAME_STATE:
                String path = Config.MAP_FOLDER + GameConfiguration.getInstance().getMapName() + Config.MAP_EXTENSION;
                EnigmaGameLauncher.getInstance().setContentToGame(path);
                break;
            case CONFIGURE_STATE:
            default:
                break;
        }
    }

    /**
     * Obtenir l'instance
     * @return Instance
     */
    public static MHIManager getInstance(){
        return instance;
    }
}
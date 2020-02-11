package game.hmi.listener.action;

import common.hud.EnigmaOptionPane;
import data.NeedToBeTranslated;
import data.config.GameConfiguration;
import game.EnigmaGameLauncher;
import game.hmi.MHIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire du bouton "commencer"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class LaunchListener implements ActionListener {
    /**
     * Textes
     */
    public final static String NOT_ENOUGH_PLAYERS = NeedToBeTranslated.NOT_ENOUGH_PLAYERS;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(GameConfiguration.getInstance().isFull())
            MHIManager.getInstance().refresh(MHIManager.GAME_STATE);
        else
            EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),NOT_ENOUGH_PLAYERS);
    }
}

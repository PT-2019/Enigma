package game.hmi.listener.redirect;

import common.hud.EnigmaOptionPane;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiRedirectListener implements ActionListener {
    /**
     * Textes
     */
    public final static String QUIT_CONFIRMATION = "Quitter?";

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(ContentManager.getInstance().getState() != ContentManager.LOBBY_STATE)
            ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
        else{
            if(EnigmaOptionPane.showConfirmDialog(EnigmaGameLauncher.getInstance().getWindow(),QUIT_CONFIRMATION))
                ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
        }
    }
}

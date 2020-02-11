package game.hmi.listener.action;

import common.hud.EnigmaTextField;
import common.utils.Logger;
import game.hmi.ActionBar;
import game.hmi.ContentManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire du bouton "rejoindre"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class JoinListener implements ActionListener {
    /**
     * Pour entre l'adresse IP
     */
    private EnigmaTextField field;

    /**
     * @param field Composant où l'adresse IP est rentrée
     */
    public JoinListener(EnigmaTextField field){
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO : (lorsque multi disponible)
        //Verifier la syntaxe
        //Tenter de se connecter à l'addresse ip
        //Récupérer les infos de la partie et les rentrer dans GameConfiguration
        Logger.printDebug("JoinListener.java"," A tester : " + field.getText());
        ContentManager.getInstance().refresh(ContentManager.LOBBY_STATE);
        ActionBar.getInstance().refresh(ActionBar.QUIT_STATE);
    }
}

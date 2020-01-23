package starter.gameConfig.managers.redirect;

import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gère l'action de créer une partie après l'avoir configurée
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class CreateGameRedirect implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO: créer une partie selon les valeurs de GameConfiguration
        LaunchGameDisplay.getInstance().showDisplay(LaunchGameDisplay.SELECT_GAME);
    }
}

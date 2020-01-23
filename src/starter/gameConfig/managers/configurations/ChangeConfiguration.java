package starter.gameConfig.managers.configurations;

import java.awt.event.MouseListener;

/**
 * Gère l'action de changer une configuration de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public interface ChangeConfiguration extends MouseListener {
    /**
     * Si on souhaite changer
     */
    void onChange();
}

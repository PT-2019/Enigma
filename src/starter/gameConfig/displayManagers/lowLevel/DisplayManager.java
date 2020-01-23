package starter.gameConfig.displayManagers.lowLevel;

import editor.hud.EnigmaPanel;

/**
 * Gère les affichages
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public interface DisplayManager {
    /**
     * Rafraichir le contenu
     */
    void refreshContent();

    /**
     * Rafraichir la barre de menu
     */
    void refreshRightBar();

    /**
     * Tout rafraichir
     */
    void refreshAll();

    /**
     * Obtenir le contenu
     * @return Le contenu
     */
    EnigmaPanel getContent();

    /**
     * Obtenir la barre de menu
     * @return La barre de menu
     */
    EnigmaPanel getRightBar();
}

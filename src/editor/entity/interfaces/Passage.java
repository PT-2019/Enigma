package editor.entity.interfaces;

import editor.map.Room;

/**
 * Définie une entité comme traversable pour faire le lien entre deux pièces
 */
public interface Passage {
    /**
     * Obtenir la première pièce
     * @return La pièce, null sinon
     */

    Room getRoom1();

    /**
     * Obtenir la seconde pièce
     * @return La pièce, null sinon
     */
    Room getRoom2();
}
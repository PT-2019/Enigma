package api.entity.types;


import game.entity.item.Room;

/**
 * Définie une entité comme traversable pour faire le lien entre deux pièces
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public interface Passage {
	/**
	 * Obtenir la première pièce
	 *
	 * @return La pièce, null sinon
	 * @since 2.0
	 */

	Room getRoom1();

	/**
	 * Obtenir la seconde pièce
	 *
	 * @return La pièce, null sinon
	 * @since 2.0
	 */
	Room getRoom2();
}
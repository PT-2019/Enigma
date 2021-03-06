package common.entities.types;

import common.map.model.Room;

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
@Deprecated
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
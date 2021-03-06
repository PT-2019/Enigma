package common.entities.types;

import common.entities.players.EntityGame;

/**
 * Définie une entité comme mortelle donc vivante
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 2.0
 */
public interface Living extends NeedContainerManager, EntityGame {

	/**
	 * Obtenir les points de vie de l'entité
	 *
	 * @return Les points de vie
	 * @since 2.0
	 */
	int getHP();

	/**
	 * Retourne le nom de l'entité
	 *
	 * @return nom
	 */
	String getName();

	/**
	 * Définit le nom de l'entité
	 *
	 * @param name nom
	 */
	void setName(String name);
}


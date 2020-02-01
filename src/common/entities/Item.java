package common.entities;

import common.entities.players.Player;
import common.entities.types.EnigmaContainer;
import common.entities.types.NeedContainerManager;

/**
 * Définie une entité comme un item
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * apiNote suppression des méthode de IDInterface pour lisibilité et de Entity et de EnigmaContainer
 * @see EnigmaContainer
 * @see Entity
 * @since 2.0
 */
public interface Item extends EnigmaContainer, Entity, NeedContainerManager {

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	void interactsWith(Player p);

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	String toString();

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	String toLongString();

	@Override
	default float getGameObjectWidth() {
		return 1f;
	}

	@Override
	default float getGameObjectHeight() {
		return 1f;
	}
}

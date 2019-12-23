package api.entity.interfaces;

import editor.entity.player.Player;

/**
 * Définie une entité comme un item
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @see api.entity.interfaces.EnigmaContainer
 * @see Entity
 * @since 2.0
 *
 * @apiNote suppression des méthode de IDInterface pour lisibilité et de Entity et de EnigmaContainer
 */
public interface Item extends EnigmaContainer, Entity {

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
	default int getWidth(){ return 1; }

	@Override
	default int getHeight(){ return 1; }
}

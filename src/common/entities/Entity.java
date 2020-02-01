package common.entities;

import common.entities.players.Player;
import common.utils.textures.Texture;

/**
 * Définie une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * apiNote suppression des méthode de IDInterface pour lisibilité
 * @since 2.0
 */
public interface Entity extends GameObject {

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 * @since 2.0
	 */
	void interactsWith(Player p);

	/**
	 * Obtenir la texture de l'objet
	 *
	 * @return Texture de l'objet, null sinon
	 * @since 2.0
	 * @deprecated 4.0 aucune utilitée
	 */
	@Deprecated
	default Texture getTexture() {
		return null;
	}

	/**
	 * Aucune utilitée
	 *
	 * @param t texture a associer
	 * @since 2.0
	 * @deprecated 4.0 aucune utilitée
	 */
	@Deprecated
	default void setTexture(Texture t) {
	}

	/**
	 * Affiche un dialogue avec l'objet
	 *
	 * @since 2.0
	 * @deprecated 4.0 car pas utilisée
	 */
	@Deprecated
	default void showDialog() {
	}
}

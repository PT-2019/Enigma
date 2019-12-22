package api.entity.interfaces;

import editor.entity.player.Player;
import editor.utils.textures.Texture;

/**
 * Définie une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public interface Entity extends IDInterface {

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	void interactsWith(Player p);

	/**
	 * Obtenir la texture de l'objet
	 *
	 * @return Texture de l'objet, null sinon
	 */
	Texture getTexture();

	void setTexture(Texture t);

	/**
	 * Affiche un dialogue avec l'objet
	 */
	void showDialog();

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	int getID();

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	void setID(int id);
}

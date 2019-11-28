package editor.entities.interfaces;

import editor.entities.Player;
import editor.textures.Texture;

/**
 * Définie une entité
 */
public interface Entity{

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	void interactsWith(Player p);

	/**
	 * Obtenir la texture de l'objet
	 * @return Texture de l'objet, null sinon
	 */
	Texture getTexture();

	/**
	 * Affiche un dialogue avec l'objet
	 */
	void showDialog();
}

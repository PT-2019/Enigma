package api.entity.interfaces;

import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.utils.textures.Texture;

import java.util.Iterator;

/**
 * Définie une entité comme un item
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.1
 * @see api.entity.interfaces.EnigmaContainer
 * @see api.entity.interfaces.Entity
 * @since 2.0
 */
public interface Item extends EnigmaContainer, Entity {

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

	/**
	 * Affiche un dialogue avec l'objet
	 */
	void showDialog();

	/**
	 * Ajouter une énigme
	 *
	 * @param e Enigme à ajouter
	 * @throws IllegalArgumentException si l'énigme existe déjà
	 */
	void addEnigma(Enigma e);

	/**
	 * Permet de supprimer une énigme
	 *
	 * @param e Enigme à supprimer
	 */
	void removeEnigma(Enigma e);

	/**
	 * Obtenir toutes les énigmes
	 *
	 * @return Iterator des énigmes
	 */
	Iterator<Enigma> getAllEnigmas();

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

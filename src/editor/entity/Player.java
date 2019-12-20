package editor.entity;

import editor.entity.interfaces.Entity;
import editor.entity.interfaces.Living;
import editor.textures.Texture;

/**
 * Définie un personnage contrôlable : un joueur
 *
 * @version 2.0
 * @see editor.entity.interfaces.Entity
 * @see editor.entity.interfaces.Living
 */
public class Player implements Entity, Living {

	/**
	 * Points de vie maximaux du joueur
	 */
	public final int MAX_PLAYER_PV = 100;
	/**
	 * Point de vie du joueur
	 */
	private int pv;
	/**
	 * Dialogue de l'objet
	 */
	private String dialog;

	/**
	 * Texture de l'objet
	 */
	private Texture texture;

	/**
	 * ID
	 */
	private int id;

	public Player() {
		this.pv = MAX_PLAYER_PV;
	}

	/**
	 * @param id ID
	 */
	public Player(int id) {
		this.pv = MAX_PLAYER_PV;
		this.id = id;
	}

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	@Override
	public void interactsWith(Player p) {
	}

	/**
	 * Obtenir la texture de l'objet
	 *
	 * @return Texture de l'objet, null sinon
	 */
	@Override
	public Texture getTexture() {
		return null;
	}

	@Override
	public void setTexture(Texture t) {
		texture = t;
	}

	/**
	 * Affiche un dialogue avec l'objet
	 */
	@Override
	public void showDialog() {
	}

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	@Override
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Obtenir les points de vie de l'entité
	 *
	 * @return Les points de vie
	 */
	@Override
	public int getHP() {
		return this.pv;
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Player  : ID = " + this.id + ", dialog = " + this.dialog + ", pv = " + this.pv + ", texture = " + this.texture + "]";
	}
}
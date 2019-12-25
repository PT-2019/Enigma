package game.entity.item;

import api.entity.AbstractItem;
import api.entity.Item;
import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.utils.textures.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @version 2.2
 * @see Item
 */
@Deprecated
public class Floor extends AbstractItem {

	/**
	 * Enigmes données à l'objet
	 */
	private ArrayList<Enigma> enigmas;

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

	public Floor() {
		this.enigmas = new ArrayList<Enigma>();
		this.id = -1;
	}

	/**
	 * @param id ID
	 */
	public Floor(int id) {
		this.enigmas = new ArrayList<Enigma>();
		this.id = id;
	}

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	@Override
	public void interactsWith(Player p) {
		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}

	/**
	 * Ajouter une énigme
	 *
	 * @param e Enigme à ajouter
	 * @throws IllegalArgumentException si l'énigme existe déjà
	 */
	@Override
	public void addEnigma(Enigma e) {
		if (this.enigmas.contains(e)) throw new IllegalArgumentException("Cette énigme existe déjà dans la liste");
		this.enigmas.add(e);
	}

	/**
	 * Permet de supprimer une énigme
	 *
	 * @param e Enigme à supprimer
	 */
	@Override
	public void removeEnigma(Enigma e) {
		this.enigmas.remove(e);
	}

	/**
	 * Obtenir toutes les énigmes
	 *
	 * @return Iterator des énigmes
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Enigma> getAllEnigmas() {
		ArrayList<Enigma> e = (ArrayList<Enigma>) this.enigmas.clone();
		return e.iterator();
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
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Floor  : ID = " + this.id + ", dialog = " + this.dialog + ", texture = " + this.texture + "]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public String toLongString() {
		StringBuilder s = new StringBuilder("[Floor  : ID = " + this.id + ", dialog = " + this.dialog + ", texture = " + this.texture + ", enigmas = {");
		int size = this.enigmas.size() - 1;
		int i = 0;
		for (Enigma e : this.enigmas) {
			s.append(e.toLongString());
			if (i < size) s.append(", ");
			i++;
		}
		s.append("}]");
		return s.toString();
	}
}

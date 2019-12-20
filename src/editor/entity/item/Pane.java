package editor.entity.item;

import editor.enigma.Enigma;
import editor.entity.Player;
import editor.entity.interfaces.Content;
import editor.entity.interfaces.Item;
import editor.textures.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @version 2.2
 * @see editor.entity.interfaces.Item
 * @see editor.entity.interfaces.Content
 */
public class Pane implements Content, Item {

	/**
	 * Enigmes données à l'objet
	 */
	private ArrayList<Enigma> enigmas;

	/**
	 * Contenu de l'objet
	 */
	private String content;

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

	public Pane() {
		this.enigmas = new ArrayList<Enigma>();
		this.id = -1;
	}

	/**
	 * @param id ID
	 */
	public Pane(int id) {
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
	 * Ajoute un contenu à l'objet
	 *
	 * @param content Contenu à ajouter
	 */
	@Override
	public void addContent(String content) {
		this.content = content;
	}

	/**
	 * Obtenir le contenu
	 *
	 * @return le contenu, le contenu peut être vide
	 */
	@Override
	public String getContent() {
		return this.content;
	}

	/**
	 * Obtenir la texture de l'objet
	 *
	 * @return Texture de l'objet, null sinon
	 */
	@Override
	public Texture getTexture() {
		return this.texture;
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
		return "[Pane  : ID = " + this.id + ", dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture + "]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public String toLongString() {
		StringBuilder s = new StringBuilder("[Pane  : ID = " + this.id + ", dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture + ", enigmas = {");
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

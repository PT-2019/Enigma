package editor.enigma.condition;

import editor.datas.Attributes;
import editor.entity.IDFactory;
import editor.entity.Player;
import editor.entity.interfaces.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Une condition à pour but d'être satisfaite par une action des joueurs. Elle diffère fonction du type de condition
 * Elle est utilisée dans les {@link editor.enigma.Enigma énigmes} pour déterminer si elles ont été résolues
 *
 * @version 2.1
 * @see editor.enigma.Enigma
 */
public abstract class Condition {

	/**
	 * Entité dont l'état détermine si la condition est satisfaite ou nom
	 */
	protected Entity entity;

	/**
	 * @param e Entité concernée par la condition
	 */
	public Condition(Entity e) {
		this.entity = e;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Condition(Map<String, Object> attributes) {
		IDFactory idFactory = IDFactory.getInstance();
		if (attributes.containsKey(Attributes.ENTITY))
			this.entity = (Entity) idFactory.getObject(Integer.parseInt((String) attributes.get(Attributes.ENTITY)));
		else throw new IllegalArgumentException("Attribut \"entity\" abscent");
	}

	/**
	 * Vérifie que la condition est satisfaite
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 * @return true si la condtion est satisfaite, false sinon
	 */
	public abstract boolean verify(Player p);

	/**
	 * Obtenir l'entité consernée par la condition
	 *
	 * @return L'entité, null sinon
	 */
	public Entity getEntity() {
		return this.entity;
	}

	/**
	 * Indiquer l'entité consernée par la condition
	 *
	 * @param e Entité
	 */
	public void setEntity(Entity e) {
		this.entity = e;
	}

	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see editor.datas.Attributes
	 */
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = new HashMap<>();
		object.put(Attributes.PATH, this.getClass().getName());
		object.put(Attributes.ENTITY, this.entity.getID() + "");
		return object;
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public abstract String toLongString();
}

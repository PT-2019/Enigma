package editor.enigma.operation;

import api.entity.Entity;
import api.enums.Attributes;
import editor.entity.IDFactory;
import editor.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Un opération définie une action à réaliser sur une entité
 * Elle est utilisée dans les {@link editor.enigma.Enigma énigmes} pour faire des actions après que les {@link editor.enigma.condition.Condition conditions} est été satisfaites
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see editor.enigma.Enigma
 * @since 2.0
 */
public abstract class Operation {

	/**
	 * Entité concernée par l'opération
	 */
	protected Entity entity;

	/**
	 * @param e Entité concernée par l'opération
	 */
	public Operation(Entity e) {
		this.entity = e;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Operation(Map<String, Object> attributes) {
		IDFactory idFactory = IDFactory.getInstance();
		if (attributes.containsKey(Attributes.ENTITY))
			this.entity = (Entity) idFactory.getObject(Integer.parseInt((String) attributes.get(Attributes.ENTITY)));
		else throw new IllegalArgumentException("Attribut \"entity\" abscent");
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	public abstract void doOperation(Player p);

	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see api.enums.Attributes
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

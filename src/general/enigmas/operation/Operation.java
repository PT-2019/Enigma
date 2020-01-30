package general.enigmas.operation;

import general.entities.Entity;
import general.entities.players.Player;
import general.save.enigmas.EnigmaAttributes;
import general.utils.IDFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Un opération définie une action à réaliser sur une entité
 * Elle est utilisée dans les {@link general.enigmas.Enigma énigmes} pour faire des actions après que les
 * {@link general.enigmas.condition.Condition conditions} est été satisfaites
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see general.enigmas.Enigma
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
		if (attributes.containsKey(EnigmaAttributes.ENTITY))
			this.entity = (Entity) idFactory.getObject(Integer.parseInt((String) attributes.get(EnigmaAttributes.ENTITY)));
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
	 * @see EnigmaAttributes
	 */
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = new HashMap<>();
		object.put(EnigmaAttributes.PATH, this.getClass().getName());
		object.put(EnigmaAttributes.ENTITY, this.entity.getID() + "");
		return object;
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public abstract String toLongString();
}

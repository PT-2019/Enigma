package editor.enigma.operation;

import api.entity.Entity;
import api.enums.Attributes;
import editor.entity.IDFactory;
import editor.entity.Player;
import game.EnigmaGame;
import game.Louka.GameMap;

import java.util.ArrayList;
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
 * @version 5.0
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
		ArrayList<String> attr = new ArrayList<>();
		attr.add(Attributes.ENTITY);

		for(String a : attr){
			if(!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" abscent");

			Object get = attributes.get(a);

			switch(a){
				case Attributes.ENTITY:
					this.entity = (Entity) EnigmaGame.getInstance().getCurrentMap().getObjects().getObjectByID(Integer.parseInt((String) get));
					break;
			}
		}
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
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public abstract String toLongString();
}

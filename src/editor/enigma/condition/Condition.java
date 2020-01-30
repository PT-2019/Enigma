package editor.enigma.condition;

import api.entity.Entity;
import api.enums.Attributes;
import editor.entity.Player;
import game.EnigmaGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Une condition à pour but d'être satisfaite par une action des joueurs. Elle diffère fonction du type de condition
 * Elle est utilisée dans les {@link editor.enigma.Enigma énigmes} pour déterminer si elles ont été résolues
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see editor.enigma.Enigma
 * @since 2.0
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
		ArrayList<String> attr = new ArrayList<>();
		attr.add(Attributes.ENTITY);

		for(String a : attr){
			if(!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" abscent");

			Object get = attributes.get(a);

			switch(a){
				case Attributes.ENTITY:
					this.entity = (Entity) EnigmaGame.getInstance().getCurrentMap().getEntities().getObjectByID(Integer.parseInt((String) get));
					break;
			}
		}
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

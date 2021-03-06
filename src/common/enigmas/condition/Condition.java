package common.enigmas.condition;

import common.enigmas.EnigmaElementReadablePrint;
import common.enigmas.reporting.EnigmaReport;
import common.entities.GameObject;
import common.entities.players.Player;
import common.save.enigmas.EnigmaAttributes;
import game.EnigmaGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Une condition à pour but d'être satisfaite par une action des joueurs. Elle diffère fonction du type de condition
 * Elle est utilisée dans les {@link common.enigmas.Enigma énigmes} pour déterminer si elles ont été résolues
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see common.enigmas.Enigma
 * @since 2.0
 */
public abstract class Condition implements EnigmaElementReadablePrint {

	/**
	 * Entité dont l'état détermine si la condition est satisfaite ou nom
	 */
	protected GameObject entity;

	/**
	 * @param e Entité concernée par la condition
	 */
	public Condition(GameObject e) {
		this.entity = e;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Condition(Map<String, Object> attributes) {
		ArrayList<String> attr = new ArrayList<>();
		attr.add(EnigmaAttributes.ENTITY);

		for (String a : attr) {
			if (!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" absent");

			Object get = attributes.get(a);

			switch (a) {
				case EnigmaAttributes.ENTITY:
					this.entity = EnigmaGame.getCurrentScreen().getMap().getEntities().getObjectByID(Integer.parseInt((String) get));
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
	public abstract EnigmaReport verify(Player p);

	/**
	 * Obtenir l'entité consernée par la condition
	 *
	 * @return L'entité, null sinon
	 */
	public GameObject getEntity() {
		return this.entity;
	}

	/**
	 * Indiquer l'entité consernée par la condition
	 *
	 * @param e Entité
	 */
	public void setEntity(GameObject e) {
		this.entity = e;
	}

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

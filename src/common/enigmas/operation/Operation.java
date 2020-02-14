package common.enigmas.operation;

import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
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
 * Un opération définie une action à réaliser sur une entité
 * Elle est utilisée dans les {@link common.enigmas.Enigma énigmes} pour faire des actions après que les
 * {@link common.enigmas.condition.Condition conditions} est été satisfaites
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see common.enigmas.Enigma
 * @since 2.0
 */
public abstract class Operation implements EnigmaElementReadablePrint {

	/**
	 * Entité concernée par l'opération
	 */
	protected GameObject entity;

	/**
	 * @param e Entité concernée par l'opération
	 */
	public Operation(GameObject e) {
		this.entity = e;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Operation(Map<String, Object> attributes) {
		ArrayList<String> attr = new ArrayList<>();
		attr.add(EnigmaAttributes.ENTITY);

		for (String a : attr) {
			if (!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" absent");

			Object get = attributes.get(a);

			if (EnigmaAttributes.ENTITY.equals(a)) {
				this.entity = EnigmaGame.getCurrentScreen().getMap()
						.getEntities().getObjectByID(Integer.parseInt((String) get));
			}
		}
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Deprecated
	public void doOperation(Player p){run(p);}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 *
	 * @return bilan de l'exécution de l'opération
	 */
	public abstract EnigmaReport run(Player p);

	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see EnigmaAttributes
	 */
	public HashMap<String, Object> objectToMap() {
		System.out.println(this);
		HashMap<String, Object> object = new HashMap<>();
		object.put(EnigmaAttributes.PATH, this.getClass().getName());
		object.put(EnigmaAttributes.ENTITY, this.entity.getID() + "");
		return object;
	}

	/**
	 * Obtenir l'entité concernée par la condition
	 *
	 * @return L'entité, null sinon
	 */
	public GameObject getEntity() {
		return this.entity;
	}

	/**
	 * Indiquer l'entité concernée par la condition
	 *
	 * @param e Entité
	 */
	public void setEntity(GameObject e) {
		this.entity = e;
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	public abstract String toLongString();
}

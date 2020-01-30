package general.enigmas.operation;

import general.entities.Item;
import general.entities.players.Player;

import java.util.Map;

/**
 * Donne un objet au joueur ayant entrainé le déclenchement de cette opération
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see Operation
 * @since 2.0
 */
public class Give extends Operation {

	/**
	 * @param i Item concerné par l'opération
	 */
	public Give(Item i) {
		super(i);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Give(Map<String, Object> attributes) {
		super(attributes);
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	public void doOperation(Player p) {
		Item i = (Item) this.entity;
		//Donner i à p
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Give]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[Give  : entity = " + this.entity + "]";
	}
}

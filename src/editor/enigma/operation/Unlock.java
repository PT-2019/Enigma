package editor.enigma.operation;

import api.entity.Item;
import api.entity.types.Lockable;
import editor.entity.Player;

import java.util.Map;

/**
 * Déverrouille un objet verrouillable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see editor.enigma.operation.Operation
 * @since 2.0
 */
public class Unlock extends Operation {

	/**
	 * @param l Objet verrouillabe concerné par l'opération
	 */
	public Unlock(Lockable l) {
		super((Item) l);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Unlock(Map<String, Object> attributes) {
		super(attributes);
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	public void doOperation(Player p) {
		Lockable l = (Lockable) this.entity;
		l.unlock();
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return toLongString();
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[Unlock  : entity = " + this.entity + "]";
	}
}

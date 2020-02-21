package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.Item;
import common.entities.players.Player;
import common.entities.types.Lockable;
import common.language.EnigmaField;

import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Déverrouille un objet verrouillable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see Operation
 * @since 2.0
 */
public class Unlock extends Operation {

	/**
	 * @param l Objet verrouillable concerné par l'opération
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
	public EnigmaReport run(Player p) {
		if (!this.fulfilled) {
			Lockable l = (Lockable) this.entity;
			l.unlock();
			this.fulfilled = true;
		}
		return new EnigmaReport(OperationReport.UNLOCKED, true, this.entity); //opération ok
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

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.UNLOCK) + ": " + this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

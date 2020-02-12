package common.enigmas.condition;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.ConditionReport;
import common.entities.players.Player;
import common.entities.types.Activatable;
import common.language.EnigmaField;

import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Vérifie qu'un objet activable est activé
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see common.enigmas.condition.Condition
 * @since 2.0
 */
public class Activated extends Condition {

	/**
	 * @param a Objet activable concerné par la condition
	 */
	public Activated(Activatable a) {
		super(a);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Activated(Map<String, Object> attributes) {
		super(attributes);
	}

	/**
	 * Vérifie que la condition est satisfaite
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 * @return true si la condtion est satisfaite, false sinon
	 */
	@Override
	public EnigmaReport verify(Player p) {
		Activatable a = (Activatable) this.entity;
		return new EnigmaReport(ConditionReport.DONE, a.isActivated());
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Activated]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[Activated  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.ACTIVATED) + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

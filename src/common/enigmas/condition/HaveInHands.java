package common.enigmas.condition;

import common.enigmas.Enigma;
import common.enigmas.reporting.ConditionReport;
import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.Item;
import common.entities.players.Player;
import common.language.EnigmaField;

import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Vérifie que le joueur tien un item défini dans ses mains
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see Condition
 * @since 2.0
 */
public class HaveInHands extends Condition {

	/**
	 * @param i Item concerné par la condition
	 */
	public HaveInHands(Item i) {
		super(i);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public HaveInHands(Map<String, Object> attributes) {
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
		Item i = (Item) this.entity;
		if (p.holdItemInRightHand() && p.getItemInRightHand().equals(i)) {
			return new EnigmaReport(ConditionReport.FOUND_IN_HANDS, true);
		} else if (p.holdItemInLeftHand() && p.getItemInLeftHand().equals(i))
			return new EnigmaReport(ConditionReport.FOUND_IN_HANDS, true);

		return new EnigmaReport(ConditionReport.NOT_IN_HANDS, false);
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[HaveInHands]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[HaveInHands  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.HAVE_IN_HANDS) + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}
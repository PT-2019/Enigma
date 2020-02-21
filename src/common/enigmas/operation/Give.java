package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.Item;
import common.entities.players.Player;
import common.language.EnigmaField;

import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Donne un objet au joueur ayant entrainé le déclenchement de cette opération
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
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

	@Override
	public EnigmaReport run(Player p) {
		if (!this.fulfilled) {
			Item i = (Item) this.entity;
			if (!p.getInventory().isFull())
				p.addItem(i);
			else if (!p.holdItemInRightHand())
				p.setItemInRightHand(i);
			else if (!p.holdItemInLeftHand())
				p.setItemInLeftHand(i);
			else
				return new EnigmaReport(OperationReport.INVENTORY_FULL, false);
		}

		this.fulfilled = true;
		return new EnigmaReport(OperationReport.GIVEN, true); //opération ok
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

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.GIVE) + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

package common.enigmas.condition;

import common.enigmas.reporting.ConditionReport;
import common.enigmas.reporting.EnigmaReport;
import common.entities.players.Player;
import common.entities.special.Room;
import data.NeedToBeTranslated;

import java.util.Map;

/**
 * Une salle doit être découverte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 05/02/2020
 * @since 6.0 05/02/2020
 */
public class RoomDiscovered extends Condition {
	public RoomDiscovered(Room room) {
		super(room);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public RoomDiscovered(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public EnigmaReport verify(Player p) {
		Room r = (Room) this.entity;
		if (r.isDiscovered())
			return new EnigmaReport(ConditionReport.DISCOVERED, true);

		return new EnigmaReport(ConditionReport.UNDISCOVERED, false);
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[RoomDiscovered]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[RoomDiscovered  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.ROOM_DISCOVERED_DESC + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

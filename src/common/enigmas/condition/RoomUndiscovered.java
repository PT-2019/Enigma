package common.enigmas.condition;

import common.enigmas.reporting.ConditionReport;
import common.enigmas.reporting.EnigmaReport;
import common.entities.players.Player;
import common.entities.special.Room;
import data.NeedToBeTranslated;

import java.util.Map;

/**
 * Une salle ne doit pas être découverte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 05/02/2020
 * @since 6.0 05/02/2020
 */
public class RoomUndiscovered extends Condition {
	public RoomUndiscovered(Room room) {
		super(room);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public RoomUndiscovered(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public EnigmaReport verify(Player p) {
		Room r = (Room) this.entity;
		if (!r.isDiscovered())
			return new EnigmaReport(ConditionReport.UNDISCOVERED, true);

		return new EnigmaReport(ConditionReport.DISCOVERED, false);
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[RoomUndiscovered]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[RoomUndiscovered  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.ROOM_UNDISCOVERED_DESC + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

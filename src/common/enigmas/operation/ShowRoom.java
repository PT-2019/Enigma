package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.players.Player;
import common.entities.special.Room;
import data.NeedToBeTranslated;

import java.util.Map;

/**
 * Cache une pièce
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see Operation
 * @since 2.0
 */
public class ShowRoom extends Operation {

	public ShowRoom(Room object) {
		super(object);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public ShowRoom(Map<String, Object> attributes) {
		super(attributes);
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	@Deprecated
	public void doOperation(Player p) {
		this.run(p);
	}


	@Override
	public EnigmaReport run(Player p) {
		Room room = (Room) this.entity;
		room.setShowed(false);
		return new EnigmaReport(OperationReport.DONE, true); //opération ok
	}

	@Override
	public String toString() {
		return "[ShowRoom]";
	}

	@Override
	public String toLongString() {
		return "[ShowRoom  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.SHOW_ROOM_DESC + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}


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
public class HideRoom extends Operation {

	public HideRoom(Room object) {
		super(object);
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public HideRoom(Map<String, Object> attributes) {
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
		if (!this.fulfilled) {
			Room room = (Room) this.entity;
			room.setShowed(false);
			this.fulfilled = true;
		}
		return new EnigmaReport(OperationReport.ROOM_HIDDEN, true, this.entity); //opération ok
	}

	@Override
	public String toString() {
		return "[HideRoom]";
	}

	@Override
	public String toLongString() {
		return "[HideRoom  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.HIDE_ROOM_DESC + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

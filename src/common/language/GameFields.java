package common.language;

/**
 * Les champs du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public enum GameFields implements Field {
	ROOM, CHEST, PANE, SWITCH, BUTTON, KEY, FLOOR,
	BOOK, DOOR, PRESSURE_PLATE, PLAYER, NPC, MONSTER, LIBRARY;

	@Override
	public String toString() {
		String name = this.name();
		if (name.contains("_")) {
			name = name.replaceAll("_", "");
		}
		return name.toLowerCase();
	}
}

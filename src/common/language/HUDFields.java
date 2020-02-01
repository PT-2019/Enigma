package common.language;

/**
 * Les champs du HUD
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public enum HUDFields implements Field {
	PLAY, EDITOR, RUNNING,
	CREATE, OPEN, SAVE, SAVE_AS, UNDO, REDO, BRUSH, ERASER, MOVE,
	FILE, EDIT, RUN, HELP,
	EXPORT, DOC, SUPPORT, START, STOP,
	ROOMS, DECORS, OBJECTS, CHARACTERS, ACTIONS,
	LAYER, FIRST_LAYER, OK, CANCEL, LEAVE, GAME_EXIT;

	@Override
	public String toString() {
		String name = this.name();
		if (name.contains("_")) {
			name = name.replaceAll("_", "");
		}
		return name.toLowerCase();
	}
}

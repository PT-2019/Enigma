package common.language;

import api.utils.Utility;

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
	EXPORT, IMPORT, DOC, SUPPORT, START, STOP,
	ROOMS, DECORS, OBJECTS, CHARACTERS, ACTIONS,
	LAYER, FIRST_LAYER, OK, CANCEL, LEAVE, GAME_EXIT;

    @Override
	public String toString() {
		return Utility.snakeCaseToCamelCase(this.name());
	}
}

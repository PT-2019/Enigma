package editor.utils.lang.fields;

public enum HUDFields implements Field {
	PLAY, EDITOR, RUNNING,
	CREATE, OPEN, SAVE, UNDO, REDO, BRUSH, ERASER, MOVE,
	FILE, EDIT, RUN, HELP,
	EXPORT, DOC, SUPPORT, START, STOP,
	ROOMS, DECORS, OBJECTS, CHARACTERS, ACTIONS,
	LAYER, FIRST_LAYER, OK, CANCEL, LEAVE;

	@Override
	public String toString() {
		String name = this.name();
		if (name.contains("_")) {
			name = name.replaceAll("_", "");
		}
		return name.toLowerCase();
	}
}

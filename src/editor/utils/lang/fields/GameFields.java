package editor.utils.lang.fields;

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

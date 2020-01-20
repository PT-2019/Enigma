package editor.utils.lang;

public enum  Field {
    PLAY, EDITOR, RUNNING,
    CREATE, OPEN, SAVE, UNDO, REDO, BRUSH, ERASER, MOVE,
    FILE, EDIT, RUN, HELP,
    EXPORT, DOC, SUPPORT, START, STOP,
    ROOM, DECORS, OBJECTS, CHARACTERS, ACTIONS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

package editor.utils.lang;

@Deprecated
public class LanguageSerialization implements EnigmaLanguage {

	private String play, editor, running;

	private String create, open, save, undo, redo, brush, eraser, move;

	LanguageSerialization() {
	}

	@Override
	public String getEditorButton() {
		return editor;
	}

	@Override
	public String getPlayButton() {
		return play;
	}

	@Override
	public String getRunningMessage() {
		return running;
	}

	@Override
	public String getCreate() {
		return create;
	}

	@Override
	public String getOpen() {
		return open;
	}

	@Override
	public String getSave() {
		return save;
	}

	@Override
	public String getUndo() {
		return undo;
	}

	@Override
	public String getRedo() {
		return redo;
	}

	@Override
	public String getBrush() {
		return brush;
	}

	@Override
	public String getEraser() {
		return eraser;
	}

	@Override
	public String getMove() {
		return move;
	}
}

package editor;

import editor.api.Application;
import editor.utils.Window;

/**
 * Lanceur de l'éditeur d'escape game
 */
public class EditorLauncher implements Application {

	/** la fenêtre dans laquelle il est lancé */
	private Window window;

	/**
	 * Construit l'éditeur d'escape game
	 */
	public EditorLauncher() {
		this.window = new Window("Editeur de maps");
	}

	@Override
	public void start() {
		//on démarre l'application
		this.window.setVisible(true);
	}
}
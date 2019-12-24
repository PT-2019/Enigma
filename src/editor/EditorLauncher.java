package editor;

import editor.api.Application;
import editor.window.Window;

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
		this.window = new Window();
	}

	@Override
	public void start() {
		//on démarre l'application
		this.window.addWindowListener(new AppClosingManager());
		this.window.setSize(Window.HALF_SCREEN_SIZE);
		this.window.setVisible(true);
	}
}
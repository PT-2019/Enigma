package editor;

import editor.api.Application;
import editor.utils.Window;

/**
 * Lanceur de l'éditeur d'escape game
 */
public class EditorLuncher implements Application {

	/** la fenêtre dans laquelle il est lancé **/
	private Window window;

	/**
	 * Construit l'éditeur d'escape game
	 *
	 * @param width largeur de la fenêtre
	 * @param height hauteur de la fenêtre
	 */
	public EditorLuncher(int width, int height) {
		//préparer ici l'application pour son démarrage
		this.window = new Window(width,height);//TODO: coder la classe fenêtre

		//titre de la fenêtre
		//this.window.setTitle("Enigma");

		// Icône de l'application
		// this.window.setIconImage();
	}

	@Override
	public void start() {
		//on démarre l'application
		this.window.setVisible(true);
	}
}
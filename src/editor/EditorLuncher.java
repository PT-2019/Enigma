package editor;

import editor.api.Application;
import editor.bibliotheque.MenuScreen;
import editor.window.Window;

import java.awt.*;

/**
 * Lanceur de l'éditeur d'escape game
 *
 * @version 1.0
 */
public class EditorLuncher implements Application {

	/**
	 * la fenêtre dans laquelle il est lancé
	 **/
	private Window window;

	/**
	 * Construit l'éditeur d'escape game
	 *
	 * @param width  largeur de la fenêtre
	 * @param height hauteur de la fenêtre
	 */
	public EditorLuncher(int width, int height) {
		//préparer ici l'application pour son démarrage
		this.window = new Window(width, height);

		//ici on configure la fenêtre

		//titre de la fenêtre
		this.window.setTitle("Enigma");

		// Icône de l'application
		// this.window.setIconImage();

		this.window.setLayout(new BorderLayout());
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
		this.window.add(new MenuScreen(this.window), BorderLayout.CENTER);
	}

	@Override
	public void start() {
		//on démarre l'application
		this.window.setVisible(true);
	}
}

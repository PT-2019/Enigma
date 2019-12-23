package editor;

import api.Application;
import editor.screens.EditorScreen;
import editor.window.Window;

import java.awt.BorderLayout;

/**
 * Lanceur de l'éditeur d'escape game
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 1.0
 */
public class EditorLuncher implements Application {

	private static EditorLuncher editor;

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
	private EditorLuncher(int width, int height) {
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
		this.window.add(new EditorScreen(this.window), BorderLayout.CENTER);
	}

	public static EditorLuncher setEditor(int width, int height) {
		if (editor == null) {
			editor = new EditorLuncher(width, height);
		}
		return editor;
	}

	public static EditorLuncher getInstance() {
		if (editor == null) {
			editor = new EditorLuncher(1080, 720);
		}
		return editor;
	}

	@Override
	public void start() {
		//on démarre l'application
		this.window.setVisible(true);
	}

	public Window getWindow() {
		return window;
	}
}

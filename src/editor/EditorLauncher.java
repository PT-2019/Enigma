package editor;

import api.Application;
import api.enums.EnigmaScreens;
import editor.hud.Window;
import editor.screens.EditorScreen;
import game.EnigmaGame;
import starter.AppClosingManager;

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
public class EditorLauncher implements Application {

	private static EditorLauncher editor;

	/**
	 * la fenêtre dans laquelle il est lancé
	 **/
	private Window window;

	/**
	 * Construit l'éditeur d'escape game
	 */
	private EditorLauncher() {
		this.window = new Window();
		this.window.addWindowListener(new AppClosingManager());
		this.window.setLayout(new BorderLayout());
		this.window.add(new EditorScreen(this.window), BorderLayout.CENTER);
	}

	/**
	 * Construit l'éditeur d'escape game
	 *
	 * @param width  largeur de la fenêtre
	 * @param height hauteur de la fenêtre
	 * @since 1.0
	 * @deprecated since 3.0 utiliser {@link EditorLauncher#EditorLauncher()}
	 */
	@Deprecated
	private EditorLauncher(int width, int height) {
		//préparer ici l'application pour son démarrage
		this.window = new Window(width, height);

		//ici on configure la fenêtre

		//titre de la fenêtre
		this.window.setTitle("Enigma");

		// Icône de l'application
		// this.window.setIconImage();

		this.window.setLayout(new BorderLayout());
		this.window.add(new EditorScreen(this.window), BorderLayout.CENTER);
		this.window.addWindowListener(new AppClosingManager());
	}

	/**
	 * Retourne le singleton qui représente l'éditor luncher
	 *
	 * @return le singleton qui représente l'éditor luncher
	 * @since 4.0
	 */
	public static EditorLauncher getInstance() {
		if (editor == null) {
			editor = new EditorLauncher();
		}
		return editor;
	}

	/**
	 * Crée un éditeur d'une certaine taille
	 *
	 * @param width  largeur de l'éditeur
	 * @param height hauteur de l'éditeur
	 * @return l'éditeur
	 */
	@Deprecated
	public static EditorLauncher setEditor(int width, int height) {
		if (editor == null) {
			editor = new EditorLauncher(width, height);
		}
		return editor;
	}

	@Override
	public void start() {
		EnigmaGame.setFirstScreen(EnigmaScreens.TEST.name());
		//on démarre l'application
		this.window.setVisible(true);
	}

	/**
	 * Retourne la fenêtre de l'application
	 *
	 * @return la fenêtre de l'application
	 */
	public Window getWindow() {
		return window;
	}

}
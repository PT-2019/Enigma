package starter;

import api.Application;
import api.enums.EnigmaScreens;
import api.hud.WindowSize;
import api.hud.components.CustomWindow;
import editor.entity.EntityFactory;
import editor.hud.EnigmaWindow;
import editor.screens.EditorScreen;
import game.EnigmaGame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Lanceur de l'éditeur d'escape game
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.2
 * @since 1.0
 */
public class EditorLauncher implements Application {

	private static EditorLauncher editor;

	private EditorScreen editorScreen;

	/**
	 * la fenêtre dans laquelle il est lancé
	 **/
	private EnigmaWindow window;

	/**
	 * Construit l'éditeur d'escape game
	 */
	private EditorLauncher() {
		this.window = new EnigmaWindow();
		this.window.addWindowListener(new AppClosingManager());
		this.window.setSize(WindowSize.FULL_SCREEN_SIZE);
		this.editorScreen = null;

		//

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		for (GraphicsDevice gd : gs) {
			System.out.println(gd);

			JFrame jFrame = new JFrame(gd.getDefaultConfiguration());
			jFrame.getGraphicsConfiguration();


		}

		//charge entités
		EntityFactory.loadEntities("assets/rooms.json");
		EntityFactory.loadEntities("assets/items.json");
		EntityFactory.loadEntities("assets/decors.json");
		EntityFactory.loadEntities("assets/entities.json");
		EntityFactory.loadEntities("assets/actions.json");
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
		this.window = new EnigmaWindow(width, height);

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
		if (this.editorScreen != null)
			this.window.remove(this.editorScreen);
		this.window.setLayout(new BorderLayout());
		EnigmaGame.setStartScreen(EnigmaScreens.TEST);
		this.editorScreen = new EditorScreen(this.window);
		this.window.add(this.editorScreen, BorderLayout.CENTER);
		//on démarre l'application
		this.window.setVisible(true);
	}

	@Override
	public void stop() {
		this.window.close();
	}

	/**
	 * Retourne la fenêtre de l'application
	 *
	 * @return la fenêtre de l'application
	 */
	public CustomWindow getWindow() {
		return window;
	}

}
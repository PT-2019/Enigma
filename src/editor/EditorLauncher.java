package editor;

import api.Application;
import api.hud.CustomWindow;
import api.hud.base.WindowSize;
import api.utils.Utility;
import datas.EditorState;
import datas.EnigmaScreens;
import game.EnigmaGame;
import game.screens.TestScreen;
import general.entities.serialization.EntityFactory;
import general.hud.EnigmaWindow;

import java.awt.BorderLayout;
import java.awt.Cursor;

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
	private EditorLauncher(EnigmaWindow launcher) {
		if (launcher != null) {
			this.window = new EnigmaWindow(Utility.getMonitorOf(launcher));
		} else {
			this.window = new EnigmaWindow();
		}

		TestScreen.setState(EditorState.NORMAL);

		this.window.setSize(WindowSize.FULL_SCREEN_SIZE);
		this.editorScreen = null;

		//charge entités
		EntityFactory.loadEntities("assets/rooms.json");
		EntityFactory.loadEntities("assets/items.json");
		EntityFactory.loadEntities("assets/decors.json");
		EntityFactory.loadEntities("assets/entities.json", true);
		EntityFactory.loadEntities("assets/actions.json");
	}

	/**
	 * Construit l'éditeur d'escape game
	 */
	private EditorLauncher() {
		this(null);
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

	public static EditorLauncher setEditor(EnigmaWindow window) {
		if (editor == null) {
			editor = new EditorLauncher(window);
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

	/**
	 * Définit le cursor de l'application
	 *
	 * @param cursor le cursor
	 */
	public static void setCursor(Cursor cursor) {
		EditorLauncher.getInstance().getWindow().setCursor(cursor);
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

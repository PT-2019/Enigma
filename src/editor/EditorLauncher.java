package editor;

import api.Application;
import api.ui.CustomWindow;
import api.ui.base.WindowSize;
import api.utils.Utility;
import common.hud.EnigmaWindow;
import common.save.entities.serialization.EntityFactory;
import data.EditorState;
import data.EnigmaScreens;
import editor.bar.edition.ActionsManager;
import game.EnigmaGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Lanceur de l'éditeur d'escape game
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 1.0
 */
public class EditorLauncher implements Application {

	private static EditorLauncher editor;
	/**
	 * états de l'éditor
	 *
	 * @since 5.0
	 */
	private static ArrayList<EditorState> states;
	/**
	 * écran de l'éditor
	 */
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

		//reset states
		states = new ArrayList<>();
		clearStates();

		this.window.setSize(WindowSize.FULL_SCREEN_SIZE);
		this.editorScreen = null;

		//charge entités
		EntityFactory.loadEntities("assets/rooms.json");
		EntityFactory.loadEntities("assets/items.json");
		EntityFactory.loadEntities("assets/decors.json");
		EntityFactory.loadPlayers("assets/entities.json");
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

	/**
	 * Retourne le singleton qui représente l'éditor luncher, création si n'existe pas
	 *
	 * @param window fenêtre parent
	 * @return le singleton qui représente l'éditor luncher
	 * @since 5.0
	 */
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

	/**
	 * Retourne l'état de l'éditeur
	 *
	 * @return l'état
	 * @since 5.0
	 */
	@Deprecated
	public static EditorState getState() {
		throw new UnsupportedOperationException("deprecated.");
	}

	/**
	 * Change l'état de l'éditeur
	 *
	 * @param state un état
	 * @since 5.0
	 */
	@Deprecated
	public static void setState(@NotNull EditorState state) {
		throw new UnsupportedOperationException("deprecated.");
	}

	/**
	 * Retourne si l'état de l'éditeur est cet état
	 *
	 * @param state un état
	 * @return true si c'est cet état
	 * @since 5.0
	 */
	@Deprecated
	public static boolean isState(@NotNull EditorState state) {
		throw new UnsupportedOperationException("deprecated.");
	}

	/**
	 * Ajoute un état
	 *
	 * @param state état
	 * @since 6.0
	 */
	public static void addState(@NotNull EditorState state) {
		//active la simulation
		if (state.equals(EditorState.SIMULATION)) {
			getInstance().editorScreen.simulationMode(true);
		}
		if (states.contains(state)) return;
		states.add(state);
	}

	/**
	 * Retourne si l'éditor est dans un état
	 *
	 * @param state état
	 * @return true si dans l'état sinon false
	 * @since 6.0
	 */
	public static boolean containsState(@NotNull EditorState state) {
		return containsState(state, false);
	}

	/**
	 * Retourne si l'éditor est dans un état
	 *
	 * @param state état
	 * @param only  ne contient que cet état
	 * @return true si dans l'état sinon false
	 * @since 6.0
	 */
	public static boolean containsState(EditorState state, boolean only) {
		if (states.isEmpty()) states.add(EditorState.NORMAL);
		if (only) {
			return states.size() == 1 && states.contains(state);
		} else {
			return states.contains(state);
		}
	}

	/**
	 * Supprime tous les états
	 *
	 * @param exceptions états à ne pas supprimer
	 * @since 6.0
	 */
	public static void clearStates(@Nullable EditorState... exceptions) {
		if (exceptions != null && exceptions.length > 0) {
			ArrayList<EditorState> exceptionsList = new ArrayList<>(Arrays.asList(exceptions));
			ArrayList<EditorState> remove = new ArrayList<>();

			boolean persistant = false;
			if(exceptionsList.contains(EditorState.PERSISTANT)) persistant = true;

			//save des états à supprimer
			for (EditorState state : states) {
				if (exceptionsList.contains(state)) continue;
				if(persistant){//si on ne doit pas supprimer les persistants
					if(!state.persistant){ //si l'état n'est pas persistant
						remove.add(state); //supprime
					}
				} else {
					remove.add(state);//sinon supprime tout le monde
				}
			}

			//suppression
			for (EditorState s : remove) {
				states.remove(s);
			}

			//désactive la simulation
			if (remove.contains(EditorState.SIMULATION))
				getInstance().editorScreen.simulationMode(false);

			return;
		}

		//vide tout
		states.clear();
	}

	/**
	 * Supprime un état
	 *
	 * @param state un état
	 * @since 6.0
	 */
	public static void removeState(EditorState state) {
		states.remove(state);
	}

	public static ArrayList<EditorState> getStates() {
		return states;
	}

	@Override
	public void start() {
		if (this.editorScreen != null) this.window.remove(this.editorScreen);
		//Reset historique des actions
		ActionsManager.reset();
		//écran éditeur
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
	 * @since 4.0
	 */
	@Override
	public CustomWindow getWindow() {
		return window;
	}

	/**
	 * Retourne libgdx map panel
	 *
	 * @return libgdx map panel
	 * @since 6.0
	 */
	public JPanel getMapPanel() {
		return this.editorScreen.getMap();
	}
}

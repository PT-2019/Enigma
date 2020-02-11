package game.configure.displayManagers.highLevel;

import common.hud.EnigmaPanel;
import game.configure.LaunchGameDisplay;
import game.configure.displayManagers.lowLevel.CreateGameDisplayManager;
import game.configure.displayManagers.lowLevel.DisplayManager;
import game.configure.displayManagers.lowLevel.JoinGameDisplayManager;
import game.configure.displayManagers.lowLevel.SelectGameDisplayManager;
import game.configure.displayManagers.lowLevel.WaitPlayersDisplayManager;
import game.configure.displayManagers.lowLevel.WaitPlayersLeaderDisplayManager;

import java.awt.CardLayout;
import java.util.HashMap;

/**
 * Gère la barre de menu des l'affichages
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 * @deprecated
 */
public class RightBarDisplayManager {

	/**
	 * Instance
	 */
	private final static RightBarDisplayManager instance = new RightBarDisplayManager();

	/**
	 * Contenu
	 */
	private EnigmaPanel panel;

	/**
	 * Disposition
	 */
	private CardLayout layout;

	/**
	 * Liste des affichages
	 */
	private HashMap<String, DisplayManager> displays;

	/**
	 * Affichage actuel
	 */
	private String currentDisplay;

	private RightBarDisplayManager() {
		this.layout = new CardLayout();
		this.panel = new EnigmaPanel();
		this.panel.setLayout(this.layout);

		this.displays = new HashMap<>();
		this.displays.put(LaunchGameDisplay.SELECT_GAME, SelectGameDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.JOIN_GAME, JoinGameDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.WAIT_PLAYERS, WaitPlayersDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.WAIT_PLAYERS_LEADER, WaitPlayersLeaderDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.CREATE_GAME, CreateGameDisplayManager.getInstance());

		this.panel.add(SelectGameDisplayManager.getInstance().getRightBar(), LaunchGameDisplay.SELECT_GAME);
		this.panel.add(JoinGameDisplayManager.getInstance().getRightBar(), LaunchGameDisplay.JOIN_GAME);
		this.panel.add(WaitPlayersDisplayManager.getInstance().getRightBar(), LaunchGameDisplay.WAIT_PLAYERS);
		this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getRightBar(), LaunchGameDisplay.WAIT_PLAYERS_LEADER);
		this.panel.add(CreateGameDisplayManager.getInstance().getRightBar(), LaunchGameDisplay.CREATE_GAME);

		this.currentDisplay = LaunchGameDisplay.SELECT_GAME;
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static RightBarDisplayManager getInstance() {
		return instance;
	}

	/**
	 * Change l'affichage
	 *
	 * @param displayName Affichage
	 */
	public void showDisplay(String displayName) {
		this.layout.show(this.panel, displayName);
		this.displays.get(displayName).refreshAll();
		this.currentDisplay = displayName;
	}

	/**
	 * Obtenir le contenu
	 *
	 * @return Le contenu
	 */
	public EnigmaPanel getPanel() {
		return this.panel;
	}

	/**
	 * Obtenir l'affichage actuel
	 *
	 * @return Affichage actuel
	 */
	public String getCurrentDisplay() {
		return this.currentDisplay;
	}

	/**
	 * Rafraichi l'affichage actuel
	 */
	public void refreshCurrentDisplay() {
		this.displays.get(this.currentDisplay).refreshAll();
	}
}

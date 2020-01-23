package starter.gameConfig.displayManagers.highLevel;

import editor.hud.EnigmaPanel;
import starter.gameConfig.LaunchGameDisplay;
import starter.gameConfig.displayManagers.lowLevel.CreateGameDisplayManager;
import starter.gameConfig.displayManagers.lowLevel.DisplayManager;
import starter.gameConfig.displayManagers.lowLevel.JoinGameDisplayManager;
import starter.gameConfig.displayManagers.lowLevel.SelectGameDisplayManager;
import starter.gameConfig.displayManagers.lowLevel.WaitPlayersDisplayManager;
import starter.gameConfig.displayManagers.lowLevel.WaitPlayersLeaderDisplayManager;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.HashMap;

/**
 * Gère le contenu des l'affichages
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class ContentDisplayManager {

	/**
	 * Instance
	 */
	private final static ContentDisplayManager instance = new ContentDisplayManager();

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

	private ContentDisplayManager() {
		this.layout = new CardLayout();
		this.panel = new EnigmaPanel();
		this.panel.setLayout(this.layout);
		this.panel.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);

		this.displays = new HashMap<>();
		this.displays.put(LaunchGameDisplay.SELECT_GAME, SelectGameDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.JOIN_GAME, JoinGameDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.WAIT_PLAYERS, WaitPlayersDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.WAIT_PLAYERS_LEADER, WaitPlayersLeaderDisplayManager.getInstance());
		this.displays.put(LaunchGameDisplay.CREATE_GAME, CreateGameDisplayManager.getInstance());

		this.panel.add(SelectGameDisplayManager.getInstance().getContent(), LaunchGameDisplay.SELECT_GAME);
		this.panel.add(JoinGameDisplayManager.getInstance().getContent(), LaunchGameDisplay.JOIN_GAME);
		this.panel.add(WaitPlayersDisplayManager.getInstance().getContent(), LaunchGameDisplay.WAIT_PLAYERS);
		this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getContent(), LaunchGameDisplay.WAIT_PLAYERS_LEADER);
		this.panel.add(CreateGameDisplayManager.getInstance().getContent(), LaunchGameDisplay.CREATE_GAME);

		this.currentDisplay = LaunchGameDisplay.SELECT_GAME;
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static ContentDisplayManager getInstance() {
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

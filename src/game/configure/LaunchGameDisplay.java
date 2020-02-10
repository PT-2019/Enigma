package game.configure;

import common.hud.EnigmaPanel;
import game.configure.displayManagers.highLevel.ContentDisplayManager;
import game.configure.displayManagers.highLevel.RightBarDisplayManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Gère l'affichage pour sélectionner, créer, configurer, rejoindre, une partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 * @deprecated
 */
public class LaunchGameDisplay {
	/**
	 * Affichage pour choisir une partie a lancer
	 */
	public static final String SELECT_GAME = "select game";
	/**
	 * Affichage pour rejoindre une partie
	 */
	public static final String JOIN_GAME = "join game";
	/**
	 * Affichage pour créer une partie
	 */
	public static final String CREATE_GAME = "create game";
	/**
	 * Affichage pour attendre le début de la partie côté chef de la partie
	 */
	public static final String WAIT_PLAYERS_LEADER = "wait players leader";
	/**
	 * Affichage pour attendre le début de la partie
	 */
	public static final String WAIT_PLAYERS = "wait players";
	/**
	 * Instance
	 */
	private final static LaunchGameDisplay instance = new LaunchGameDisplay();
	/**
	 * Contenu
	 */
	private EnigmaPanel panel;

	private LaunchGameDisplay() {
		this.panel = new EnigmaPanel();
		this.panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 5;
		gbc.weighty = 1;
		this.panel.add(ContentDisplayManager.getInstance().getPanel(), gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.panel.add(RightBarDisplayManager.getInstance().getPanel(), gbc);
		this.showDisplay(SELECT_GAME);
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static LaunchGameDisplay getInstance() {
		return instance;
	}

	/**
	 * Montre l'affichage désigné
	 *
	 * @param displayName Affichage
	 */
	public void showDisplay(String displayName) {
		ContentDisplayManager.getInstance().showDisplay(displayName);
		RightBarDisplayManager.getInstance().showDisplay(displayName);
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
	 * Rafraîchi l'écran actuel
	 */
	public void refreshCurrentDisplay() {
		ContentDisplayManager.getInstance().refreshCurrentDisplay();
		RightBarDisplayManager.getInstance().refreshCurrentDisplay();
	}
}


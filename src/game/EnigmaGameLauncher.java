package game;

import api.Application;
import api.enums.EnigmaScreens;
import api.utils.LoadGameLibgdxApplication;
import editor.entity.player.Player;
import editor.hud.*;
import editor.hud.Window;
import game.display.LaunchGameDisplay;
import starter.AppClosingManager;

import java.awt.*;

/**
 * Lance le jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 25/12/2019
 * @since 4.0 25/12/2019
 */
public class EnigmaGameLauncher implements Application {

	private static EnigmaGameLauncher launcher;
	private Window window;
	private CardLayout configurationLayout;
	private CardLayout gameLayout;
	private LaunchGameDisplay launchGameDisplay;

	public final static String LAUNCH_GAME = "launch game";
	public final static String PLAY_GAME = "play game";

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
		GameConfiguration.getInstance().setOwner(new Player("utilisateur qui à lancé la partie"));
		this.window = new Window();
		this.window.setWindowBackground(Color.DARK_GRAY);
		this.window.addWindowListener(new AppClosingManager());

		EnigmaPanel gamePanel = new EnigmaPanel();
		//this.configurationLayout = new CardLayout();
		this.gameLayout = new CardLayout();

		this.window.getContentSpace().setLayout(this.gameLayout);

		LoadGameLibgdxApplication.load(gamePanel, window);

		/*EnigmaPanel configurationPanel = new EnigmaPanel();
		configurationPanel.setLayout(new GridBagLayout());
		EnigmaPanel navBar = new EnigmaPanel();
		EnigmaPanel configContent = new EnigmaPanel();
		configContent.setLayout(this.configurationLayout);

		configContent.add(SoloDisplayManager.getInstance().getPanel(),SOLO);
		configContent.add(MultiDisplayManager.getInstance().getPanel(),MULTI);
		configContent.add(CreateGameDisplayManager.getInstance().getPanel(), CREATE_GAME);
		configContent.add(WaitPlayersDisplayManager.getInstance().getPanel(),WAIT_PLAYERS);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		configurationPanel.add(navBar,gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 4;
		configurationPanel.add(configContent,gbc);

		this.window.getContentSpace().add(configurationPanel,CONFIGURATION);*/
		this.window.getContentSpace().add(LaunchGameDisplay.getInstance().getPanel(),LAUNCH_GAME);
		this.window.getContentSpace().add(gamePanel, PLAY_GAME);
	}

	/**
	 * Retourne l'instance unique du lanceur du jeu
	 *
	 * @return l'instance unique du lanceur du jeu
	 * @since 4.0
	 */
	public static EnigmaGameLauncher getInstance() {
		if (launcher == null) {
			launcher = new EnigmaGameLauncher();
		}
		return launcher;
	}

	/**
	 * Lance le jeu dans une nouvelle fenêtre
	 *
	 * @since 4.0
	 */
	@Override
	public void start() {
		EnigmaGame.setFirstScreen(EnigmaScreens.GAME.name());
		this.showDisplay(LAUNCH_GAME);
		this.window.setVisible(true);
	}

	/**
	 * Retourne la fenêtre dans laquelle tourne le jeu
	 *
	 * @return la fenêtre dans laquelle tourne le jeu
	 * @since 4.0
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * Affiche l'écran désigné par son nom
	 * @param displayName Nom de l'écran
	 * @since 4.0
	 */
	public void showDisplay(String displayName){
		this.gameLayout.show(this.window.getContentSpace(),displayName);
	}
}

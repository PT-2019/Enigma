package starter;

import api.Application;
import api.enums.EnigmaScreens;
import api.hud.components.CustomWindow;
import api.utils.LoadGameLibgdxApplication;
<<<<<<< HEAD:src/game/EnigmaGameLauncher.java
import editor.entity.player.Player;
import editor.hud.*;
import editor.hud.Window;
import game.display.LaunchGameDisplay;
import starter.AppClosingManager;
=======
import api.utils.annotations.NeedPatch;
import editor.hud.EnigmaWindow;
import game.EnigmaGame;
>>>>>>> e3069dfb0e80fc64dd0c3b99e16d2ec82f0dd0e7:src/starter/EnigmaGameLauncher.java

import java.awt.*;

/**
 * Lance le jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
@NeedPatch
public class EnigmaGameLauncher implements Application {

	private static EnigmaGameLauncher launcher;
<<<<<<< HEAD:src/game/EnigmaGameLauncher.java
	private Window window;
	private CardLayout configurationLayout;
	private CardLayout gameLayout;
	private LaunchGameDisplay launchGameDisplay;

	public final static String LAUNCH_GAME = "launch game";
	public final static String PLAY_GAME = "play game";
=======
	private CustomWindow window;
	private JPanel gameScreen;
>>>>>>> e3069dfb0e80fc64dd0c3b99e16d2ec82f0dd0e7:src/starter/EnigmaGameLauncher.java

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
<<<<<<< HEAD:src/game/EnigmaGameLauncher.java
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
=======
		this.window = new EnigmaWindow();
		this.window.setLayout(new BorderLayout());
		this.window.addWindowListener(new AppClosingManager());
		this.gameScreen = null;
>>>>>>> e3069dfb0e80fc64dd0c3b99e16d2ec82f0dd0e7:src/starter/EnigmaGameLauncher.java
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
<<<<<<< HEAD:src/game/EnigmaGameLauncher.java
		EnigmaGame.setFirstScreen(EnigmaScreens.GAME.name());
		this.showDisplay(LAUNCH_GAME);
=======
		if (this.gameScreen != null)
			this.window.remove(this.gameScreen);
		this.window.setLayout(new BorderLayout());
		this.gameScreen = new JPanel();
		LoadGameLibgdxApplication.load(this.gameScreen, window);
		EnigmaGame.setStartScreen(EnigmaScreens.GAME);
		this.window.add(this.gameScreen, BorderLayout.CENTER);
>>>>>>> e3069dfb0e80fc64dd0c3b99e16d2ec82f0dd0e7:src/starter/EnigmaGameLauncher.java
		this.window.setVisible(true);
	}

	@Override
	public void stop() {
		this.window.close();
	}

	/**
	 * Retourne la fenêtre dans laquelle tourne le jeu
	 *
	 * @return la fenêtre dans laquelle tourne le jeu
	 * @since 4.0
	 */
	public CustomWindow getWindow() {
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

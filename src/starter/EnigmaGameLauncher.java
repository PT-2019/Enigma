package starter;

import api.Application;
import api.enums.EnigmaScreens;
import api.hud.components.CustomWindow;
import api.utils.LoadGameLibgdxApplication;
import api.utils.annotations.NeedPatch;
import editor.hud.EnigmaWindow;
import game.EnigmaGame;
import starter.gameConfig.LaunchGameDisplay;

import javax.swing.JPanel;
import java.awt.BorderLayout;

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
	private CustomWindow window;
	private JPanel gameScreen;

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
		this.window = new EnigmaWindow();
		this.window.setLayout(new BorderLayout());
		this.window.addWindowListener(new AppClosingManager());
		this.gameScreen = LaunchGameDisplay.getInstance().getPanel();
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
		if (this.gameScreen != null)
			this.window.remove(this.gameScreen);
		this.window.setLayout(new BorderLayout());
		this.gameScreen = new JPanel();
		LoadGameLibgdxApplication.load(this.gameScreen, window);
		EnigmaGame.setStartScreen(EnigmaScreens.GAME);
		this.window.add(this.gameScreen, BorderLayout.CENTER);
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
}
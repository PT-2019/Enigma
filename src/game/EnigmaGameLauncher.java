package game;

import api.Application;
import api.enums.EnigmaScreens;
import api.utils.LoadGameLibgdxApplication;
import editor.hud.Window;
import starter.AppClosingManager;

import javax.swing.JPanel;
import java.awt.BorderLayout;

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

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
		this.window = new Window();
		this.window.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		LoadGameLibgdxApplication.load(p, window);
		this.window.add(p, BorderLayout.CENTER);
		this.window.addWindowListener(new AppClosingManager());
	}

	/**
	 * Retourne l'instace unique du lanceur du jeu
	 *
	 * @return l'instace unique du lanceur du jeu
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
}

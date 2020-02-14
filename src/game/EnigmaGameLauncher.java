package game;

import api.Application;
import api.libgdx.utils.LoadGameLibgdxApplication;
import api.ui.CustomWindow;
import common.hud.EnigmaWindow;
import common.utils.runnable.StartGameRunnable;
import data.EnigmaScreens;
import game.hmi.MHIManager;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Lance le jeu
 *
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0
 */
public class EnigmaGameLauncher implements Application {

	/**
	 * instance unique
	 * @since 4.0
	 */
	private static EnigmaGameLauncher launcher;

	/**
	 * Fenêtre de l'éditeur
	 * @since 4.0
	 */
	private CustomWindow window;

	/**
	 * écran du jeu
	 * @since 6.0
	 */
	private JPanel gameScreen;

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
		this.window = new EnigmaWindow();
		this.window.setLayout(new BorderLayout());
		this.setContentToGameConfig();
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
		if(this.gameScreen != null) {
			this.window.remove(this.gameScreen);
			this.gameScreen = new JPanel();
		}
		//Affiche l'écran de configuration de partie
		this.setContentToGameConfig();
		//écran du jeu a afficher
		EnigmaGame.setStartScreen(EnigmaScreens.GAME);
		//affiche fenêtre
		this.window.setVisible(true);
	}

	/**
	 * Lance l'écran de configuration de partie
	 * @since 6.0
	 */
	@SuppressWarnings("WeakerAccess")
	public void setContentToGameConfig(){
		this.gameScreen = MHIManager.getInstance().getContent();
		this.window.add(this.gameScreen, BorderLayout.CENTER);
	}

	/**
	 * Lance le jeu
	 * @param mapPath chemin de la map
	 * @since 6.0
	 */
	public void setContentToGame(String mapPath){
		//méthode a exécuter au lancement
		EnigmaGame.setOnLoad(new StartGameRunnable(mapPath));
		//charge la libgdx
		LoadGameLibgdxApplication.load(this.gameScreen, window);
		//affiche
		this.window.add(this.gameScreen, BorderLayout.CENTER);
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
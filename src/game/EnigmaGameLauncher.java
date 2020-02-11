package game;

import api.Application;
import api.libgdx.utils.LoadGameLibgdxApplication;
import api.ui.CustomWindow;
import common.hud.EnigmaWindow;
import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import game.hmi.MHIManager;
import game.screens.GameScreen;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Lance le jeu
 *
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
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
		if (this.gameScreen != null)
			this.window.remove(this.gameScreen);
		this.window.setLayout(new BorderLayout());
		this.setContentToGameConfig();
		System.out.println("---");
		EnigmaGame.setStartScreen(EnigmaScreens.GAME);
		this.window.add(this.gameScreen, BorderLayout.CENTER);
		this.window.setVisible(true);
	}

	public void setContentToGameConfig(){
		this.gameScreen = MHIManager.getInstance().getContent();
	}

	public void setContentToGame(String mapPath){
		EnigmaGame.setOnLoad(() -> {
			//change la map
			EnigmaGame.getCurrentScreen().setMap(mapPath);
			//recharge écran avec la nouvelle map
			EnigmaGame.reload(EnigmaScreens.GAME.name());
			//charge les entités sur la bonne map !
			EmptyMapGenerator.load(GameScreen.getMapPath());
		});
		LoadGameLibgdxApplication.load(this.gameScreen, window);
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
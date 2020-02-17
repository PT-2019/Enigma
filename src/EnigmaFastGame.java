import api.Application;
import api.libgdx.utils.LoadGameLibgdxApplication;
import api.ui.CustomWindow;
import api.utils.WindowClosing;
import api.utils.annotations.NeedPatch;
import common.hud.EnigmaWindow;
import common.language.GameLanguage;
import common.utils.runnable.StartGameRunnable;
import data.EnigmaScreens;
import game.EnigmaGame;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * UNE CLASSE TEMPORAIRE POUR LANCER LE JEU RAPIDEMENT,
 * LANCE l'écran DE JEU
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
public class EnigmaFastGame implements Application {

	private static final String PATH = "assets/files/map/test.tmx";
	private static EnigmaFastGame launcher;
	private CustomWindow window;
	private JPanel gameScreen;

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	@NeedPatch
	private EnigmaFastGame() {
		this.window = new EnigmaWindow();
		this.window.setLayout(new BorderLayout());
		//TEMPORAIRE
		this.window.addWindowListener((WindowClosing) e -> System.exit(0));
	}

	/**
	 * Retourne l'instance unique du lanceur du jeu
	 *
	 * @return l'instance unique du lanceur du jeu
	 * @since 4.0
	 */
	public static EnigmaFastGame getInstance() {
		if (launcher == null) {
			launcher = new EnigmaFastGame();
		}
		return launcher;
	}

	public static void main(String[] args) {
		//appelle après initialisation de la libgdx, l'éditeur
		SwingUtilities.invokeLater(() -> {
			//init de la langue
			GameLanguage.init();

			//définit le jeu
			LoadGameLibgdxApplication.setGame(EnigmaGame.getInstance());

			//start
			EnigmaFastGame.getInstance().start();
		});
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
		EnigmaGame.setOnLoad(new StartGameRunnable(PATH));
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
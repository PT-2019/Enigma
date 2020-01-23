import api.Application;
import api.enums.EnigmaScreens;
import api.hud.components.CustomWindow;
import api.utils.LoadGameLibgdxApplication;
import api.utils.annotations.NeedPatch;
import editor.hud.EnigmaWindow;
import game.EnigmaGame;
import starter.gameConfig.LaunchGameDisplay;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * UNE CLASSE TEMPORAIRE POUR LANCER LE JEU RAPIDEMENT,
 * LANCE l'écran DE JEU
 *
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
public class EnigmaFastGame implements Application {

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
		this.window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.gameScreen = LaunchGameDisplay.getInstance().getPanel();
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

	public static void main(String[] args) {
		//appelle après initialisation de la libgdx, l'éditeur
		SwingUtilities.invokeLater(() -> {
			LoadGameLibgdxApplication.setGame(EnigmaGame.getInstance());
			EnigmaFastGame.getInstance().start();
		});
	}
}
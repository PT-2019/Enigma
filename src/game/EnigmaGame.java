package game;

import api.libgdx.LibgdxGame;
import api.ui.CustomWindow;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import data.EnigmaScreens;
import game.screens.GameScreen;
import game.screens.TestScreen;

/**
 * Lanceur du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 2.0
 */
public class EnigmaGame extends LibgdxGame {

	/**
	 * Instance unique
	 * @since 2.0
	 */
	private static EnigmaGame enigmaGame;

	/**
	 * Le screen a lancer au début du jeu
	 * @since 3.0
	 */
	private static EnigmaScreens startScreen = EnigmaScreens.GAME;

	/**
	 * Exécutable lancé après initialisation de la libgdx
	 * @since 6.0
	 */
	private static Runnable onLoad = null;

	/**
	 * Empêche la création extérieure.
	 * @since 6.0
	 */
	private EnigmaGame(){
	}

	@Override
	public void start() {
		//garde l'instance
		enigmaGame = this;

		//définit le niveau de log
		Gdx.app.setLogLevel(Application.LOG_ERROR | Application.LOG_DEBUG);

		//ajout des screens disponibles
		EnigmaGame.addScreen(EnigmaScreens.TEST.name(), TestScreen.class);
		EnigmaGame.addScreen(EnigmaScreens.GAME.name(), GameScreen.class);

		//chargement des screens au début pour éviter les chargement pendant le jeu
		EnigmaGame.load(EnigmaScreens.TEST.name());
		EnigmaGame.load(EnigmaScreens.GAME.name());

		//définit l'écran actuel de l'application
		EnigmaGame.setScreen(EnigmaGame.startScreen.name());

		//lance onLoad
		if(EnigmaGame.onLoad != null) EnigmaGame.onLoad.run();
	}

	@Override
	public void stop() {
		free();
	}

	@Override
	public void free() {//aucune ressource à libérer
	}

	@Override
	public CustomWindow getWindow() { return null; }

	/**
	 * Il s'agit d'une singleton.
	 *
	 * @return l'instance unique du jeu
	 *
	 * @since 2.0
	 */
	public static EnigmaGame getInstance() {
		if (EnigmaGame.enigmaGame == null)
			EnigmaGame.enigmaGame = new EnigmaGame();
		return EnigmaGame.enigmaGame;
	}

	/**
	 * écran affiché après initialisation de la libgdx
	 * @param screen écran affiché après initialisation de la libgdx
	 * @since 3.0
	 */
	public static void setStartScreen(EnigmaScreens screen) {
		EnigmaGame.startScreen = screen;
	}

	/**
	 * Exécutable lancé après initialisation de la libgdx
	 * @param r Exécutable lancé après initialisation de la libgdx
	 */
	@SuppressWarnings("WeakerAccess")
	public static void setOnLoad(Runnable r) {
		EnigmaGame.onLoad = r;
	}
}

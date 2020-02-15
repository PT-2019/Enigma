package game;

import api.libgdx.LibgdxGame;
import api.ui.CustomWindow;
import api.utils.annotations.NeedPatch;
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
 * @version 3.0 14 décembre 2019
 * @since 2.0
 */
public class EnigmaGame extends LibgdxGame {

	//instance unique
	private static EnigmaGame enigmaGame;
	/**
	 * Le screen a lancer au début du jeu
	 */
	private static EnigmaScreens startScreen = EnigmaScreens.GAME;
	private static Runnable onLoad;

	//on charge ici le joueur et tout ce qui vit indépendamment des écrans
	//....

	/**
	 * Il s'agit d'une singleton.
	 *
	 * @return l'instance unique du jeu
	 */
	public static EnigmaGame getInstance() {
		if (enigmaGame == null)
			enigmaGame = new EnigmaGame();
		return enigmaGame;
	}

	public static void setStartScreen(EnigmaScreens screen) {
		startScreen = screen;
	}

	public static void setOnLoad(Runnable r) {
		onLoad = r;
	}

	@Override
	@NeedPatch
	public void start() {
		enigmaGame = this;

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
		onLoad.run();
	}

	@Override
	public void stop() {
	}

	@Override
	public void free() {

	}

	@Override
	public CustomWindow getWindow() {
		return null;
	}
}

package game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import game.api.LibgdxGame;
import game.screen.EnigmaScreens;
import game.screen.TestScreen;

/**
 * Lanceur du jeu
 *
 * @version 3.0 14 décembre 2019
 * @since 2.0
 */
public class EnigmaGame extends LibgdxGame {

	//on charge ici le joueur et tout ce qui vit indépendamment des écrans

	//instance unique
	private static EnigmaGame enigmaGame;

	private EnigmaGame() {
	}

	/**
	 * Il s'agit d'une singleton.
	 *
	 * @return l'instance unique du jeu
	 */
	public static EnigmaGame getInstance() {
		if (enigmaGame == null) {
			enigmaGame = new EnigmaGame();
		}
		return enigmaGame;
	}

	@Override
	public void start() {
		enigmaGame = this;
		Gdx.app.setLogLevel(Application.LOG_ERROR | Application.LOG_DEBUG);

		//ajout des screens disponibles
		EnigmaGame.addScreen(EnigmaScreens.TEST.name(), TestScreen.class);

		//chargement des screens au début pour éviter les chargement pendant le jeu
		EnigmaGame.load(EnigmaScreens.TEST.name());

		//définit l'écran actuel de l'application
		EnigmaGame.setScreen(EnigmaScreens.TEST.name());
	}
}

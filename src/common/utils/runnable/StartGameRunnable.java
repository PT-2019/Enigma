package common.utils.runnable;

import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import data.Layer;
import game.EnigmaGame;
import game.screens.GameScreen;

/**
 * Lance le jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class StartGameRunnable implements Runnable {

	/**
	 * chemin de la map
	 */
	private final String mapPath;

	/**
	 * Lance le jeu
	 *
	 * @param mapPath chemin de la map
	 */
	public StartGameRunnable(String mapPath) {
		this.mapPath = mapPath;
	}

	@Override
	public void run() {
		//change la map
		EnigmaGame.getCurrentScreen().setMap(mapPath);
		//recharge écran avec la nouvelle map
		EnigmaGame.reload(EnigmaScreens.GAME.name());
		EnigmaGame.getCurrentScreen().getMap().showLayer(Layer.COLLISION, false);
		//charge les énigmes sur la bonne map !
		EmptyMapGenerator.load(GameScreen.getMapPath());
		//recharge la map
		EnigmaGame.getCurrentScreen().getMap().reload();
	}
}

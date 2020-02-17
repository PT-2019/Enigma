package common.utils.runnable;

import data.Layer;
import game.EnigmaGame;

/**
 * Lance la simulation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class StartSimulationRunnable extends StartGameRunnable {


	/**
	 * Lance la simulation
	 *
	 * @param mapPath chemin de la map
	 */
	public StartSimulationRunnable(String mapPath) {
		super(mapPath);
	}

	@Override
	public void run() {
		super.run();

		//affiche le layer de collision
		EnigmaGame.getCurrentScreen().getMap().showLayer(Layer.COLLISION, true);
	}
}
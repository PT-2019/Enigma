package api;

/**
 * Représentation d'une application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @see game.EnigmaGame
 * @see starter.EditorLauncher
 * @since 1.0
 */
public interface Application {

	/**
	 * Cette méthode lance une application
	 *
	 * @since 1.0
	 */
	void start();

	/**
	 * Ferme l'application
	 *
	 * @since 4.0
	 */
	void stop();

}
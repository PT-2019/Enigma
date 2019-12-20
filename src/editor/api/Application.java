package editor.api;

import game.EnigmaGame;

/**
 * Représentation d'une application
 *
 * @version 1.0
 * @see editor.EditorLuncher
 * @see EnigmaGame
 * @since 1.0
 */
@FunctionalInterface
public interface Application {

	/**
	 * Cette méthode lance une application
	 */
	void start();

}
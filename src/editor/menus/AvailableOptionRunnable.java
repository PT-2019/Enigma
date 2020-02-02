package editor.menus;

import common.entities.GameObject;

/**
 * Les runnables pour ajouter les options au popup
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public interface AvailableOptionRunnable {

	/**
	 * Retourne l'option
	 *
	 * @return l'option
	 */
	AvailablePopUpOption getOption();

	/**
	 * Lance le code pour ajouter l'option
	 */
	void run();

	/**
	 * Lance le code pour ajouter l'option
	 */
	void run(AbstractPopUpView view, Drawable panel, GameObject object);
}

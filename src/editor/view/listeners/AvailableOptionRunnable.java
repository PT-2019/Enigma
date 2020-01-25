package editor.view.listeners;

import api.enums.AvailablePopUpOption;
import editor.view.listeners.available.AddItems;
import editor.view.listeners.available.DefineAsHero;
import editor.view.listeners.available.DefineAsNpc;
import editor.view.listeners.available.Delete;
import editor.view.listeners.available.ManageEnigmas;
import editor.view.listeners.available.SetAccess;
import editor.view.listeners.available.SetContent;

/**
 * Les runnables pour ajouter les options au popup
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public interface AvailableOptionRunnable extends Runnable {

	/**
	 * Constante des classes disponibles pour les options,
	 * pas très propre mais j'ai pas d'autre idée.
	 */
	Class[] classes = {
			SetAccess.class, DefineAsHero.class, DefineAsNpc.class,
			Delete.class, ManageEnigmas.class, SetContent.class,
			AddItems.class
	};

	/**
	 * Retourne l'option
	 * @return l'option
	 */
	AvailablePopUpOption getOption();

	/**
	 * Lance le code pour ajouter l'option
	 */
	@Override
	void run();
}

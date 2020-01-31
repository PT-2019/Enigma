package editor.menus;

import editor.menus.content.SetContent;
import editor.menus.item.AddItems;
import editor.menus.others.DefineAsHero;
import editor.menus.others.DefineAsNpc;
import editor.menus.others.Delete;
import editor.menus.others.ManageEnigmas;
import editor.menus.others.SetAccess;
import general.entities.GameObject;
import general.hud.EnigmaPanel;

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
	void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object);
}

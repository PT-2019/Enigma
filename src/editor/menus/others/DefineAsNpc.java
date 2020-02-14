package editor.menus.others;

import common.entities.GameObject;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.popup.cases.CasePopUp;

/**
 * Définit l'entité actuelle comme étant un NPC
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
@Deprecated
public class DefineAsNpc implements AvailableOptionRunnable {

	/**
	 * Parent
	 */
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private final CasePopUp parent;

	/**
	 * Définit l'entité actuelle comme étant un NPC
	 *
	 * @param parent parent
	 */
	public DefineAsNpc(CasePopUp parent) {
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.DEFINE_AS_NPC;
	}

	@Override
	public void run() {

	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {

	}
}



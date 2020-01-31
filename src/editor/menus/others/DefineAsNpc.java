package editor.menus.others;

import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.popup.cases.CasePopUp;
import general.entities.GameObject;
import general.hud.EnigmaPanel;

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
public class DefineAsNpc implements AvailableOptionRunnable {

	/**
	 * Parent
	 */
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
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {

	}
}



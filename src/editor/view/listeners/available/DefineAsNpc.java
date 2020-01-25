package editor.view.listeners.available;

import api.enums.AvailablePopUpOption;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;

/**
 * Définit l'entité actuelle comme étant un NPC
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
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
	public DefineAsNpc(CasePopUp parent){
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.DEFINE_AS_NPC;
	}

	@Override
	public void run() {

	}
}



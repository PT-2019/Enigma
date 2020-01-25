package editor.view.listeners.available;

import api.enums.AvailablePopUpOption;
import editor.hud.EnigmaButton;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;

/**
 * Permet d'ajouter des objets a un conteneur.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class AddItems implements AvailableOptionRunnable {

	private static final String ADD_ITEMS = "Ajouter des objets";

	/**
	 * Bouton d'ajout d'objets
	 */
	private EnigmaButton addItems;

	/**
	 * Parent
	 */
	private final CasePopUp parent;

	public AddItems(CasePopUp parent){
		this.parent = parent;
		this.addItems = new EnigmaButton(ADD_ITEMS);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.ADD_ITEMS;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.addItems);
	}
}

package editor.view.listeners.available;

import api.enums.AvailablePopUpOption;
import editor.hud.EnigmaButton;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;

/**
 * Permet de changer la propriété d'accès (collision)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class SetContent implements AvailableOptionRunnable {

	private static final String DIALOG = "Définir le contenu";

	/**
	 * Affiche la gestion de contenu pour les entités.
	 * @see api.entity.types.Content
	 */
	private EnigmaButton contentButton;

	private final CasePopUp parent;

	public SetContent(CasePopUp parent){
		this.parent = parent;
		this.contentButton = new EnigmaButton(DIALOG);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_CONTENT;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.contentButton);
	}
}

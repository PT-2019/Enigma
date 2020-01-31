package common.hud;

import api.ui.CustomLabel;
import common.hud.ui.EnigmaLabelUI;

/**
 * Un label de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaLabel extends CustomLabel {

	/**
	 * Crée un label
	 */
	public EnigmaLabel() {
		super();
		this.setComponentUI(new EnigmaLabelUI());
	}

	/**
	 * Crée un label
	 *
	 * @param title son contenu
	 */
	public EnigmaLabel(String title) {
		super(title);
		this.setComponentUI(new EnigmaLabelUI());
	}

	@Override
	public EnigmaLabelUI getComponentUI() {
		return (EnigmaLabelUI) super.getComponentUI();
	}
}

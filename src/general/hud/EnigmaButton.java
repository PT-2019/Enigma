package general.hud;

import api.hud.CustomButton;
import general.hud.ui.EnigmaButtonUI;

/**
 * Un bouton de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaButton extends CustomButton {

	/**
	 * Crée un simple bouton
	 */
	public EnigmaButton() {
		super();
		this.setComponentUI(new EnigmaButtonUI());
	}

	/**
	 * Crée un simple bouton
	 *
	 * @param title son contenu
	 */
	public EnigmaButton(String title) {
		super(title);
		this.setComponentUI(new EnigmaButtonUI());
	}

	@Override
	public EnigmaButtonUI getComponentUI() {
		return (EnigmaButtonUI) this.ui;
	}
}

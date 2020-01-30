package general.hud;

import api.hud.CustomTextArea;
import general.hud.ui.EnigmaTextAreaUI;

/**
 * Une champ de saisie de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaTextArea extends CustomTextArea {

	public EnigmaTextArea() {
		super();
		this.setComponentUI(new EnigmaTextAreaUI());
	}

	@Override
	public EnigmaTextAreaUI getComponentUI() {
		return (EnigmaTextAreaUI) super.getComponentUI();
	}
}

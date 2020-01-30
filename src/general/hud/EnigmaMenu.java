package general.hud;

import api.hud.CustomMenu;
import general.hud.ui.EnigmaMenuUI;

/**
 * Un menu de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaMenu extends CustomMenu {

	/**
	 * Un menu
	 *
	 * @param title son nom
	 */
	public EnigmaMenu(String title) {
		super(title);
		this.setComponentUI(new EnigmaMenuUI());
	}

	@Override
	public EnigmaMenuUI getComponentUI() {
		return (EnigmaMenuUI) super.getComponentUI();
	}
}

package general.hud;

import api.hud.CustomMenuItem;
import general.hud.ui.EnigmaMenuItemUI;

/**
 * Un élément d'un menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaMenuItem extends CustomMenuItem {

	/**
	 * Un élément d'un menu
	 *
	 * @param title contenu
	 */
	public EnigmaMenuItem(String title) {
		super(title);
		this.setComponentUI(new EnigmaMenuItemUI());
	}

	@Override
	public EnigmaMenuItemUI getComponentUI() {
		return (EnigmaMenuItemUI) super.getComponentUI();
	}
}

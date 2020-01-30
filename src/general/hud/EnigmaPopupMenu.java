package general.hud;

import api.hud.CustomPopupMenu;
import general.hud.ui.EnigmaPopupMenuUI;

/**
 * Un popup de menus de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaPopupMenu extends CustomPopupMenu {

	public EnigmaPopupMenu() {
		super();
		this.setComponentUI(new EnigmaPopupMenuUI());
	}

	@Override
	public EnigmaPopupMenuUI getComponentUI() {
		return (EnigmaPopupMenuUI) super.getComponentUI();
	}
}

package common.hud;

import api.ui.CustomMenuBar;
import common.hud.ui.EnigmaMenuBarUI;

/**
 * Barre de menu du jeu enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaMenuBar extends CustomMenuBar {

	public EnigmaMenuBar() {
		super();
		this.setComponentUI(new EnigmaMenuBarUI());
	}

	@Override
	public EnigmaMenuBarUI getComponentUI() {
		return (EnigmaMenuBarUI) this.ui;
	}
}

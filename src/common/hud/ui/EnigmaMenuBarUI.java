package common.hud.ui;

import api.ui.skin.CustomMenuBarUI;
import data.config.EnigmaUIValues;

/**
 * Style d'une barre de menus de enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaMenuBarUI extends CustomMenuBarUI {

	public EnigmaMenuBarUI() {
		super();
		this.background = EnigmaUIValues.ENIGMA_MENU_BAR_BACKGROUND;
		this.border = EnigmaUIValues.ENIGMA_MENU_BAR_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_MENU_BAR_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_MENU_BAR_SHOWED_BORDERS;
	}

	@Override
	public CustomMenuBarUI duplicate() {
		return duplicate(this);
	}
}

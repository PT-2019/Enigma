package common.hud.ui;

import api.ui.skin.CustomMenuItemUI;
import data.config.EnigmaUIValues;

import java.awt.Cursor;

/**
 * Style de base d'un élément d'un menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public final class EnigmaMenuItemUI extends CustomMenuItemUI {

	public EnigmaMenuItemUI() {
		this.background = EnigmaUIValues.ENIGMA_MENU_ITEM_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_MENU_ITEM_FOREGROUND;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public EnigmaMenuItemUI duplicate() {
		return (EnigmaMenuItemUI) super.duplicate();
	}
}

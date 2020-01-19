package editor.hud.ui;

import api.hud.ui.CustomMenuUI;
import editor.hud.EnigmaUIValues;

import java.awt.Cursor;

/**
 * Apparence d'un menu de l'applcation enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaMenuUI extends CustomMenuUI {

	public EnigmaMenuUI() {
		this.background = EnigmaUIValues.ENIGMA_MENU_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_MENU_HOVERED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_MENU_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_MENU_HOVERED_FOREGROUND;
		this.popupBackground = EnigmaUIValues.ENIGMA_MENU_POPUP_BACKGROUND;
		this.popupBorderSize = EnigmaUIValues.ENIGMA_MENU_POPUP_BORDER_SIZE;
		this.showedPopupBorders = EnigmaUIValues.ENIGMA_MENU_POPUP_SHOWED_BORDER;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public EnigmaMenuUI duplicate() {
		return duplicate(this);
	}
}

package editor.hud.ui;

import api.hud.ui.CustomPopupMenuUI;
import editor.hud.EnigmaUIValues;

/**
 * Style d'un popup de menu de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaPopupMenuUI extends CustomPopupMenuUI {

	public EnigmaPopupMenuUI() {
		this.popupBackground = EnigmaUIValues.ENIGMA_POPUP_MENU_BACKGROUND;
		this.popupBorder = EnigmaUIValues.ENIGMA_POPUP_MENU_BORDER;
		this.popupBorderSize = EnigmaUIValues.ENIGMA_POPUP_MENU_BORDER_SIZE;
		this.showedPopupBorders = EnigmaUIValues.ENIGMA_POPUP_MENU_SHOWED_BORDER;
	}

	@Override
	public CustomPopupMenuUI duplicate() {
		return duplicate(this);
	}
}

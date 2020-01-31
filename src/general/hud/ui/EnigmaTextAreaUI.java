package general.hud.ui;

import api.hud.ui.CustomTextAreaUI;
import data.config.EnigmaUIValues;

import java.awt.Cursor;

/**
 * La style d'un menu de saisie de enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public final class EnigmaTextAreaUI extends CustomTextAreaUI {

	public EnigmaTextAreaUI() {
		this.background = EnigmaUIValues.ENIGMA_TEXTAREA_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BACKGROUND;
		this.focusedBackground = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_TEXTAREA_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_FOREGROUND;
		this.focusedForeground = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_FOREGROUND;
		this.border = EnigmaUIValues.ENIGMA_TEXTAREA_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BORDER;
		this.focusedBorder = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_TEXTAREA_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BORDER_SIZE;
		this.focusedBorderSize = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_SHOWED_BORDERS;
		this.focusedShowedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_SHOWED_BORDERS;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.TEXT_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public CustomTextAreaUI duplicate() {
		return duplicate(this);
	}
}

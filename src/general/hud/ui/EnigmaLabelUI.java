package general.hud.ui;

import api.hud.ui.CustomLabelUI;
import data.config.EnigmaUIValues;

import java.awt.Cursor;

/**
 * Style d'un label de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaLabelUI extends CustomLabelUI {

	/**
	 * Crée l'ui des label d'enigma
	 */
	public EnigmaLabelUI() {
		this.background = EnigmaUIValues.ENIGMA_LABEL_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BACKGROUND;
		this.pressedBackground = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_LABEL_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_LABEL_HOVERED_FOREGROUND;
		this.pressedForeground = EnigmaUIValues.ENIGMA_LABEL_PRESSED_FOREGROUND;
		this.border = EnigmaUIValues.ENIGMA_LABEL_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BORDER;
		this.pressedBorder = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_LABEL_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_LABEL_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_LABEL_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = EnigmaUIValues.ENIGMA_LABEL_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.pressed = false;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;
	}

	@Override
	public CustomLabelUI duplicate() {
		return duplicate(this);
	}
}

package common.hud.ui;

import api.ui.skin.CustomButtonUI;
import data.config.EnigmaUIValues;

import java.awt.Cursor;

/**
 * Le style d'un bouton de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaButtonUI extends CustomButtonUI {

	public EnigmaButtonUI() {
		this.background = EnigmaUIValues.ENIGMA_BUTTON_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BACKGROUND;
		this.pressedBackground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_BUTTON_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND;
		this.pressedForeground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_FOREGROUND;
		this.border = EnigmaUIValues.ENIGMA_BUTTON_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER;
		this.pressedBorder = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_BUTTON_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;

		this.selectedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BACKGROUND;
		this.selectedHoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BACKGROUND;
		this.selectedPressedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BACKGROUND;
		this.selectedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_FOREGROUND;
		this.selectedHoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_FOREGROUND;
		this.selectedPressedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_FOREGROUND;
		this.selectedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER;
		this.selectedHoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER;
		this.selectedPressedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER;
		this.selectedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER_SIZE;
		this.selectedHoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER_SIZE;
		this.selectedPressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER_SIZE;
		this.selectedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_SHOWED_BORDERS;
		this.selectedHoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS;
		this.selectedPressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS;
		this.selected = false;
		this.selectedCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.selectedBackgroundImage = null;
		this.selectedHoveredBackgroundImage = null;
		this.selectedPressedBackgroundImage = null;

		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public CustomButtonUI duplicate() {
		return duplicate(this);
	}
}

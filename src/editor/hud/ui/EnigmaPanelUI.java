package editor.hud.ui;

import api.hud.ui.CustomPanelUI;
import editor.hud.EnigmaUIValues;

import java.awt.Cursor;

/**
 * Style d'un panneau de enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaPanelUI extends CustomPanelUI {

	public EnigmaPanelUI() {
		super();
		this.background = EnigmaUIValues.ENIGMA_PANEL_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BACKGROUND;
		this.pressedBackground = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BACKGROUND;
		this.border = EnigmaUIValues.ENIGMA_PANEL_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BORDER;
		this.pressedBorder = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_PANEL_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_PANEL_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_PANEL_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = EnigmaUIValues.ENIGMA_PANEL_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.pressed = false;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;
	}

	@Override
	public EnigmaPanelUI duplicate() {
		return duplicate(this);
	}
}

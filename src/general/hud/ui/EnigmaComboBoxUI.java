package general.hud.ui;

import api.hud.ui.CustomComboBoxUI;
import data.config.EnigmaUIValues;

import java.awt.Color;
import java.awt.Cursor;

/**
 * Style d'une liste déroulante de enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class EnigmaComboBoxUI extends CustomComboBoxUI {

	public EnigmaComboBoxUI() {
		this.border = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_COMBOBOX_SHOWED_BORDERS;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;

		this.labelUI = new EnigmaLabelUI();
		this.labelUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		this.buttonUI = new EnigmaButtonUI();
		Color color = new Color(100, 100, 100);
		this.buttonUI.setAllBackgrounds(color, color, color);
		this.buttonUI.setAllBorders(Color.WHITE, Color.WHITE, Color.WHITE);
		boolean[] sb = new boolean[4];
		boolean[] shb = new boolean[4];
		boolean[] spb = new boolean[4];
		sb[EnigmaUIValues.LEFT_BORDER] = true;
		shb[EnigmaUIValues.LEFT_BORDER] = true;
		spb[EnigmaUIValues.LEFT_BORDER] = true;
		this.buttonUI.setAllShowedBorders(sb, shb, spb);
		this.buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		this.popupUI = new EnigmaPopupMenuUI();
		this.popupUI.setPopupBorder(Color.WHITE);
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.TOP_BORDER] = true;
		this.popupUI.setShowedPopupBorders(borders);
	}

	@Override
	public CustomComboBoxUI duplicate() {
		return duplicate(this);
	}

	@Override
	public EnigmaButtonUI getButtonUI() {
		return (EnigmaButtonUI) super.getButtonUI();
	}

	@Override
	public EnigmaLabelUI getLabelUI() {
		return (EnigmaLabelUI) super.getLabelUI();
	}

	@Override
	public EnigmaPopupMenuUI getPopupUI() {
		return (EnigmaPopupMenuUI) super.getPopupUI();
	}
}

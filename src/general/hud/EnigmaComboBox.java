package general.hud;

import api.hud.CustomComboBox;
import general.hud.ui.EnigmaComboBoxUI;

/**
 * Une liste déroulante de enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaComboBox extends CustomComboBox {

	/**
	 * Crée une liste vide
	 */
	public EnigmaComboBox() {
		this(new EnigmaPopupMenu());
	}

	/**
	 * Liste avec le popup choisi
	 *
	 * @param popup un popup
	 */
	public EnigmaComboBox(EnigmaPopupMenu popup) {
		super(false);
		this.popup = popup;
		this.label = new EnigmaLabel();
		this.button = new EnigmaButton("v");
		this.selected = null;
		this.init();
		this.setComponentUI(new EnigmaComboBoxUI());
	}

	@Override
	public EnigmaPopupMenu getPopup() {
		return (EnigmaPopupMenu) super.getPopup();
	}

	@Override
	public EnigmaLabel getLabel() {
		return (EnigmaLabel) super.getLabel();
	}

	@Override
	public EnigmaButton getButton() {
		return (EnigmaButton) super.getButton();
	}

	@Override
	public EnigmaComboBoxUI getComponentUI() {
		return (EnigmaComboBoxUI) super.getComponentUI();
	}
}

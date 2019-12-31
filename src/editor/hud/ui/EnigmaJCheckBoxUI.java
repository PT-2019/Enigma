package editor.hud.ui;

import editor.hud.EnigmaUIValues;

import javax.swing.JCheckBox;
import javax.swing.plaf.basic.BasicCheckBoxUI;

/**
 * Une classe de style pour les checkbox fait rapidement
 * mais l'idéal serait une implementation du style libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public final class EnigmaJCheckBoxUI extends BasicCheckBoxUI {

	private EnigmaJCheckBoxUI() {
	}

	public static EnigmaJCheckBoxUI createUI(JCheckBox c) {
		EnigmaJCheckBoxUI ui = new EnigmaJCheckBoxUI();

		c.setBackground(EnigmaUIValues.ENIGMA_BUTTON_BACKGROUND);
		c.setForeground(EnigmaUIValues.ENIGMA_BUTTON_FOREGROUND);
		c.setFont(EnigmaUIValues.ENIGMA_FONT);

		return ui;
	}
}

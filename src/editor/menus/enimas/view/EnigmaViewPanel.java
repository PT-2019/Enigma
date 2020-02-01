package editor.menus.enimas.view;

import common.hud.EnigmaPanel;

/**
 * Toute les classes gérant les panneaux de EnigmaView héritent de cette classe.
 *
 * @see EnigmaView
 */
public abstract class EnigmaViewPanel extends EnigmaPanel {
	protected EnigmaView parent;

	public EnigmaViewPanel(EnigmaView parent) {
		this.parent = parent;
	}

	public EnigmaView getFrame() {
		return parent;
	}
}

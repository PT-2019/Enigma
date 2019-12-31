package editor.hud;

import api.hud.components.CustomPanel;
import editor.hud.ui.EnigmaPanelUI;

/**
 * Un panneau de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaPanel extends CustomPanel {

	public EnigmaPanel() {
		super();
		this.setComponentUI(new EnigmaPanelUI());
	}

	@Override
	public EnigmaPanelUI getComponentUI() {
		return (EnigmaPanelUI) this.ui;
	}
}

package api.ui.manager;

import api.ui.CustomPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Observateur et manager des états des panneaux
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.2
 * @since 3.0
 */
public final class CustomPanelManager extends MouseAdapter {

	private CustomPanel panel;

	public CustomPanelManager(CustomPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if (this.panel.getComponentUI() != null) {
			this.panel.getComponentUI().setIsPressed(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (this.panel.getComponentUI() != null) {
			this.panel.getComponentUI().setIsPressed(false);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.panel.getComponentUI() != null) {
			this.panel.getComponentUI().setIsHovered(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.panel.getComponentUI() != null) {
			this.panel.getComponentUI().setIsHovered(false);
			this.panel.repaint();
		}
	}
}
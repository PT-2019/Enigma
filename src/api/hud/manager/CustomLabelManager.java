package api.hud.manager;

import api.hud.CustomLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Desc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public final class CustomLabelManager extends MouseAdapter {

	private CustomLabel label;

	public CustomLabelManager(CustomLabel label) {
		this.label = label;
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if (this.label.getComponentUI() != null) {
			this.label.getComponentUI().setIsPressed(true);
			this.label.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (this.label.getComponentUI() != null) {
			this.label.getComponentUI().setIsPressed(false);
			this.label.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.label.getComponentUI() != null) {
			this.label.getComponentUI().setIsHovered(true);
			this.label.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.label.getComponentUI() != null) {
			this.label.getComponentUI().setIsHovered(false);
			this.label.repaint();
		}
	}
}

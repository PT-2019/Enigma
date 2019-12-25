package editor.hud.managers;

import editor.hud.EnigmaPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaPanelManager implements MouseListener {

	private EnigmaPanel panel;

	public EnigmaPanelManager(EnigmaPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if (this.panel.getPanelUI() != null) {
			this.panel.getPanelUI().setIsPressed(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (this.panel.getPanelUI() != null) {
			this.panel.getPanelUI().setIsPressed(false);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.panel.getPanelUI() != null) {
			this.panel.getPanelUI().setIsHovered(true);
			this.panel.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.panel.getPanelUI() != null) {
			this.panel.getPanelUI().setIsHovered(false);
			this.panel.repaint();
		}
	}
}

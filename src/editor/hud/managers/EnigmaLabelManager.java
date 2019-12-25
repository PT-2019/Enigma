package editor.hud.managers;

import editor.hud.EnigmaLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaLabelManager implements MouseListener {

	private EnigmaLabel label;

	public EnigmaLabelManager(EnigmaLabel label) {
		this.label = label;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if (this.label.getLabelUI() != null) {
			this.label.getLabelUI().setIsPressed(true);
			this.label.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (this.label.getLabelUI() != null) {
			this.label.getLabelUI().setIsPressed(false);
			this.label.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.label.getLabelUI() != null) {
			this.label.getLabelUI().setIsHovered(true);
			this.label.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.label.getLabelUI() != null) {
			this.label.getLabelUI().setIsHovered(false);
			this.label.repaint();
		}
	}
}

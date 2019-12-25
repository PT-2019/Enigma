package editor.hud.managers;

import editor.hud.EnigmaLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment EnigmaLabelManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
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

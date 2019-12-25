package editor.hud.managers;

import editor.hud.EnigmaTextArea;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment EnigmaTextAreaManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaTextAreaManager implements MouseListener, FocusListener {

	private EnigmaTextArea textArea;

	public EnigmaTextAreaManager(EnigmaTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.textArea.getTextAreaUI() != null) {
			this.textArea.getTextAreaUI().setIsHovered(true);
			this.textArea.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.textArea.getTextAreaUI() != null) {
			this.textArea.getTextAreaUI().setIsHovered(false);
			this.textArea.repaint();
		}
	}

	@Override
	public void focusGained(FocusEvent focusEvent) {
		this.textArea.repaint();
	}

	@Override
	public void focusLost(FocusEvent focusEvent) {
		this.textArea.repaint();
	}
}

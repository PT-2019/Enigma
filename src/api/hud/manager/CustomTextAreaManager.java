package api.hud.manager;

import api.hud.components.CustomTextArea;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Manager du survol, et des clics sur la zone de saisie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomTextAreaManager extends MouseAdapter implements FocusListener {

	private CustomTextArea textArea;

	public CustomTextAreaManager(CustomTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.textArea.getComponentUI() != null) {
			this.textArea.getComponentUI().setIsHovered(true);
			this.textArea.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.textArea.getComponentUI() != null) {
			this.textArea.getComponentUI().setIsHovered(false);
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

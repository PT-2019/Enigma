package editor.hud.managers;

import editor.hud.EnigmaButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment EnigmaButtonManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaButtonManager implements MouseListener {

	private EnigmaButton button;

	public EnigmaButtonManager(EnigmaButton button) {
		this.button = button;
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
		if (this.button.getButtonUI() != null) this.button.getButtonUI().setIsHovered(true);
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.button.getButtonUI() != null) this.button.getButtonUI().setIsHovered(false);
	}
}

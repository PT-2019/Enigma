package editor.hud.managers;

import editor.hud.EnigmaComboBox;
import editor.hud.EnigmaPopupMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment EnigmaComboBoxManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaComboBoxManager implements MouseListener {

	private EnigmaComboBox box;

	public EnigmaComboBoxManager(EnigmaComboBox box) {
		this.box = box;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		EnigmaPopupMenu popup = this.box.getPopup();
		popup.show(this.box, 0, this.box.getHeight());
		popup.setPopupSize(this.box.getWidth(), popup.getHeight());
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
	}
}

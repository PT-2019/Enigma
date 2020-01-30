package api.hud.manager;

import api.hud.CustomComboBox;
import api.hud.CustomPopupMenu;
import api.utils.annotations.NeedPatch;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Observateur et manager d'une combo box (liste déroulante)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class ComboBoxManager extends MouseAdapter {

	private CustomComboBox box;

	public ComboBoxManager(CustomComboBox box) {
		this.box = box;
	}

	@NeedPatch
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		CustomPopupMenu popup = this.box.getPopup();
		popup.show(this.box, 0, this.box.getHeight());
		popup.setPopupSize(this.box.getWidth(), popup.getHeight());
	}
}

package api.hud.manager;

import api.hud.CustomComboBox;
import api.hud.CustomMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observateur des éléments d'une liste déroulante
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomComboBoxItemManager implements ActionListener {

	private CustomComboBox box;

	public CustomComboBoxItemManager(CustomComboBox box) {
		this.box = box;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.box.setSelected((CustomMenuItem) actionEvent.getSource());
	}
}

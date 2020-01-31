package api.hud.manager;

import api.hud.CustomButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Observateur de la sélection d'un bouton
 * sur une gamme de plusieurs.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class SelectedManager extends MouseAdapter {

	private MultipleButtonManager manager;

	public SelectedManager(MultipleButtonManager manager) {
		this.manager = manager;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		this.manager.setSelected((CustomButton) mouseEvent.getSource());
	}
}

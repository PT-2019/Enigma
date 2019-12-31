package api.hud.manager.choices;

import editor.hud.EnigmaButton;

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
public class SelectedManager extends MouseAdapter {

	private MultipleButtonManager manager;

	public SelectedManager(MultipleButtonManager manager) {
		this.manager = manager;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		this.manager.setSelected((EnigmaButton) mouseEvent.getSource());
	}
}

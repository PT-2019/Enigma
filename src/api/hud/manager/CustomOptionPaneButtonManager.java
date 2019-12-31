package api.hud.manager;

import api.hud.components.CustomButton;
import api.hud.components.CustomOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui observe les boutons d'un CustomOptionPane
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomOptionPaneButtonManager implements ActionListener {

	private CustomOptionPane optionPane;

	public CustomOptionPaneButtonManager(CustomOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.optionPane.answer(((CustomButton) actionEvent.getSource()).getText());
	}
}
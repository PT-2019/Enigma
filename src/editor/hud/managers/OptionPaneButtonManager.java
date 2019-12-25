package editor.hud.managers;

import editor.hud.EnigmaButton;
import editor.hud.EnigmaOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment OptionPaneButtonManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class OptionPaneButtonManager implements ActionListener {

	private EnigmaOptionPane optionPane;

	public OptionPaneButtonManager(EnigmaOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.optionPane.answer(((EnigmaButton) actionEvent.getSource()).getText());
	}
}

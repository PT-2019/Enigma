package editor.hud.managers;

import editor.hud.EnigmaButton;
import editor.hud.EnigmaOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPaneButtonManager implements ActionListener {

	private EnigmaOptionPane optionPane;

	public OptionPaneButtonManager(EnigmaOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.optionPane.setHaveAnswered(true, ((EnigmaButton) actionEvent.getSource()).getText());
		this.optionPane.close();
	}
}

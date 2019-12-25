package editor.hud.managers;

import editor.hud.EnigmaComboBox;
import editor.hud.EnigmaMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment ComboBoxItemManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ComboBoxItemManager implements ActionListener {

	private EnigmaComboBox box;

	public ComboBoxItemManager(EnigmaComboBox box) {
		this.box = box;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.box.setSelected((EnigmaMenuItem) actionEvent.getSource());
	}
}

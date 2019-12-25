package editor.hud.managers;

import editor.hud.EnigmaComboBox;
import editor.hud.EnigmaMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

package editor.hud.managers;

import editor.hud.EnigmaButton;
import editor.hud.ui.EnigmaButtonUI;

public class RadioButtonManager implements MultipleButtonManager {

	private EnigmaButton selected;

	public RadioButtonManager() {
		this.selected = null;
	}

	@Override
	public void add(EnigmaButton b) {
		if (b.getButtonUI() == null) b.setButtonUI(new EnigmaButtonUI());
		b.addMouseListener(new SelectedManager(this));
	}

	@Override
	public EnigmaButton[] getSelected() {
		EnigmaButton[] tab = new EnigmaButton[1];
		tab[0] = this.selected;
		return tab;
	}

	@Override
	public void setSelected(EnigmaButton b) {
		if (this.selected != null) {
			this.selected.getButtonUI().setIsSelected(false);
			this.selected.repaint();
		}
		b.getButtonUI().setIsSelected(true);
		b.repaint();
		this.selected = b;
	}

	public EnigmaButton getSelectedButton() {
		return this.selected;
	}
}

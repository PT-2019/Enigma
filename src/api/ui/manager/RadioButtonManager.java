package api.ui.manager;

import api.ui.CustomButton;
import api.ui.skin.CustomButtonUI;

/**
 * Observateur des interactions avec des boutons radio
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class RadioButtonManager implements MultipleButtonManager {

	private CustomButton selected;

	public RadioButtonManager() {
		this.selected = null;
	}

	@Override
	public void add(CustomButton b) {
		if (b.getComponentUI() == null) b.setComponentUI(new CustomButtonUI());
		b.addMouseListener(new SelectedManager(this));
	}

	@Override
	public CustomButton[] getSelected() {
		CustomButton[] tab = new CustomButton[1];
		tab[0] = this.selected;
		return tab;
	}

	@Override
	public void setSelected(CustomButton b) {
		if (this.selected != null) {
			this.selected.getComponentUI().setIsSelected(false);
			this.selected.repaint();
		}
		b.getComponentUI().setIsSelected(true);
		b.repaint();
		this.selected = b;
	}

	/**
	 * Retourne le bouton sélectionné
	 *
	 * @return le bouton sélectionné
	 */
	public CustomButton getSelectedButton() {
		return this.selected;
	}
}

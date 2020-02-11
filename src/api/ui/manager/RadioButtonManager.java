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
 * @version 6.0
 * @since 3.0
 */
public final class RadioButtonManager implements MultipleButtonManager {

	private final boolean setFirstSelected;
	private CustomButton selected;

	/**
	 * Observateur des interactions avec des boutons radio
	 */
	public RadioButtonManager() {
		this.selected = null;
		this.setFirstSelected = false;
	}

	/**
	 * Observateur des interactions avec des boutons radio
	 * @param setFirstSelected premier est sélectionné
	 */
	public RadioButtonManager(boolean setFirstSelected) {
		this.setFirstSelected = setFirstSelected;
		this.selected = null;
	}

	@Override
	public void add(CustomButton b) {
		if (b.getComponentUI() == null) b.setComponentUI(new CustomButtonUI());
		if(selected == null && setFirstSelected) setSelected(b);
		b.addMouseListener(new SelectedManager(this));
	}

	@Override
	public CustomButton[] getSelected() {
		CustomButton[] tab = new CustomButton[1];
		tab[0] = this.selected;
		return tab;
	}

	/**
	 * Appelée automatiquement lorsque l'on appuie sur un bouton
	 * @param b un bouton
	 */
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

package api.ui.manager;

import api.ui.CustomButton;
import api.ui.skin.CustomButtonUI;

import java.util.ArrayList;

/**
 * Observateur des interactions avec des check-box
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CheckBoxManager implements MultipleButtonManager {

	private ArrayList<CustomButton> selected;

	public CheckBoxManager() {
		this.selected = new ArrayList<>();
	}

	@Override
	public void add(CustomButton b) {
		if (b.getComponentUI() == null) b.setComponentUI(new CustomButtonUI());
		b.addActionListener(new SelectedManager(this));
	}

	@Override
	public CustomButton[] getSelected() {
		return (CustomButton[]) this.selected.toArray();
	}

	@Override
	public void setSelected(CustomButton b) {
		if (b.getComponentUI().isSelected()) {
			b.getComponentUI().setIsSelected(false);
			b.repaint();
			this.selected.remove(b);
		} else {
			b.getComponentUI().setIsSelected(true);
			b.repaint();
			this.selected.add(b);
		}
	}
}

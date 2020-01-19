package api.hud.manager.choices;

import api.hud.components.CustomButton;
import api.hud.ui.CustomButtonUI;

import java.util.ArrayList;

/**
 * Observateur des interactions avec des combo-box
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class CheckBoxManager implements MultipleButtonManager {

	private ArrayList<CustomButton> selected;

	public CheckBoxManager() {
		this.selected = new ArrayList<>();
	}

	@Override
	public void add(CustomButton b) {
		if (b.getComponentUI() == null) b.setComponentUI(new CustomButtonUI());
		b.addMouseListener(new SelectedManager(this));
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

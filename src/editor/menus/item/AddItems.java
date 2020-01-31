package editor.menus.item;

import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.enimas.listeners.EnigmaWindowListener;
import editor.popup.cases.CasePopUp;
import general.entities.GameObject;
import general.hud.EnigmaButton;
import general.hud.EnigmaPanel;

/**
 * Permet d'ajouter des objets a un conteneur.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class AddItems implements AvailableOptionRunnable {

	private static final String ADD_ITEMS = "Ajouter des objets";
	/**
	 * Parent
	 */
	private final CasePopUp parent;
	/**
	 * Bouton d'ajout d'objets
	 */
	private EnigmaButton addItems;

	public AddItems(CasePopUp parent) {
		this.parent = parent;
		this.addItems = new EnigmaButton(ADD_ITEMS);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.ADD_ITEMS;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.addItems);
		this.addItems.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddItemView addItemView = new AddItemView(this.parent);
			addItemView.setVisible(true);
			addItemView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {
		panel.add(this.addItems);
		this.addItems.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddItemView addItemView = new AddItemView(this.parent);
			addItemView.setVisible(true);
			addItemView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}
}

package editor.view.listeners.available;

import api.entity.GameObject;
import api.enums.AvailablePopUpOption;
import editor.enigma.create.listeners.EnigmaWindowListener;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaPanel;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;
import editor.view.listeners.available.view.item.AddItemView;

/**
 * Permet d'ajouter des objets a un conteneur.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class AddItems implements AvailableOptionRunnable {

	private static final String ADD_ITEMS = "Ajouter des objets";

	/**
	 * Bouton d'ajout d'objets
	 */
	private EnigmaButton addItems;

	/**
	 * Parent
	 */
	private final CasePopUp parent;

	public AddItems(CasePopUp parent){
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

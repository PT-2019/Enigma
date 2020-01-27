package editor.view.listeners.available.view.item;

import editor.view.cases.CasePopUp;
import editor.view.listeners.available.view.AbstractPopUpView;

/**
 * Element principal dans la création de l'enigme, contient toute les informations nécessaire
 *
 * @see CasePopUp
 */
public class AddItemView extends AbstractPopUpView {

	public static final int WIDTH = 700, HEIGHT = 300;
	public static final String MENU = "menu";
	public static final String ADD = "add";

	public AddItemView(CasePopUp popUp) {
		super("Gérer les objets", popUp);

		AddItemAddView addItemAddView = new AddItemAddView(this);
		popUp.getObserver().addObserveur(addItemAddView);

		panel.add(new AddItemListView(this), MENU);
		panel.add(addItemAddView, ADD);
	}
}

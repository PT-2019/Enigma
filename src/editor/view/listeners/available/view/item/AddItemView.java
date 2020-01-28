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
	public static final String SEE = "see";
	private final AddItemAddView addItemAddView;

	public AddItemView(CasePopUp popUp) {
		super("Gérer les objets", popUp);

		AddItemSeeView addItemSeeView = new AddItemSeeView(this);
		AddItemListView addItemListView = new AddItemListView(this, addItemSeeView);
		addItemAddView = new AddItemAddView(this, addItemListView);
		popUp.getObserver().addObserveur(addItemAddView);

		panel.add(addItemListView, MENU);
		panel.add(addItemAddView, ADD);
		panel.add(addItemSeeView, SEE);
	}

	public AddItemAddView getAddItemAddView() {
		return addItemAddView;
	}

	@Override
	public void clean() {
		addItemAddView.clean();
	}
}

package editor.menus.item;

import editor.menus.AbstractPopUpView;
import editor.popup.cases.CasePopUp;

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
	private static final String TITLE = "Gérer les objets";
	private final AddItemAddView addItemAddView;

	public AddItemView(CasePopUp popUp) {
		super(TITLE, popUp);

		AddItemSeeView addItemSeeView = new AddItemSeeView(this);
		AddItemListView addItemListView = new AddItemListView(this, addItemSeeView);
		addItemAddView = new AddItemAddView(this, addItemListView);
		popUp.getObserver().addObserver(addItemAddView);

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

package editor.menus.item;

import editor.menus.AbstractPopUpView;
import editor.popup.cases.CasePopUp;

/**
 * Ajout d'objects dans un object
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0
 * @since 5.0
 */
public class AddItemView extends AbstractPopUpView {

	private static final String TITLE = "Gérer les objets";

	static final String MENU = "menu";
	static final String ADD = "add";
	static final String SEE = "see";

	/**
	 * Stockage des écrans
	 */
	private final AddItemAddView addItemAddView;
	private final AddItemListView addItemListView;

	public AddItemView(CasePopUp popUp) {
		super(TITLE, popUp);

		//ajout des écrans
		AddItemSeeView addItemSeeView = new AddItemSeeView(this);
		this.addItemListView = new AddItemListView(this, addItemSeeView);
		this.addItemAddView = new AddItemAddView(this, this.addItemListView);

		//ajout à l'observateur pour la sélection des entités
		popUp.getObserver().addObserver(this.addItemAddView);

		//ajout au card Layout
		this.panel.add(this.addItemListView, MENU);
		this.panel.add(this.addItemAddView, ADD);
		this.panel.add(addItemSeeView, SEE);
	}

	/**
	 * Retourne la vue pour ajouter des items
	 * @return la vue pour ajouter des items
	 */
	public AddItemAddView getAddItemAddView() {
		return this.addItemAddView;
	}

	@Override
	public void clean() {
		this.addItemAddView.clean();
	}

	@Override
	public void invalidateDrawable() {
		addItemListView.clean();
		addItemListView.initComponent();
	}
}

package editor.menus.enimas;

import common.entities.GameObject;
import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.ManageEnigmasAddView;
import editor.popup.cases.CasePopUp;

/**
 * Ajout d'objects dans un object
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class ManageEnigmasView extends AbstractPopUpView {

	private static final String TITLE = "Gérer les énigmes";

	/**
	 * CardLayout
	 */
	static final String MENU = "menu", ADD = "add", SEE = "see";

	/**
	 * Stockage des écrans
	 */
	private final ManageEnigmasAddView manageEnigmasAddView;
	private final ManageEnigmasListView manageEnigmasListView;

	public ManageEnigmasView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);

		if(object == null) object = popUp.getCell().getEntity();

		//ajout des écrans
		ManageEnigmasSeeView manageEnigmasSeeView = new ManageEnigmasSeeView(this);
		this.manageEnigmasListView = new ManageEnigmasListView(this, manageEnigmasSeeView, object);
		this.manageEnigmasAddView = new ManageEnigmasAddView(this, object);

		//ajout au card Layout
		this.panel.add(this.manageEnigmasListView, MENU);
		this.panel.add(this.manageEnigmasAddView, ADD);
		this.panel.add(manageEnigmasSeeView, SEE);
	}

	/**
	 * Retourne la vue pour ajouter des items
	 * @return la vue pour ajouter des items
	 */
	public ManageEnigmasAddView getManageEnigmaAddView() {
		return this.manageEnigmasAddView;
	}

	@Override
	public void clean() {
		this.manageEnigmasAddView.clean();
	}

	@Override
	public void invalidateDrawable() {
		this.manageEnigmasListView.clean();
		this.manageEnigmasListView.initComponent();
	}
}

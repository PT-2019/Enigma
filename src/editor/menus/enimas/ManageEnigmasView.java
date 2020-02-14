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
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class ManageEnigmasView extends AbstractPopUpView {

	/**
	 * CardLayout
	 */
	public static final String MENU = "menu";
	static final String ADD = "add";
	static final String SEE = "see";
	private static final String TITLE = "Gérer les énigmes";
	private static ManageEnigmasView instance;
	/**
	 * Stockage des écrans
	 */
	private final ManageEnigmasAddView addView;
	private final ManageEnigmasListView listView;

	public ManageEnigmasView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);
		instance = this;

		if (object == null) object = popUp.getCell().getEntity();

		//ajout des écrans
		this.addView = new ManageEnigmasAddView(this, object);
		ManageEnigmasSeeView manageEnigmasSeeView = new ManageEnigmasSeeView(this, object);
		this.listView = new ManageEnigmasListView(this, manageEnigmasSeeView, object);

		//ajout au card Layout
		this.panel.add(this.listView, MENU);
		this.panel.add(this.addView, ADD);
		this.panel.add(manageEnigmasSeeView, SEE);
	}

	public static ManageEnigmasView getInstance() {
		return instance;
	}

	@Override
	public void clean() {
		this.addView.clean();
	}

	@Override
	public void invalidateDrawable() {
		this.listView.clean();
		this.listView.initComponent();
	}
}

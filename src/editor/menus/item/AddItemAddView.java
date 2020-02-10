package editor.menus.item;

import api.utils.Observer;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.NullGameObjet;
import common.entities.types.Container;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.map.MapTestScreen;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.EditorAction;
import editor.bar.edition.actions.EditorActionFactory;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.popup.listeners.CaseListener;
import game.EnigmaGame;
import game.dnd.DragAndDropBuilder;
import game.screens.TestScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vue du menu d'ajout d'un item
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class AddItemAddView extends AbstractSubPopUpView implements Observer<GameObject> {

	private static final String TITLE = "Ajouter un objet";
	private static final String NO_ITEMS = "Veuillez sélectionner un objet (menu)";
	private static final String INVALID = "Entité Invalide. Objets Uniquement (livre, ...)";
	private static final String SUMBIT = "Confimer";

	private final EnigmaButton submit;
	private final EnigmaLabel empty;
	private GameObject item;

	AddItemAddView(AbstractPopUpView parent, AddItemListView list) {
		super(TITLE, parent, true);

		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridBagLayout());
		this.empty = new EnigmaLabel(NO_ITEMS);
		this.empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		panel.add(this.empty);

		this.submit = new EnigmaButton(SUMBIT);
		this.submit.setVisible(false);
		this.submit.addActionListener(new SubmitListener(parent, this, list));
		this.footer.add(this.submit);

		this.content.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void update(GameObject object) {
		if (CaseListener.getAvailable() != null) return;
		//PrintColor.println("update#"+object, AnsiiColor.CYAN);
		//Récupération de la map
		MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();

		if (!(object instanceof Consumable)) {
			this.empty.setText(INVALID);
			this.submit.setVisible(false);
			if (this.item != null) {
				//supprime
				map.removeEntity(this.item);
				this.item = null;
				return;
			}
			if (object == null) return;
			//PrintColor.println("#delete"+object, AnsiiColor.CYAN);
			//supprime de la map
			map.removeEntity(object);
			//System.out.println("#"+map.getEntities().size());
		} else {
			//System.out.println("#item:"+item);
			if (this.item != null) {
				//System.out.println("JE TOUCHE AUX IDS"+this.item.getID()+" "+object.getID());
				//supprime l'ancien
				map.removeEntity(this.item);
				//transfert id
				object.setID(this.item.getID());
			}
			this.item = object;
			//System.out.println("#item:"+item);
			this.empty.setText(object.getReadableName() + " (id=" + object.getID() + ")");
			this.submit.setVisible(true);
		}
	}

	@Override
	public void clean() {
		this.update(new NullGameObjet());
		DragAndDropBuilder.setForPopup(null);
		this.empty.setText(NO_ITEMS);
	}

	@Override
	public void initComponent() {
	}

	/**
	 * Bouton de validation
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 02/02/2020
	 * @since 6.0 02/02/2020
	 */
	private static class SubmitListener implements ActionListener {
		private final AbstractPopUpView parent;
		private final AddItemAddView addItemAddView;
		private final AddItemListView list;

		SubmitListener(AbstractPopUpView parent, AddItemAddView addItemAddView, AddItemListView list) {
			this.parent = parent;
			this.addItemAddView = addItemAddView;
			this.list = list;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			GameObject entity = this.parent.getPopUp().getCell().getEntity();
			if (entity instanceof Container) {
				//ajout au container
				((Container) entity).addItem((Item) addItemAddView.item);

				//ajout à l'historique
				EditorAction action = EditorActionFactory.actionWithinAMenu(ActionTypes.ADD_SUB_ENTITY,
						entity, addItemAddView.item);
				ActionsManager.getInstance().add(action);

				//clean
				this.addItemAddView.item = null;
				this.addItemAddView.submit.setVisible(false);
				this.addItemAddView.empty.setText(NO_ITEMS);
				DragAndDropBuilder.setForPopup(null);
				//update parent
				this.list.clean();
				this.list.initComponent();
				//retour menu
				this.parent.getCardLayout().show(parent.getPanel(), "menu");
			}
		}
	}
}
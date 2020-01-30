package editor.menus.item;

import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import game.EnigmaGame;
import game.dnd.DragAndDropBuilder;
import game.screens.TestScreen;
import general.entities.Consumable;
import general.entities.GameObject;
import general.entities.Item;
import general.entities.types.Container;
import general.hud.EnigmaButton;
import general.hud.EnigmaLabel;
import general.hud.EnigmaPanel;
import general.map.MapTestScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemAddView extends AbstractSubPopUpView {

	private static final String TITLE = "Ajouter un objet";
	private static final String NO_ITEMS = "Veuillez sélectionner un objet (menu)";
	private static final String INVALID = "Entité Invalide. Objets Uniquement (livre, ...)";
	private static final String SUMBIT = "Confimer";
	private final EnigmaButton submit;
	private final EnigmaLabel empty;
	private GameObject item;

	public AddItemAddView(AbstractPopUpView parent, AddItemListView list) {
		super(TITLE, parent, true);

		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridBagLayout());
		empty = new EnigmaLabel(NO_ITEMS);
		empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		panel.add(empty);

		submit = new EnigmaButton(SUMBIT);
		submit.setVisible(false);
		submit.addActionListener(new SubmitListener(parent, this, list));
		footer.add(submit);

		this.content.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void update(GameObject object) {
		//PrintColor.println("update#"+object, AnsiiColor.CYAN);
		//Récupération de la map
		MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();

		if (!(object instanceof Consumable)) {
			empty.setText(INVALID);
			submit.setVisible(false);
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
			item = object;
			//System.out.println("#item:"+item);
			empty.setText(object.getReadableName() + " (id=" + object.getID() + ")");
			submit.setVisible(true);
		}
	}

	@Override
	public void clean() {
		GameObject objNull = null;
		this.update(objNull);
		DragAndDropBuilder.setForPopup(null);
		empty.setText(NO_ITEMS);
	}

	@Override
	public void onHide() {
	}

	@Override
	public void onShow() {
	}

	@Override
	public void initComponent() {
	}

	public EnigmaLabel getInfoLabel() {
		return this.infoLabel;
	}

	private static class SubmitListener implements ActionListener {
		private final AbstractPopUpView parent;
		private final AddItemAddView addItemAddView;
		private final AddItemListView list;

		public SubmitListener(AbstractPopUpView parent, AddItemAddView addItemAddView, AddItemListView list) {
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
				//clean
				addItemAddView.item = null;
				addItemAddView.submit.setVisible(false);
				addItemAddView.empty.setText(NO_ITEMS);
				DragAndDropBuilder.setForPopup(null);
				//update parent
				list.clean();
				list.initComponent();
				//retour menu
				parent.getCardLayout().show(parent.getPanel(), "menu");
			}
		}
	}
}
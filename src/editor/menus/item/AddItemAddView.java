package editor.menus.item;

import api.utils.Observer;
import com.badlogic.gdx.math.Vector2;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Container;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.map.MapTestScreen;
import data.NeedToBeTranslated;
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
import org.jetbrains.annotations.Nullable;

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

	/**
	 * Textes
	 */
	private static final String TITLE = NeedToBeTranslated.ADD_ITEM;
	private static final String NO_ITEMS = NeedToBeTranslated.ASK_SELECT+" ("+NeedToBeTranslated.MENU_ONLY+")";
	private static final String INVALID = NeedToBeTranslated.INVALID_ENTITY+" "+NeedToBeTranslated.ITEMS_ONLY;
	private static final String SUBMIT = NeedToBeTranslated.CONFIRM;

	/**
	 * valider
	 */
	private final EnigmaButton submit;
	/**
	 * item choisi (vue)
	 */
	private final EnigmaLabel selectedItem;
	/**
	 * item choisi (model)
	 */
	private GameObject item;

	/**
	 * True si la sélection a étée validée sinon false.
	 * @see #free(GameObject, MapTestScreen)
	 */
	private boolean submitted;

	/**
	 * Vue du menu d'ajout d'un item
	 * @param parent parent
	 * @param list liste des items
	 */
	AddItemAddView(AbstractPopUpView parent, AddItemListView list) {
		super(TITLE, parent, true);

		//attributs
		this.submitted =  false;
		this.item = null;

		//label de l'item choisi
		this.selectedItem = new EnigmaLabel(AddItemAddView.NO_ITEMS);
		this.selectedItem.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);

		//bouton valider
		this.submit = new EnigmaButton(AddItemAddView.SUBMIT);
		this.submit.setVisible(false);
		this.submit.addActionListener(new SubmitListener(parent, this, list));
		this.footer.add(this.submit);

		//ajout au panel d'affichage
		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridBagLayout());
		panel.add(this.selectedItem);

		this.content.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void update(@Nullable GameObject object) {
		if (CaseListener.getAvailable() != null) return;
		//Récupération de la map
		MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();

		//libère l'ancien object, change avec le nouveau
		this.free(object, map);

		//si je peux pas le stocker, alors je le supprime
		if (!(object instanceof Consumable)) {
			//up de l'affichage
			this.selectedItem.setText(INVALID);
			this.submit.setVisible(false);
		} else {
			//up de l'affichage
			this.selectedItem.setText(object.getReadableName() + " (id=" + object.getID() + ")");
			this.submit.setVisible(true);
		}
	}

	/**
	 * Libère un ressource
	 * @param object object
	 * @param map map
	 */
	private void free(@Nullable GameObject object, MapTestScreen map){
		if (this.item != null && !this.submitted) {//si j'avais un object temporaire, je le supprime
			//il existe déjà un object
			Vector2 pos = this.item.getGameObjectPosition();
			//this.object est un temporaire
			if (pos == null || pos.x < 0 || pos.y < 0) {
				//supprime de la map
				int id = this.item.getID();
				map.removeEntity(this.item);
				//l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
				if (object != null && object.getID() > id) {
					//libère l'id
					map.freeId(object);
					object.setID(id);//transfert de l'ID du supprimé*/
				} else {
					//on veut supprimer l'object
					map.freeId(this.item);
				}
			}
		}
		this.item = object;
	}

	@Override
	public void clean() {
		this.update((GameObject) null);
		DragAndDropBuilder.setForPopup(null);
		this.selectedItem.setText(NO_ITEMS);
		this.submitted = false;
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
	private static final class SubmitListener implements ActionListener {
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
				this.addItemAddView.submitted = true;
				this.addItemAddView.clean();

				//update parent
				this.list.clean();
				this.list.initComponent();
				//retour menu
				this.parent.getCardLayout().show(parent.getPanel(), "menu");
			}
		}
	}
}
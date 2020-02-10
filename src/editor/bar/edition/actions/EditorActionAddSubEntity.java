package editor.bar.edition.actions;

import common.entities.Consumable;
import common.entities.types.Container;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;
import editor.menus.item.AddItemView;

/**
 * Ajout d'une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
class EditorActionAddSubEntity implements EditorAction {

	private final ActionTypes type;
	private final Container container;
	private final Consumable consumable;

	EditorActionAddSubEntity(ActionTypes type, Container container, Consumable consumable) {
		this.type = type;
		this.container = container;
		this.consumable = consumable;
	}

	@Override
	public void doAction() {
		this.container.addItem(this.consumable);
		AddItemView instance = AddItemView.getInstance();
		if (instance != null) instance.invalidateDrawable();
	}

	@Override
	public void undoAction() {
		this.container.removeItem(this.consumable);
		AddItemView instance = AddItemView.getInstance();
		if (instance != null) instance.invalidateDrawable();
	}

	@Override
	public ActionTypes getType() {
		return this.type;
	}

	@Override
	public void clear() {
		//rien a faire.
	}

	/**
	 * Retourne l'entité
	 *
	 * @return l'entité
	 */
	@Override
	public Object getActor() {
		return this.consumable;
	}
}


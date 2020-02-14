package editor.bar.edition.actions;

import common.entities.types.Living;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;
import editor.menus.name.AddNameView;

/**
 * Changement du nom
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
class EditorActionSetName implements EditorAction {

	private final ActionTypes type;
	private final Living entity;
	private final String oldName, newName;

	EditorActionSetName(ActionTypes type, Living entity, String oldName) {
		this.type = type;
		this.entity = entity;
		this.oldName = oldName;
		this.newName = entity.getName();
	}

	@Override
	public void doAction() {
		this.entity.setName(this.newName);
		AddNameView instance = AddNameView.getInstance();
		if (instance != null) {
			//restaure le nom
			instance.getPopUp().invalidateDrawable();
			instance.invalidateDrawable();
		}
	}

	@Override
	public void undoAction() {
		this.entity.setName(this.oldName);
		AddNameView instance = AddNameView.getInstance();
		if (instance != null) {
			//restaure le nom
			instance.getPopUp().invalidateDrawable();
			instance.invalidateDrawable();
		}
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
		return this.entity;
	}
}

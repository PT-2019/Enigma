package editor.bar.edition.actions;

import common.entities.types.Content;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;
import editor.menus.content.AddContentView;

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
class EditorActionSetContent implements EditorAction {

	private final ActionTypes type;
	private final Content content;
	private final String oldContent, newContent;

	EditorActionSetContent(ActionTypes type, Content content, String oldContent) {
		this.type = type;
		this.content = content;
		this.oldContent = oldContent;
		this.newContent = content.getContent();
	}

	@Override
	public void doAction() {
		this.content.setContent(this.newContent);
		AddContentView instance = AddContentView.getInstance();
		if(instance != null){
			//restaure le nom
			instance.getPopUp().invalidateDrawable();
			instance.invalidateDrawable();
		}
	}

	@Override
	public void undoAction() {
		this.content.setContent(this.oldContent);
		AddContentView instance = AddContentView.getInstance();
		if(instance != null){
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
		return this.content;
	}
}

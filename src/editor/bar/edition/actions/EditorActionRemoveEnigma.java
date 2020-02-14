package editor.bar.edition.actions;

import common.enigmas.Enigma;
import common.entities.types.EnigmaContainer;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;
import editor.menus.enimas.ManageEnigmasView;

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
class EditorActionRemoveEnigma implements EditorAction {

	private final ActionTypes type;
	private final EnigmaContainer entity;
	private final Enigma enigma;

	EditorActionRemoveEnigma(ActionTypes type, EnigmaContainer entity, Enigma enigma) {
		this.type = type;
		this.entity = entity;
		this.enigma = enigma;
	}

	@Override
	public void doAction() {
		this.entity.removeEnigma(this.enigma);
		ManageEnigmasView instance = ManageEnigmasView.getInstance();
		if (instance != null) instance.invalidateDrawable();
	}

	@Override
	public void undoAction() {
		this.entity.addEnigma(this.enigma);
		ManageEnigmasView instance = ManageEnigmasView.getInstance();
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
		return this.enigma;
	}
}


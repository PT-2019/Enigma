package editor.bar.edition.actions;

import common.enigmas.Enigma;
import common.entities.types.EnigmaContainer;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;

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
	private final Runnable update;

	EditorActionRemoveEnigma(ActionTypes type, EnigmaContainer entity, Enigma enigma, Runnable update) {
		this.type = type;
		this.entity = entity;
		this.enigma = enigma;
		this.update = update;
	}

	@Override
	public void doAction() {
		this.entity.remove(this.enigma);
		this.update.run();
	}

	@Override
	public void undoAction() {
		this.entity.add(this.enigma);
		this.update.run();
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
	 * @return l'entité
	 */
	@Override
	public Object getActor() {
		return this.enigma;
	}
}


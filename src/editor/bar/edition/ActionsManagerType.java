package editor.bar.edition;

import data.NeedToBeTranslated;

/**
 * Actions sur le manager possibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 04/02/2020
 * @since 6.0 04/02/2020
 */
public enum ActionsManagerType {
	ADD(""),
	UNDO(NeedToBeTranslated.UN_DONE),
	REDO(NeedToBeTranslated.RE_DONE);

	private final String value;

	ActionsManagerType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

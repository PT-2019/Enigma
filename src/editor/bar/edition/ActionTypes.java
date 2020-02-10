package editor.bar.edition;

import data.NeedToBeTranslated;

/**
 * Les types d'actions possibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
public enum ActionTypes {

	/**
	 * Ajout d'une entité
	 */
	ADD_ENTITY(NeedToBeTranslated.ADD_ENTITY),

	/**
	 * Suppression d'une entité
	 */
	REMOVE_ENTITY(NeedToBeTranslated.REMOVE_ENTITY),

	/**
	 * Ajoute une énigme
	 */
	ADD_ENIGMA(NeedToBeTranslated.ADD_ENIGMA),

	/**
	 * Suppression d'une énigme
	 * Affiche un message qui confirme la suppression
	 */
	REMOVE_ENIGMA(NeedToBeTranslated.REMOVE_ENIGMA),

	ADD_CONTENT(NeedToBeTranslated.ADD_CONTENT),
	REMOVE_CONTENT(NeedToBeTranslated.REMOVE_CONTENT),
	ADD_SUB_ENTITY(NeedToBeTranslated.ADD_SUB_ENTITY),
	REMOVE_SUB_ENTITY(NeedToBeTranslated.REMOVE_SUB_ENTITY),
	SET_HERO(NeedToBeTranslated.SET_HERO),
	UNSET_HERO(NeedToBeTranslated.UNSET_HERO),
	;

	private final String value;

	ActionTypes(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

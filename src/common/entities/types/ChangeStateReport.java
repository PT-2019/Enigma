package common.entities.types;

import common.enigmas.reporting.Report;
import common.language.EnigmaField;
import common.language.GameLanguage;

/**
 * Report des changement d'états
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 17/02/2020
 * @since 6.0 17/02/2020
 */
public enum ChangeStateReport implements Report {
	//TODO: ces trucs sont pas utiles, pour l'instant. A déplacer aussi dans NeedToBeTranslated.
	DONE("", SHOULD_NOT_BE_SHOWED),
	UNLOCK(GameLanguage.gl.get(EnigmaField.UNLOCK), MUST_BE_SHOWED),
	INVENTORY("Ajout dans l'inventaire", SHOULD_NOT_BE_SHOWED),
	LOCKED(GameLanguage.gl.get(EnigmaField.LOCK), MUST_BE_SHOWED),
	OPEN("", SHOULD_NOT_BE_SHOWED);

	private final String value;
	private final int importance;

	ChangeStateReport(String value, int importance) {
		this.value = value;
		this.importance = importance;
	}

	@Override
	public String getReport() {
		return this.value;
	}

	@Override
	public int getImportance() {
		return importance;
	}
}

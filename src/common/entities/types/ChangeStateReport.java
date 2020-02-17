package common.entities.types;

import common.enigmas.reporting.EnigmaReport;

/**
 * Report des changement d'états
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 17/02/2020
 * @since 6.0 17/02/2020
 */
public enum ChangeStateReport implements EnigmaReport.Report {
	DONE(""),
	UNLOCK("Ouvert"),
	INVENTORY("Ajout dans l'inventaire");

	private final String value;

	ChangeStateReport(String value) {
		this.value = value;
	}

	@Override
	public String getReport() {
		return this.value;
	}
}

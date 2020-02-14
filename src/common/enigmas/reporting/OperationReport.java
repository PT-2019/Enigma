package common.enigmas.reporting;

/**
 * Report des conditions
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
public enum OperationReport implements EnigmaReport.Report {
	DONE("Fait."), INVENTORY_FULL("Inventaire plein."), //...
	;

	private final String report;

	OperationReport(String report) {
		this.report = report;
	}

	@Override
	public String getReport() {
		return this.report;
	}
}


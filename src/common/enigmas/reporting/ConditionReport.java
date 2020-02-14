package common.enigmas.reporting;

/**
 * Report des conditions
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
public enum ConditionReport implements EnigmaReport.Report {
	DONE("Fait.") //...ajouter des trucs sérieux
	;

	private final String report;

	ConditionReport(String report){
		this.report = report;
	}

	@Override
	public String getReport() {
		return this.report;
	}
}

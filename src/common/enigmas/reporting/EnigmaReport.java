package common.enigmas.reporting;

/**
 * Le message retourné l'exécution d'une opération
 * ou d'une condition.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
public class EnigmaReport {

	/**
	 * Message sur la réalisation de l'action
	 */
	private Report report;

	/**
	 * true si l'action c'est faite sinon false
	 */
	private boolean fulfilled;

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report Message sur la réalisation de l'action
	 */
	public EnigmaReport(Report report) {
		this.report = report;
		this.fulfilled = false;
	}

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report    Message sur la réalisation de l'action
	 * @param fulfilled true si l'action c'est faite sinon false
	 */
	public EnigmaReport(Report report, boolean fulfilled) {
		this.report = report;
		this.fulfilled = fulfilled;
	}

	/**
	 * Message sur la réalisation de l'action
	 *
	 * @return Message sur la réalisation de l'action
	 */
	public String getReport() {
		return this.report.getReport();
	}

	/**
	 * true si l'action c'est faite sinon false
	 *
	 * @return true si l'action c'est faite sinon false
	 */
	public boolean isFulfilled() {
		return this.fulfilled;
	}

	/**
	 * true si l'action c'est faite sinon false
	 *
	 * @param fulfilled true si l'action c'est faite sinon false
	 */
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	/**
	 * Le message a reporter
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 12/02/2020
	 * @since 6.0 12/02/2020
	 */
	public interface Report {

		/**
		 * Message sur la réalisation de l'action
		 *
		 * @return Message sur la réalisation de l'action
		 */
		String getReport();
	}

}

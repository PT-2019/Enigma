package common.enigmas.reporting;

/**
 * Report des conditions
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1 12/02/2020
 * @since 6.0 12/02/2020
 */
public enum ConditionReport implements Report {
	ACTIVATED("Quelque chose est activé.",SHOULD_NOT_BE_SHOWED),
	UNACTIVATED("Quelque chose n'est pas activé.",MAY_BE_SHOWED),
	FOUND_IN_HANDS("Le joueur a l'objet dans ses mains.",SHOULD_NOT_BE_SHOWED),
	NOT_IN_HANDS("Le joueur n'a pas l'objet dans ses mains.",MAY_BE_SHOWED),
	CORRECT_ANSWER("La réponse est correcte", MUST_BE_SHOWED),
	WRONG_ANSWER("Mauvaise réponse.", MUST_BE_SHOWED),
	FOUND_IN_INVENTORY("Le joueur a l'objet dans son inventaire.",SHOULD_NOT_BE_SHOWED),
	NOT_IN_INVENTORY("Le joueur n'a pas l'objet dans son inventaire.",MAY_BE_SHOWED),
	DISCOVERED("La pièce a été découverte.",SHOULD_NOT_BE_SHOWED),
	UNDISCOVERED("La pièce n'a pas été découverte.",SHOULD_NOT_BE_SHOWED),
	NO_ANSWER("Aucune réponse saisie", SHOULD_NOT_BE_SHOWED);

	/**
	 * Rapport
	 */
	private final String report;
	/**
	 * Importance
	 * 0 : signifie que le rapport ne semble pas disposer d'intérêt à être révélé au joueur
	 * 1 : signifie qu'il existe peut être un intérêt à révéler le rapport au joueur
	 * 2 ou plus : signifie qu'il existe un intérêt clair à révéler le rapport au joueur
	 */
	private final int importance;

	/**
	 * @param report Rapport
	 * @param importance Importance
	 */
	ConditionReport(String report, int importance) {
		this.report = report;
		this.importance = importance;
	}

	/**
	 * Message sur la réalisation de l'action
	 *
	 * @return Message sur la réalisation de l'action
	 */
	@Override
	public String getReport() {
		return this.report;
	}

	/**
	 * Obtenir l'importance
	 *
	 * @return Importance
	 */
	@Override
	public int getImportance() {
		return this.importance;
	}
}

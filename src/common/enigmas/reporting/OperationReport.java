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
public enum OperationReport implements Report {
	MAIN_MUSIC_CHANGED("La musique principale changée.",SHOULD_NOT_BE_SHOWED),
	GIVEN("Un objet a été donné.",MAY_BE_SHOWED),
	INVENTORY_FULL("L'inventaire est plein.",2),
	ROOM_HIDDEN("Une salle a été cachée.",SHOULD_NOT_BE_SHOWED),
	ROOM_SHOWED("Une salle a été dévoilée.",MAY_BE_SHOWED),
	SOUND_LAUNCHED("Un son a été lancé.",SHOULD_NOT_BE_SHOWED),
	ENTITY_SPAWNED("Une entitée est apparue.",SHOULD_NOT_BE_SHOWED),
	UNLOCKED("Quelque chose a été déverrouillé",MAY_BE_SHOWED);

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
	OperationReport(String report, int importance) {
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


package common.language;

import api.utils.Utility;

/**
 * Les champs liés aux énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 03/02/2020
 * @since 6.0 03/02/2020
 */
public enum EnigmaField implements Field {
	ADVICE_CONTENT, DELAY, ADVICE, ACTIVATED, DELAY_MESURE, HAVE_IN_HANDS,
	HAVE_IN_INVENTORY, ANSWER, INPUT_ANSWER, GIVE, SUMMON, LOCATION, UNLOCK, AFTER,LOCK,DESACTIVATED;

	@Override
	public String toString() {
		return Utility.snakeCaseToCamelCase(this.name());
	}
}

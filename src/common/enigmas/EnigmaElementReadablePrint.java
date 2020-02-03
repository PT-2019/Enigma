package common.enigmas;

/**
 * Retourne une belle version pour afficher un élément
 * d'une énigme.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 03/02/2020
 * @since 6.0 03/02/2020
 */
@FunctionalInterface
public interface EnigmaElementReadablePrint {

	/**
	 * Retourne une belle version pour afficher un élément
	 * d'une énigme.
	 * @return Retourne une belle version pour afficher un élément d'une énigme.
	 * @since 6.0
	 */
	String getEnigmaElementReadablePrint();

}

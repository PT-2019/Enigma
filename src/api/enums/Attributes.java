package api.enums;

/**
 * Désigne un ensemble de variable statiques représantant des noms possibles
 * d'attribut sous forme de String
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public interface Attributes {

	/**
	 * Chemin de la classe
	 */
	String PATH = "path";

	/**
	 * Entité concernée
	 *
	 * @see editor.enigma.condition.Condition
	 * @see editor.enigma.operation.Operation
	 */
	String ENTITY = "entities";

	/**
	 * Cordonnée x d'apparition
	 *
	 * @see editor.enigma.operation.Summon
	 */
	String SPAWN_X = "x";

	/**
	 * Cordonnée y d'apparition
	 *
	 * @see editor.enigma.operation.Summon
	 */
	String SPAWN_Y = "y";

	/**
	 * Couche
	 *
	 * @see editor.enigma.operation.Summon
	 */
	String LAYER = "layer";

	/**
	 * Délai
	 *
	 * @see editor.enigma.Advice
	 */
	String DELAY = "delay";

	/**
	 * Indice
	 *
	 * @see editor.enigma.Advice
	 */
	String ADVICE = "advice";

	/**
	 * Titre
	 *
	 * @see editor.enigma.Enigma
	 */
	String TITLE = "title";

	/**
	 * Description
	 *
	 * @see editor.enigma.Enigma
	 */
	String DESCRIPTION = "description";

	/**
	 * Est connu
	 *
	 * @see editor.enigma.Enigma
	 */
	String KNOWN = "known";

	/**
	 * Index pointant l'indice actuel
	 *
	 * @see editor.enigma.Enigma
	 */
	String CURRENT_ADVICE_INDEX = "currentAdviceIndex";

	/**
	 * Indices
	 *
	 * @see editor.enigma.Enigma
	 */
	String ADVICES = "advices";

	/**
	 * Conditions
	 *
	 * @see editor.enigma.Enigma
	 */
	String CONDITIONS = "conditions";

	/**
	 * Operations
	 *
	 * @see editor.enigma.Enigma
	 */
	String OPERATIONS = "operations";

	/**
	 * Id associé à une entité pour la sauvegarde
	 */
	String ID = "id";

	/**
	 * Type associé à une énigme pour la sauvegarde
	 * @see editor.enigma.Enigma
	 */
	String TYPE = "type";
}
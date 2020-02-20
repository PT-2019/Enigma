package common.save.enigmas;

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
public interface EnigmaAttributes {

	/**
	 * Chemin de la classe
	 */
	String PATH = "path";

	/**
	 * Entité concernée
	 *
	 * @see common.enigmas.condition.Condition
	 * @see common.enigmas.operation.Operation
	 */
	String ENTITY = "entities";

	/**
	 * Cordonnée x d'apparition
	 *
	 * @see common.enigmas.operation.Summon
	 */
	String SPAWN_X = "spawn_x";

	/**
	 * Cordonnée y d'apparition
	 *
	 * @see common.enigmas.operation.Summon
	 */
	String SPAWN_Y = "spawn_y";

	/**
	 * Couche
	 *
	 * @see common.enigmas.operation.Summon
	 */
	String SPAWN_LAYER = "spawn_layer";

	/**
	 * Délai
	 *
	 * @see common.enigmas.Advice
	 */
	String DELAY = "delay";

	/**
	 * Indice
	 *
	 * @see common.enigmas.Advice
	 */
	String ADVICE = "advice";

	/**
	 * Titre
	 *
	 * @see common.enigmas.Enigma
	 */
	String TITLE = "title";

	/**
	 * Description
	 *
	 * @see common.enigmas.Enigma
	 */
	String DESCRIPTION = "description";

	/**
	 * Est connu
	 *
	 * @see common.enigmas.Enigma
	 */
	String KNOWN = "known";

	/**
	 * Index pointant l'indice actuel
	 *
	 * @see common.enigmas.Enigma
	 */
	String CURRENT_ADVICE_INDEX = "currentAdviceIndex";

	/**
	 * Indices
	 *
	 * @see common.enigmas.Enigma
	 */
	String ADVICES = "advices";

	/**
	 * Conditions
	 *
	 * @see common.enigmas.Enigma
	 */
	String CONDITIONS = "conditions";

	/**
	 * Operations
	 *
	 * @see common.enigmas.Enigma
	 */
	String OPERATIONS = "operations";

	/**
	 * Id associé à une entité pour la sauvegarde
	 */
	String ID = "id";

	/**
	 * Type associé à une énigme pour la sauvegarde
	 *
	 * @see common.enigmas.Enigma
	 */
	String TYPE = "type";

	/**
	 * Type associé à une énigme pour la sauvegarde
	 *
	 * @see common.enigmas.Enigma
	 */
	String FULFILLED = "fulfilled";
}
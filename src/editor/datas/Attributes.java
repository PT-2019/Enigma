package editor.datas;

/**
 * Désigne un ensemble de variable statiques représantant des noms possibles d'attribut sous forme de String
 *
 * @version 2.0
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
	 * Case d'apparition
	 *
	 * @see editor.enigma.operation.Summon
	 */
	String SPAWN = "spawn";

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
}
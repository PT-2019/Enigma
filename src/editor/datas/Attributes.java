package editor.datas;

/**
 * Désigne un ensemble de variable statiques représantant des noms possibles d'attribut sous forme de String
 * @version 2.0
 */
public interface Attributes {

	/**
	 * Chemin de la classe
	 */
	public final static String PATH = "path";

	/**
	 * Entité concernée
	 * @see editor.enigma.condition.Condition
	 * @see editor.enigma.operation.Operation
	 */
	public final static String ENTITY = "entities";

	/**
	 * Case d'apparition
	 * @see editor.enigma.operation.Summon
	 */
	public final static String SPAWN = "spawn";

	/**
	 * Délai
	 * @see editor.enigma.Advice
	 */
	public final static String DELAY = "delay";

	/**
	 * Indice
	 * @see editor.enigma.Advice
	 */
	public final static String ADVICE = "advice";

	/**
	 * Titre
	 * @see editor.enigma.Enigma
	 */
	public final static String TITLE = "title";

	/**
	 * Description
	 * @see editor.enigma.Enigma
	 */
	public final static String DESCRIPTION = "description";

	/**
	 * Est connu
	 * @see editor.enigma.Enigma
	 */
	public final static String KNOWN = "known";

	/**
	 * Index pointant l'indice actuel
	 * @see editor.enigma.Enigma
	 */
	public final static String CURRENT_ADVICE_INDEX = "currentAdviceIndex";

	/**
	 * Indices
	 * @see editor.enigma.Enigma
	 */
	public final static String ADVICES = "advices";

	/**
	 * Conditions
	 * @see editor.enigma.Enigma
	 */
	public final static String CONDITIONS = "conditions";

	/**
	 * Operations
	 * @see editor.enigma.Enigma
	 */
	public final static String OPERATIONS = "operations";
}
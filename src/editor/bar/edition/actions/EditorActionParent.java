package editor.bar.edition.actions;

import api.utils.annotations.ConvenienceClass;

/**
 * Pour action factory, ce que l'on considère un parent
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
@ConvenienceClass
public interface EditorActionParent<T> {

	/**
	 * Ajoute un object
	 *
	 * @param args les arguments pour supprimer l'object
	 * @since 6.0
	 */
	void add(T arg, Object... args);

	/**
	 * Retire un object
	 *
	 * @param args les arguments pour supprimer l'object
	 * @since 6.0
	 */
	void remove(T arg, Object... args);

}

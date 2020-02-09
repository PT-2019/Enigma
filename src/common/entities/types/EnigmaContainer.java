package common.entities.types;

import common.enigmas.Enigma;
import editor.bar.edition.actions.EditorActionParent;

import java.util.Iterator;

/**
 * Permet à une {@link common.entities.Entity entité} de contenir des énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see common.enigmas.Enigma
 * @since 2.0
 */
public interface EnigmaContainer extends EditorActionParent<Enigma> {

	/**
	 * Ajouter une énigme
	 *
	 * @param e Enigme à ajouter
	 * @throws IllegalArgumentException si l'énigme existe déjà
	 */
	void addEnigma(Enigma e);

	/**
	 * Permet de supprimer une énigme
	 *
	 * @param e Enigme à supprimer
	 */
	void removeEnigma(Enigma e);

	/**
	 * Obtenir toutes les énigmes
	 *
	 * @return Iterator des énigmes
	 */
	Iterator<Enigma> getAllEnigmas();
}

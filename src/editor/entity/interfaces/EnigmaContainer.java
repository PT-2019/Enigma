package editor.entity.interfaces;

import editor.enigma.Enigma;

import java.util.Iterator;

/**
 * Permet à une {@link editor.entity.interfaces.Entity entité} de contenir des énigmes
 *
 * @version 2.0
 * @see editor.enigma.Enigma
 */
public interface EnigmaContainer {

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

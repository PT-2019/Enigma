package editor.entities.interfaces;

import editor.enigma.Enigma;

import java.util.Iterator;

/**
 * Permet à une {@link editor.entities.interfaces.Entity entité} de contenir des énigmes
 * @see editor.enigma.Enigma
 * @version 2.0
 */
public interface EnigmaContainer {

    /**
     * Ajouter une énigme
     * @param e Enigme à ajouter
     * @throws IllegalArgumentException si l'énigme existe déjà
     */
    public void addEnigma(Enigma e);

    /**
     * Permet de supprimer une énigme
     * @param e Enigme à supprimer
     */
    public void removeEnigma(Enigma e);

    /**
     * Obtenir toutes les énigmes
     * @return Iterator des énigmes
     */
    public Iterator<Enigma> getAllEnigmas();
}

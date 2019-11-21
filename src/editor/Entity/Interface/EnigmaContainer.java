package editor.Entity.Interface;

import editor.Enigma.Enigma;

import java.util.Iterator;

/**
 * Permet à une {@link editor.Entity.Interface.Entity entité} de contenir des énigmes
 * @version 1.0
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

package editor.Entity.Interface;

import editor.Enigma.Enigma;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.Iterator;

/**
 * Définie une entité comme un item
 * @see editor.Entity.Interface.EnigmaContainer
 * @see editor.Entity.Interface.Entity
 * @version 2.1
 */
public interface Item extends EnigmaContainer, Entity {

    /**
     * Est appelé quand un joueur intéragit avec l'objet
     * @param p Joueur ayant intéragit avec l'objet
     */
    public void interactsWith(Player p);

    /**
     * Obtenir la texture de l'objet
     * @return Texture de l'objet, null sinon
     */
    public Texture getTexture();

    /**
     * Affiche un dialogue avec l'objet
     */
    public void showDialog();

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

    /**
     * Obtenir l'ID
     * @return L'ID, -1 si pas initialisé
     */
    public int getID();

    /**
     * Définir l'ID
     * @param id ID
     */
    public void setID(int id);
}

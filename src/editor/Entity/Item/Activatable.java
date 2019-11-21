package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.Iterator;

/**
 * Désigne des objets pouvant être activés ou désactivés
 * @see editor.Entity.Interface.Item
 * @version 1.0
 */
public abstract class Activatable implements Item {

    /**
     * Indique si l'objet est activé
     */
    protected boolean activated;

    /**
     * @param activated true si l'objet est activé de base, false sinon
     */
    public Activatable(boolean activated){
        this.activated = activated;
    }

    /**
     * Est appelé quand un joueur intéragit avec l'objet
     * @param p Joueur ayant intéragit avec l'objet
     */
    @Override
    public abstract void interactsWith(Player p);

    /**
     * Obtenir la texture de l'objet
     * @return Texture de l'objet, null sinon
     */
    @Override
    public abstract Texture getTexture();

    /**
     * Affiche un dialogue avec l'objet
     */
    @Override
    public abstract void showDialog();

    /**
     * Ajouter une énigme
     * @param e Enigme à ajouter
     * @throws IllegalArgumentException si l'énigme existe déjà
     */
    @Override
    public abstract void addEnigma(Enigma e);

    /**
     * Permet de supprimer une énigme
     * @param e Enigme à supprimer
     */
    @Override
    public abstract void removeEnigma(Enigma e);

    /**
     * Obtenir toutes les énigmes
     * @return Iterator des énigmes
     */
    public abstract Iterator<Enigma> getAllEnigmas();

    /**
     * Indique si l'objet est activé
     * @return true si l'objet est activé, false sinon
     */
    public abstract boolean isActivated();
}

package editor.entity.item;

import editor.enigma.Enigma;
import editor.entity.Player;
import editor.entity.interfaces.Item;
import editor.textures.Texture;

import java.util.Iterator;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @version 2.2
 * @see editor.entity.interfaces.Item
 * @since 1.0
 */
public abstract class Activatable implements Item {

    /**
     * Indique si l'objet est activé
     */
    protected boolean activated;

    /**
     * ID
     */
    protected int id;

    /**
     * @param activated true si l'objet est activé de base, false sinon
     */
    public Activatable(boolean activated) {
        this.activated = activated;
        this.id = -1;
    }

    /**
     * @param activated true si l'objet est activé de base, false sinon
     * @param id        ID
     */
    public Activatable(boolean activated, int id) {
        this.activated = activated;
        this.id = id;
    }

    /**
     * Est appelé quand un joueur intéragit avec l'objet
     *
     * @param p Joueur ayant intéragit avec l'objet
     */
    @Override
    public abstract void interactsWith(Player p);

    /**
     * Obtenir la texture de l'objet
     *
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
     *
     * @param e Enigme à ajouter
     * @throws IllegalArgumentException si l'énigme existe déjà
     */
    @Override
    public abstract void addEnigma(Enigma e);

    /**
     * Permet de supprimer une énigme
     *
     * @param e Enigme à supprimer
     */
    @Override
    public abstract void removeEnigma(Enigma e);

    /**
     * Obtenir toutes les énigmes
     *
     * @return Iterator des énigmes
     */
    public abstract Iterator<Enigma> getAllEnigmas();

    /**
     * Indique si l'objet est activé
     *
     * @return true si l'objet est activé, false sinon
     */
    public abstract boolean isActivated();

    /**
     * Obtenir l'ID
     *
     * @return L'ID, -1 si pas initialisé
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Définir l'ID
     *
     * @param id ID
     */
    @Override
    public void setID(int id) {
        this.id = id;
    }
}

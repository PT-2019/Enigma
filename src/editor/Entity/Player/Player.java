package editor.Entity.Player;

import editor.Entity.Interface.Entity;
import editor.Entity.Interface.Living;
import editor.texture.Texture;

/**
 * Définie un personnage contrôlable : un joueur
 * @see editor.Entity.Interface.Entity
 * @see editor.Entity.Interface.Living
 * @version 1.0
 */
public class Player implements Entity, Living {

    /**
     * Point de vie du joueur
     */
    private int pv;

    /**
     * Dialogue de l'objet
     */
    private String dialog;

    /**
     * Texture de l'objet
     */
    private Texture texture;

    /**
     * Est appelé quand un joueur intéragit avec l'objet
     * @param p Joueur ayant intéragit avec l'objet
     */
    @Override
    public void interactsWith(Player p) {}

    /**
     * Obtenir la texture de l'objet
     * @return Texture de l'objet, null sinon
     */
    @Override
    public Texture getTexture() {
        return null;
    }

    /**
     * Affiche un dialogue avec l'objet
     */
    @Override
    public void showDialog() {}

    /**
     * Obtenir les points de vie de l'entité
     * @return Les points de vie
     */
    @Override
    public int getHP() {
        return this.pv;
    }
}



package editor.Entity.Interface;

import editor.Entity.Player.Player;
import editor.texture.Texture;

/**
 * Définie une entité
 * @version 2.0
 */
public interface Entity{

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

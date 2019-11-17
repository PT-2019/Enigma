package editor.Entity.Interface;

import editor.Entity.Player.Player;
import editor.texture.Texture;

/**
 * Définie une entité
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
}

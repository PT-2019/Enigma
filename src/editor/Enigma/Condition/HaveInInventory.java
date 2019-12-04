package editor.Enigma.Condition;

import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;

import java.util.Map;

/**
 * Vérifie qu'un joueur à un item défini dans son inventaire
 * @see editor.Enigma.Condition.Condition
 * @version 2.2
 */
public class HaveInInventory extends Condition {

    /**
     * @param i Item concerné par la condition
     */
    public HaveInInventory(Item i){
        super(i);
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public HaveInInventory(Map<String,Object> attributes){
        super(attributes);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        Item i = (Item)this.entity;
        //tester si p a i dans son inventaire
        return false;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[HaveInInventory]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toLongString(){
        return "[HaveInInventory  : entity = " + this.entity + "]";
    }
}

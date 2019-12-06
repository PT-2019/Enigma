package editor.enigma.condition;

import editor.entity.interfaces.Item;
import editor.entity.player.Player;

import java.util.Map;

/**
 * Vérifie que le joueur tien un item défini dans ses mains
 * @see editor.enigma.condition.Condition
 * @version 2.2
 */
public class HaveInHands extends Condition {

    /**
     * @param i Item concerné par la condition
     */
    public HaveInHands(Item i){
        super(i);
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public HaveInHands(Map<String,Object> attributes){
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
        //tester si p a i dans ses mains
        return false;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[HaveInHands]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toLongString(){
        return "[HaveInHands  : entity = " + this.entity + "]";
    }
}
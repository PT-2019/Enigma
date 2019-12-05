package editor.enigma.condition;

import editor.entity.item.Activatable;
import editor.entity.player.Player;

import java.util.Map;

/**
 * Vérify qu'un objet activable est activé
 * @see editor.enigma.condition.Condition
 * @version 2.1
 */
public class Activated extends Condition {

    /**
     * @param a Objet activable concerné par la condition
     */
    public Activated(Activatable a){
        super(a);
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Activated(Map<String,Object> attributes){
        super(attributes);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        Activatable a = (Activatable)this.entity;
        System.out.println("La porte est dévérouillée");
        return a.isActivated();
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Activated]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toLongString(){
        return "[Activated  : entity = " + this.entity + "]";
    }
}

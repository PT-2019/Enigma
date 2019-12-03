package editor.Enigma.Condition;

import editor.Entity.Player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Vérifie que l'utilisateur donne une réponse correcte
 * @see editor.Enigma.Condition.Condition
 * @version 2.1
 */
public class Answer extends Condition {

    public Answer(){
        super(new HashMap<>());
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Answer(Map<String,Object> attributes){
        super(attributes);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        //poser la question et tester si la réponse est bonne
        return false;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Answer]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Answer]";
    }

}

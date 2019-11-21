package editor.Enigma.Condition;

import editor.Entity.Player.Player;

/**
 * Vérifie que l'utilisateur donne une réponse correcte
 * @see editor.Enigma.Condition.Condition
 * @version 1.0
 */
public class Answer extends Condition {

    public Answer(){
        super(null);
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

package editor.enigma.operation;

import editor.entity.interfaces.Item;
import editor.entity.interfaces.Lockable;
import editor.entity.Player;

import java.util.Map;

/**
 * Déverrouille un objet verrouillable
 * @see editor.enigma.operation.Operation
 * @version 2.2
 */
public class Unlock extends Operation {

    /**
     * @param l Objet verrouillabe concerné par l'opération
     */
    public Unlock(Lockable l){
        super((Item)l);
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Unlock(Map<String,Object> attributes){
        super(attributes);
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    @Override
    public void doOperation(Player p) {
        Lockable l = (Lockable)this.entity;
        l.unlock();
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Unlock]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toLongString(){
        return "[Unlock  : entity = " + this.entity + "]";
    }
}

package editor.Enigma.Operation;

import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Player.Player;

/**
 * Déverrouille un objet verrouillable
 * @see editor.Enigma.Operation.Operation
 * @version 1.0
 */
public class Unlock extends Operation {

    /**
     * @param l Objet verrouillabe concerné par l'opération
     */
    public Unlock(Lockable l){
        super((Item)l);
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
    public String toLongString(){
        return "[Unlock  : entity = " + this.entity + "]";
    }
}

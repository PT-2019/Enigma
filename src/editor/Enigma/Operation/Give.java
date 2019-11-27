package editor.Enigma.Operation;


import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;

/**
 * Donne un objet au joueur ayant entrainé le déclenchement de cette opération
 * @see editor.Enigma.Operation.Operation
 * @version 2.1
 */
public class Give extends Operation {

    /**
     * @param i Item concerné par l'opération
     */
    public Give(Item i){
        super(i);
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    @Override
    public void doOperation(Player p) {
        Item i = (Item)this.entity;
        //Donner i à p
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Give]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Give  : entity = " + this.entity + "]";
    }
}

package editor.Enigma.Operation;


import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

/**
 * Fait apparaître une entité sur une case donnée
 * @see editor.Enigma.Operation.Operation
 * @version 1.0
 */
public class Summon extends Operation {

    /**
     * Case où doit apparaître l'entité
     */
    private Case spawn;

    /**
     * @param e Entité concernée par l'opération
     * @param spawn Case où doit apparaître l'entité
     */
    public Summon(Entity e, Case spawn){
        super(e);
        this.spawn = spawn;
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    @Override
    public void doOperation(Player p) {
       //faire apparaitre this.entity sur this.spawn
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Summon]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Summon  : entity = " + this.entity + "]";
    }
}

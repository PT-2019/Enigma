package editor.Enigma.Operation;

import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

/**
 * Un opération définie une action à réaliser sur une entité
 * Elle est utilisée dans les {@link editor.Enigma.Enigma énigmes} pour faire des actions après que les {@link editor.Enigma.Condition.Condition conditions} est été satisfaites
 * @see editor.Enigma.Enigma
 * @version 1.0
 */
public abstract class Operation {

    /**
     * Entité concernée par l'opération
     */
    protected Entity entity;

    /**
     * @param e Entité concernée par l'opération
     */
    public Operation(Entity e){
        this.entity = e;
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    public abstract void doOperation(Player p);
}

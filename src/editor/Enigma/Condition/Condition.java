package editor.Enigma.Condition;

import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

/**
 * Une condition à pour but d'être satisfaite par une action des joueurs. Elle diffère fonction du type de condition
 * Elle est utilisée dans les {@link editor.Enigma.Enigma énigmes} pour déterminer si elles ont été résolues
 * @see editor.Enigma.Enigma
 * @version 1.0
 */
public abstract class Condition {

    /**
     * Entité dont l'état détermine si la condition est satisfaite ou nom
     */
    protected Entity entity;

    /**
     * @param e Entité concernée par la condition
     */
    public Condition(Entity e){
        this.entity = e;
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    public abstract boolean verify(Player p);

    /**
     * Obtenir l'entité consernée par la condition
     * @return L'entité, null sinon
     */
    public Entity getEntity(){
        return this.entity;
    }

    /**
     * Indiquer l'entité consernée par la condition
     * @param e Entité
     */
    public void setEntity(Entity e){
        this.entity = e;
    }
}

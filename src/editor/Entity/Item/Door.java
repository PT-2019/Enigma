package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Interface.Passage;
import editor.Entity.Interface.Room;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

/**
 * @see editor.Entity.Interface.Item
 * @see editor.Entity.Interface.Lockable
 * @see editor.Entity.Interface.Passage
 * @version 1.0
 */
public class Door implements Item, Passage, Lockable {

    /**
     * Enigmes données à l'objet
     */
    private ArrayList<Enigma> enigmas;

    /**
     * Dialogue de l'objet
     */
    private String dialog;

    /**
     * Texture de l'objet
     */
    private Texture texture;

    /**
     * Indique si l'objet est verrouillé
     */
    private boolean locked;

    /**
     * Pièce 1
     */
    private Room room1;

    /**
     * Pièce 2
     */
    private Room room2;

    public Door(){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = true;
    }

    /**
     * @param locked true si l'objet est verrouillé de base, false sinon
     */
    public Door(boolean locked){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = locked;
    }

    /**
     * Est appelé quand un joueur intéragit avec l'objet
     * @param p Joueur ayant intéragit avec l'objet
     */
    @Override
    public void interactsWith(Player p){
        for(Enigma e : this.enigmas){
            if(!e.isKnown()) e.discovered();
            else e.verifyConditions(p);
        }
    }

    /**
     * Obtenir la texture de l'objet
     * @return Texture de l'objet, null sinon
     */
    @Override
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Affiche un dialogue avec l'objet
     */
    @Override
    public void showDialog() {}

    /**
     * Ajouter une énigme
     * @param e Enigme à ajouter
     * @throws IllegalArgumentException si l'énigme existe déjà
     */
    @Override
    public void addEnigma(Enigma e) {
        if(this.enigmas.contains(e)) throw new IllegalArgumentException("Cette énigme existe déjà dans la liste");
        this.enigmas.add(e);
    }

    /**
     * Permet de supprimer une énigme
     * @param e Enigme à supprimer
     */
    @Override
    public void removeEnigma(Enigma e) {
        this.enigmas.remove(e);
    }

    /**
     * Obtenir la première pièce
     * @return La piece, null sinon
     */
    @Override
    public Room getRoom1() {
        return this.room1;
    }

    /**
     * Obtenir la seconde pièce
     * @return La pièce, null sinon
     */
    @Override
    public Room getRoom2() {
        return this.room2;
    }

    /**
     * Verrouille l'objet
     */
    @Override
    public void lock() {
        this.locked = true;
    }

    /**
     * Deverrouille l'objet
     */
    @Override
    public void unlock() {
        this.locked = false;
    }

    /**
     * Indique si l'objet est verrouillé
     * @return true si il est verrouillé, false sinon
     */
    @Override
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Door  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2;
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        StringBuilder s = new StringBuilder("[Door  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2 + ", enigmas = {");
        int size = this.enigmas.size() - 1;
        int i = 0;
        for(Enigma e : this.enigmas) {
            s.append(e.toLongString());
            if(i < size) s.append(", ");
            i++;
        }
        s.append("}]");
        return s.toString();
    }
}

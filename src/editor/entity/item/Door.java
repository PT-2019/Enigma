package editor.entity.item;

import editor.enigma.Enigma;
import editor.entity.interfaces.Item;
import editor.entity.interfaces.Lockable;
import editor.entity.interfaces.Passage;
import editor.entity.player.Player;
import editor.map.Room;
import editor.texture.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @see editor.entity.interfaces.Item
 * @see editor.entity.interfaces.Lockable
 * @see editor.entity.interfaces.Passage
 * @version 2.1
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

    /**
     * ID
     */
    private int id;

    public Door(){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = true;
        this.id = -1;
    }

    /**
     * @param id ID
     */
    public Door(int id){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = true;
        this.id = id;
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

    @Override
    public void setTexture(Texture t) {
        texture = t;
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
     * Obtenir toutes les énigmes
     * @return Iterator des énigmes
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Enigma> getAllEnigmas() {
        ArrayList<Enigma> e = (ArrayList<Enigma>)this.enigmas.clone();
        return e.iterator();
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
     * Obtenir l'ID
     * @return L'ID, -1 si pas initialisé
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Définir l'ID
     * @param id ID
     */
    @Override
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Door  : ID = " + this.id + ", dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2 + "]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        StringBuilder s = new StringBuilder("[Door  : ID = " + this.id + ", dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2 + ", enigmas = {");
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

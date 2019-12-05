package editor.entity.item;

import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.texture.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @see editor.entity.item.Activatable
 * @version 2.0
 */
public class Switch extends Activatable {

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

    public Switch(){
        super(false);
        this.enigmas = new ArrayList<Enigma>();
    }

    /**
     * @param id ID
     */
    public Switch(int id){
        super(false,id);
        this.enigmas = new ArrayList<Enigma>();
    }

    /**
     * @param activated true si l'objet est activé de base, false sinon
     */
    public Switch(boolean activated) {
        super(activated);
        this.enigmas = new ArrayList<Enigma>();
    }
    /**
     * @param activated true si l'objet est activé de base, false sinon
     * @param id ID
     */
    public Switch(boolean activated,int id) {
        super(activated,id);
        this.enigmas = new ArrayList<Enigma>();
    }


    /**
     * Est appelé quand un joueur intéragit avec l'objet
     * @param p Joueur ayant intéragit avec l'objet
     */
    @Override
    public void interactsWith(Player p) {
        this.activated = !this.activated;

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
     * Indique si l'objet est activé
     * @return true si l'objet est activé, false sinon
     */
    @Override
    public boolean isActivated() {
        return this.activated;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Switch  : ID = " + this.id + ", dialog = " + this.dialog + ", activated = " + this.activated + ", texture = " + this.texture + "]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        StringBuilder s = new StringBuilder("[Switch  : ID = " + this.id + ", dialog = " + this.dialog + ", activated = " + this.activated + ", texture = " + this.texture + ", enigmas = {");
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

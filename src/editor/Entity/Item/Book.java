package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Content;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @see editor.Entity.Interface.Item
 * @see editor.Entity.Interface.Content
 * @version 2.0
 */
public class Book implements Item, Content {

    /**
     * Enigmes données à l'objet
     */
    private ArrayList<Enigma> enigmas;

    /**
     * Contenu de l'objet
     */
    private String content;

    /**
     * Dialogue de l'objet
     */
    private String dialog;

    /**
     * Texture de l'objet
     */
    private Texture texture;

    public Book(){
        this.enigmas = new ArrayList<Enigma>();
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
     * Ajoute un contenu à l'objet
     * @param content Contenu à ajouter
     */
    @Override
    public void addContent(String content) {
        this.content = content;
    }

    /**
     * Obtenir le contenu
     * @return le contenu, le contenu peut être vide
     */
    @Override
    public String getContent() {
        return this.content;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Book  : dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture + "]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        StringBuilder s = new StringBuilder("[Book  : dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture + ", enigmas = {");
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

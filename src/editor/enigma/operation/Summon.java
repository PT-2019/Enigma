package editor.enigma.operation;


import editor.datas.Attributes;
import editor.entity.interfaces.Entity;
import editor.entity.Player;
import editor.entity.IDFactory;
import editor.map.Case;

import java.util.HashMap;
import java.util.Map;

/**
 * Fait apparaître une entité sur une case donnée
 * @see editor.enigma.operation.Operation
 * @version 2.2
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
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Summon(Map<String,Object> attributes){
        super(attributes);
        IDFactory idFactory = IDFactory.getInstance();
        if(attributes.containsKey(Attributes.SPAWN)) this.spawn = (Case) idFactory.getObject(Integer.parseInt((String) attributes.get(Attributes.SPAWN)));
        else throw new IllegalArgumentException("Attribut \"spawn\" abscent");
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
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.datas.Attributes
     */
    @Override
    public HashMap<String,Object> objectToMap(){
        HashMap<String,Object> object = new HashMap<>();
        object.put(Attributes.PATH,this.getClass().getName());
        object.put(Attributes.ENTITY, this.entity.getID() + "");
        object.put(Attributes.SPAWN, this.spawn.getID() + "");
        return object;
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
    @Override
    public String toLongString(){
        return "[Summon  : entity = " + this.entity + ", spawn = " + this.spawn + "]";
    }
}

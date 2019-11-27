package editor.Enigma.Operation;


import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;
import editor.Enums.Attributes;
import editor.Enums.SummonAttributes;
import editor.FileReader.IDFactory;
import editor.map.Case;

import java.util.HashMap;

/**
 * Fait apparaître une entité sur une case donnée
 * @see editor.Enigma.Operation.Operation
 * @version 2.1
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
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.Enums.SummonAttributes
     */
    @Override
    public HashMap<Attributes,String> objectToMap(){
        HashMap<Attributes,String> object = new HashMap<Attributes,String>();
        object.put(SummonAttributes.PATH,this.getClass().getName());
        object.put(SummonAttributes.ENTITY, this.entity.getID() + "");
        object.put(SummonAttributes.CASE, this.spawn.getID() + "");
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
    public String toLongString(){
        return "[Summon  : entity = " + this.entity + "]";
    }
}

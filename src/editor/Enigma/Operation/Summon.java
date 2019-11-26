package editor.Enigma.Operation;


import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;
import editor.Enums.SummonAttributes;
import editor.FileReader.EnigmaFileWriter;
import editor.map.Case;

import java.util.EnumMap;

/**
 * Fait apparaître une entité sur une case donnée
 * @see editor.Enigma.Operation.Operation
 * @version 2.0
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
    public EnumMap<SummonAttributes,String> objectToMap(){
        EnumMap<SummonAttributes,String> object = new EnumMap<SummonAttributes,String>(SummonAttributes.class);
        object.put(SummonAttributes.PATH,this.getClass().getName());
        object.put(SummonAttributes.ENTITY,EnigmaFileWriter.getID(this.entity) + "");
        object.put(SummonAttributes.CASE,EnigmaFileWriter.getID(this.spawn) + "");
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

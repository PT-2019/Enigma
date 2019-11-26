package editor.Enigma.Operation;

import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Player.Player;
import editor.Enums.OperationAttributes;
import editor.FileReader.EnigmaFileWriter;

import java.util.EnumMap;

/**
 * Déverrouille un objet verrouillable
 * @see editor.Enigma.Operation.Operation
 * @version 2.0
 */
public class Unlock extends Operation {

    /**
     * @param l Objet verrouillabe concerné par l'opération
     */
    public Unlock(Lockable l){
        super((Item)l);
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    @Override
    public void doOperation(Player p) {
        Lockable l = (Lockable)this.entity;
        l.unlock();
    }

    /**
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.Enums.OperationAttributes
     */
    public EnumMap<OperationAttributes,String> objectToMap(){
        EnumMap<OperationAttributes,String> object = new EnumMap<OperationAttributes,String>(OperationAttributes.class);
        object.put(OperationAttributes.PATH,this.getClass().getName());
        object.put(OperationAttributes.ENTITY, EnigmaFileWriter.getID(this.entity) + "");
        return object;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Unlock]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Unlock  : entity = " + this.entity + "]";
    }
}

package editor.Enigma.Condition;

import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.Enums.ConditionAttributes;
import editor.FileReader.EnigmaFileWriter;

import java.util.EnumMap;

/**
 * Vérifie qu'un joueur à un item défini dans son inventaire
 * @see editor.Enigma.Condition.Condition
 * @version 2.0
 */
public class HaveInInventory extends Condition {

    /**
     * @param i Item concerné par la condition
     */
    public HaveInInventory(Item i){
        super(i);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        Item i = (Item)this.entity;
        //tester si p a i dans son inventaire
        return false;
    }

    /**
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.Enums.ConditionAttributes
     */
    public EnumMap<ConditionAttributes,String> objectToMap(){
        EnumMap<ConditionAttributes,String> object = new EnumMap<ConditionAttributes,String>(ConditionAttributes.class);
        object.put(ConditionAttributes.PATH,this.getClass().getName());
        object.put(ConditionAttributes.ENTITY, EnigmaFileWriter.getID(this.entity) + "");
        return object;
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[HaveInInventory]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[HaveInInventory  : entity = " + this.entity + "]";
    }
}

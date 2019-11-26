package editor.Enigma.Condition;

import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.Enums.ConditionAttributes;
import editor.FileReader.EnigmaFileWriter;

import java.util.EnumMap;

/**
 * Vérifie que le joueur tien un item défini dans ses mains
 * @see editor.Enigma.Condition.Condition
 * @version 2.0
 */
public class HaveInHands extends Condition {

    /**
     * @param i Item concerné par la condition
     */
    public HaveInHands(Item i){
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
        //tester si p a i dans ses mains
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
        return "[HaveInHands]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[HaveInHands  : entity = " + this.entity + "]";
    }
}

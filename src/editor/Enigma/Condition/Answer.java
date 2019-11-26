package editor.Enigma.Condition;

import editor.Entity.Player.Player;
import editor.Enums.ConditionAttributes;
import editor.FileReader.EnigmaFileWriter;

import java.util.EnumMap;

/**
 * Vérifie que l'utilisateur donne une réponse correcte
 * @see editor.Enigma.Condition.Condition
 * @version 2.0
 */
public class Answer extends Condition {

    public Answer(){
        super(null);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        //poser la question et tester si la réponse est bonne
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
        return "[Answer]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Answer]";
    }

}

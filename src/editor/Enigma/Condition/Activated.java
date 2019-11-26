package editor.Enigma.Condition;

import editor.Entity.Item.Activatable;
import editor.Entity.Player.Player;
import editor.Enums.ConditionAttributes;
import editor.FileReader.EnigmaFileWriter;

import java.util.EnumMap;

/**
 * Vérify qu'un objet activable est activé
 * @see editor.Enigma.Condition.Condition
 * @version 2.0
 */
public class Activated extends Condition {

    /**
     * @param a Objet activable concerné par la condition
     */
    public Activated(Activatable a){
        super(a);
    }

    /**
     * Vérifie que la condition est satisfaite
     * @param p Joueur ayant mené à l'appel de cette méthode
     * @return true si la condtion est satisfaite, false sinon
     */
    @Override
    public boolean verify(Player p) {
        Activatable a = (Activatable)this.entity;
        System.out.println("La porte est dévérouillée");
        return a.isActivated();
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
        return "[Activated]";
    }

    /**
     * Version texte longue de l'objet
     * @return Texte représentant l'objet
     */
    public String toLongString(){
        return "[Activated  : entity = " + this.entity + "]";
    }
}

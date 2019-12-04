package editor.Enigma.Operation;

import com.badlogic.gdx.utils.Array;
import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;
import editor.Enums.Attributes;
import editor.Enums.OperationAttributes;
import editor.FileReader.IDFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Un opération définie une action à réaliser sur une entité
 * Elle est utilisée dans les {@link editor.Enigma.Enigma énigmes} pour faire des actions après que les {@link editor.Enigma.Condition.Condition conditions} est été satisfaites
 * @see editor.Enigma.Enigma
 * @version 2.0
 */
public abstract class Operation {

    /**
     * Entité concernée par l'opération
     */
    protected Entity entity;

    public final static String test = "entity";

    /**
     * @param e Entité concernée par l'opération
     */
    public Operation(Entity e){
        this.entity = e;
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Operation(Map<String,Object> attributes){
        /*for(Map.Entry<String, Object> entry : attributes.entrySet()) {
            if(entry.getValue() instanceof String)
            System.out.println("*"+entry.getKey()+"* / "+entry.getValue());
            else
                if(entry.getValue() instanceof List)
                for(Object o: (ArrayList)entry.getValue()) System.out.println(entry.getKey()+"----"+o);
        }*/
        if(attributes.get(Operation.test) != null) this.entity = new Player();
        //else throw new IllegalArgumentException("Attribut \"entity\" abscent");
    }

    /**
     * Effectue l'action
     * @param p Joueur ayant mené à l'appel de cette méthode
     */
    public abstract void doOperation(Player p);

    /**
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.Enums.OperationAttributes
     */
    public HashMap<String,Object> objectToMap(){
        HashMap<String,Object> object = new HashMap<>();
        object.put("path",this.getClass().getName());
        object.put(Operation.test, this.entity.getID() + "");
        return object;
    }
}

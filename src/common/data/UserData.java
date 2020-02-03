package common.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Données de l'utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class UserData {

    /**
     * Nom
     */
    private String name;

    /**
     * Donnée sur le nom
     */
    private final static String NAME = "name";

    /**
     * @param name Nom
     */
    public UserData(String name){
        this.name = name;
    }

    /**
     * @param data Données
     */
    public UserData(HashMap<String,String> data){
        ArrayList<String> dataName = new ArrayList<>();
        dataName.add(NAME);

        for(String name : dataName){
            String get = data.get(name);
            if(get != null){
                switch(name){
                    case NAME:
                        this.name = get;
                        break;
                }
            } else
                throw new IllegalArgumentException("Attribut " + name + " manquant");
        }
    }

    /**
     * Obtenir les données
     * @return Les données
     */
    public HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        data.put(NAME, this.name);

        return data;
    }

    /**
     * Obtenir le nom
     * @return Le nom
     */
    public String getName(){
        return this.name;
    }
}

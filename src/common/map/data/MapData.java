package common.map.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Donnée propre à une map
 * Les données ne sont pas modifiables pour l'instant
 * Certaines données ne doivent jamais être modifiables
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01 fevrier 2020
 * @since 6.0
 */
public class MapData {
    /**
     * Auteur de la map
     * Donné à l'instanciation, il ne doit, dans auncun cas, être changé plus tard
     */
    private String author;
    /**
     * Nom de la map
     * Donnée à l'instanciation, elle ne doit, dans auncun cas, être changée plus tard
     */
    private String mapName;

    /**
     * Attribut auteur
     */
    public final static String AUTHOR = "author";
    /**
     * Attribut nom de la partie
     */
    public final static String MAP_NAME = "mapName";

    /**
     * @param author Auteur
     */
    public MapData(String author, String mapName) {
        this.author = author;
        this.mapName = mapName;
    }

    /**
     * @param data Valeurs des attributs
     */
    public MapData(HashMap<String,String> data){
        ArrayList<String> dataName = new ArrayList<>();
        dataName.add(MAP_NAME);
        dataName.add(AUTHOR);

        for(String name : dataName){
            String get = data.get(name);
            if(get != null){
                switch(name){
                    case MAP_NAME:
                        this.mapName = get;
                        break;
                    case AUTHOR:
                        this.author = get;
                        break;
                }
            } else
                throw new IllegalArgumentException("Attribut " + name + " manquant");
        }
    }

    /**
     * Obtenir l'auteur
     * @return L'auteur
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * Obtenir le nom de la map
     * @return Nom de la map
     */
    public String getMapName(){
        return this.mapName;
    }

    /**
     * Obtenir les données
     * @return Données
     */
    public HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        data.put(AUTHOR, this.author);
        data.put(MAP_NAME, this.mapName);

        return data;
    }
}

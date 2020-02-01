package common.map.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Donnée propre à une partie
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
public class GameData {
    /**
     * Nom de la partie
     */
    private String name;
    /**
     * Nom de la map
     * Donnée à l'instanciation, elle ne doit, dans auncun cas, être changée plus tard
     */
    private String mapName;
    /**
     * Description de la partie
     */
    private String description;
    /**
     * Durée
     */
    private int duration;
    /**
     * Nombre de joueurs maximum
     */
    private int maxPlayers;

    /**
     * Attribut description
     */
    public final static String DESCRIPTION = "description";
    /**
     * Attribut nom
     */
    public final static String NAME = "name";
    /**
     * Attribut nom de la partie
     */
    public final static String MAP_NAME = "mapName";
    /**
     * Attribut durée
     */
    public final static String DURATION = "duration";
    /**
     * Attribut nombre de joueurs maximum
     */
    public final static String MAX_PLAYERS = "maxPLayers";

    /**
     * @param mapName Nom de la map
     */
    public GameData(String mapName, String name, String description, int duration, int maxPlayers){
        this.mapName = mapName;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
    }

    /**
     * @param data Valeurs des attributs
     */
    public GameData(HashMap<String,String> data){
        ArrayList<String> dataName = new ArrayList<>();
        dataName.add(MAP_NAME);
        dataName.add(DESCRIPTION);
        dataName.add(NAME);
        dataName.add(DURATION);
        dataName.add(MAX_PLAYERS);

        for(String name : dataName){
            String get = data.get(name);
            if(get != null){
                switch(name){
                    case MAP_NAME:
                        this.mapName = get;
                        break;
                    case NAME:
                        this.name = get;
                        break;
                    case DESCRIPTION:
                        this.description = get;
                        break;
                    case MAX_PLAYERS:
                        this.maxPlayers = Integer.parseInt(get);
                        break;
                    case DURATION:
                        this.duration = Integer.parseInt(get);
                        break;
                }
            } else
                throw new IllegalArgumentException("Attribut " + name + " manquant");
        }
    }

    /**
     * Obtenir le nom de la map
     * @return Nom de la map
     */
    public String getMapName(){
        return this.mapName;
    }

    /**
     * Obtenir le nom
     * @return Nom
     */
    public String getName(){
        return this.name;
    }

    /**
     * Obtenir la description
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtenir la durée
     * @return Durée
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Obtenir le nombre de joueurs maximum
     * @return Nombre de joueurs maximum
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }


    /**
     * Obtenir si multijoueurs
     * @return true si multijoueurs, false sinon
     */
    public boolean isMultiPlayer() {
        return (this.maxPlayers > 1);
    }

    /**
     * Obtenir les données
     * @return Données
     */
    public HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        data.put(NAME, this.name);
        data.put(MAP_NAME, this.mapName);
        data.put(DESCRIPTION, this.description);
        data.put(MAX_PLAYERS, String.valueOf(this.maxPlayers));
        data.put(DURATION, String.valueOf(this.duration));

        return data;
    }
}

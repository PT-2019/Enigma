package modifs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Donnée propre à une map
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
     * Donné à la création de la map, il ne peut être changé plus tard
     */
    private String author;
    /**
     * Nom de la map
     */
    private String name;
    /**
     * Description de la map
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
     * Est multijoueurs
     */
    private boolean isMultiPlayer;

    /**
     * Attribut auteur
     */
    public final static String AUTHOR = "author";
    /**
     * Attribut description
     */
    public final static String DESCRIPTION = "description";
    /**
     * Attribut nom
     */
    public final static String NAME = "name";
    /**
     * Attribut durée
     */
    public final static String DURATION = "duration";
    /**
     * Attribut nombre de joueurs maximum
     */
    public final static String MAX_PLAYERS = "maxPLayers";
    /**
     * Attribut est multijoueurs
     */
    public final static String MULTI_PLAYERS = "multiPlayers";

    /**
     * @param author Auteur
     */
    public MapData(String author){
        this.author = author;
        this.name = "unnamed";
        this.description = "";
        this.duration = 5;
        this.maxPlayers = 1;
        this.isMultiPlayer = false;
    }

    /**
     * @param data Valeurs des attributs
     */
    public MapData(HashMap<String,String> data){
        ArrayList<String> dataName = new ArrayList<>();
        dataName.add(AUTHOR);
        dataName.add(DESCRIPTION);
        dataName.add(NAME);
        dataName.add(DURATION);
        dataName.add(MAX_PLAYERS);
        dataName.add(MULTI_PLAYERS);

        for(String name : dataName){
            String get = data.get(name);
            if(get != null){
                switch(name){
                    case AUTHOR:
                        this.author = get;
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
                    case MULTI_PLAYERS:
                        this.isMultiPlayer = Boolean.parseBoolean(get);
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
     * Définir la description
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Définir la durée
     * @param duration Durée
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Définir le nombre de joueurs maximum
     * @param maxPlayers Nombre de joueurs maximum
     */
    public void setMax_players(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Définir si multijoueurs
     * @param multiPlayer true si multijoueurs, false sinon
     */
    public void setMultiPlayer(boolean multiPlayer) {
        isMultiPlayer = multiPlayer;
    }

    /**
     * Définir le nom
     * @param name Nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtenir l'auteur
     * @return L'auteur
     */
    public String getAuthor(){
        return this.author;
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
    public int getMax_players() {
        return maxPlayers;
    }
    /**
     * Obtenir si multijoueurs
     * @return true si multijoueurs, false sinon
     */
    public boolean isMultiPlayer() {
        return isMultiPlayer;
    }

    /**
     * Obtenir les données
     * @return Données
     */
    public HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        data.put(NAME, this.name);
        data.put(AUTHOR, this.author);
        data.put(DESCRIPTION, this.description);
        data.put(MAX_PLAYERS, String.valueOf(this.maxPlayers));
        data.put(DURATION, String.valueOf(this.duration));
        data.put(MULTI_PLAYERS, String.valueOf(this.isMultiPlayer));

        return data;
    }
}

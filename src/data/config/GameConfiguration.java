package data.config;

import common.data.GameData;

import java.util.ArrayList;

/**
 * Configuration de la partie actuelle
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class GameConfiguration {
    /**
     * Instance
     */
    private final static GameConfiguration instance = new GameConfiguration();
    /**
     * Données de la partie
     */
    private GameData data;
    /**
     * Chef de la partie
     */
    private String owner;
    /**
     * Joueurs dans la partie
     */
    private ArrayList<String> players;

    /**
     * Nombre maximum de joueurs dans une partie
     */
    public final static int MAX_GAME_PLAYERS = 4;

    private GameConfiguration(){
        this.owner = "";
        this.players = new ArrayList<>();
        this.data = new GameData("","","","",0,0);
    }

    /**
     * Instancie de nouvelles configurations
     * Le chef de la partie est automatiquement ajouté a la liste des joueurs
     *
     * @param data Données de la partie
     * @param owner Chef
     * @param players Joueurs
     */
    public void set(GameData data, String owner, ArrayList<String> players){
        this.owner = owner;
        this.players = players;
        this.data = data;
        this.addPLayer(this.owner);
    }

    /**
     * Obtenir les données de la partie (ce ne comprends pas toutes les configurations)
     * @return Les données de la partie
     */
    public GameData getData(){
        return this.data;
    }

    /**
     * Obtenir le chef
     * @return Chef
     */
    public String getOwner(){
        return this.owner;
    }

    /**
     * Obtenir les joueurs
     * @return Les joueurs
     */
    @SuppressWarnings("unchecked")
    public ArrayList<String> getAllPlayers(){
        return (ArrayList<String>) this.players.clone();
    }

    /**
     * Supprimer les joueurs
     */
    public void removeAllPlayers(){
        this.players = new ArrayList<>();
    }

    /**
     * Ajouter un joueur
     * @param player Joueur
     */
    public void addPLayer(String player){
        if(!this.isFull() && !this.players.contains(player))
            this.players.add(player);
    }

    /**
     * Retirer un joueur
     * @param player Joueur
     */
    public void removePlayer(String player){
        this.players.remove(player);
    }

    /**
     * Obtenir le nom de la partie
     * @return Nom de la partie
     */
    public String getGameName(){
        return this.data.getName();
    }

    /**
     * Obtenir le nom de la map
     * @return Nom de la map
     */
    public String getMapName(){
        return this.data.getMapName();
    }

    /**
     * Obtenir la description
     * @return Description
     */
    public String getDescription(){
        return this.data.getDescription();
    }

    /**
     * Obtenir l'auteur
     * @return Auteur
     */
    public String getAuthor(){
        return this.data.getAuthor();
    }

    /**
     * Obtenir le nombre de joueurs maximum de la partie
     * @return Nombre de joueurs maximum de la partie
     */
    public int getMaxPlayers(){
        return this.data.getMaxPlayers();
    }

    /**
     * Obtenir le nombre de joueur actuellement dans la partie
     * @return Nombre de joueur actuellement dans la partie
     */
    public int getTotalPlayers(){
        return this.players.size();
    }

    /**
     * Indique si le nombre de joueurs actels est égal au nombre de joueurs maximum
     * @return true si le nombre de joueurs équivaut au nombre de joueurs maximum, false sinon
     */
    public boolean isFull() {
        return (this.getTotalPlayers() == this.getMaxPlayers());
    }

    /**
     * Obtenir la durée
     * @return Durée
     */
    public int getDuration(){
        return this.data.getDuration();
    }

    /**
     * Est multijoueurs
     * @return true si multijoueurs, false sinon
     */
    public boolean isMultiPlayers(){
        return this.data.isMultiPlayer();
    }

    /**
     * Obtenir l'instance
     * @return Instance
     */
    public static GameConfiguration getInstance(){
        return instance;
    }
}

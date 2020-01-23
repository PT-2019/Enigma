package game;

import editor.entity.Player;

import java.util.ArrayList;

/**
 * Défini les configurations et informations de la partie actuelle
 * Ces informations peuvent être celle d'une partie locale ou d'une partie multijoueurs
 * Dans ce second cas, elles doivent contenir les mêmes informations et configurations que celle du créateur de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class GameConfiguration {

    /**
     * Instance
     */
    private final static GameConfiguration instance = new GameConfiguration();

    /**
     * Est multijoueur (true) ou non (false)
     */
    private boolean isMultiPlayer;

    /**
     * Nombre de joueurs maximum
     */
    private int maxGamePlayers;

    /**
     * Durée en minute
     */
    private int duration;

    /**
     * Map
     */
    private String map;

    /**
     * Nom de la partie
     */
    private String name;

    /**
     * Description de la partie
     */
    private String description;

    /**
     * Chef de la partie
     */
    private Player owner;

    /**
     * Liste des joueurs, y compris le chef de la partie
     */
    private ArrayList<Player> players;

    /**
     * Nombre maximum de joueurs autorisés par parties
     */
    public final static int MAX_PLAYERS = 4;

    private GameConfiguration(){
        this.duration = 5;
        this.map = "demo";
        this.name = "";
        this.description = "";
        this.players = new ArrayList<>();
        this.setMultiPlayer(false);
    }

    /**
     * Obtenir l'instance
     * @return L'instance
     */
    public static GameConfiguration getInstance(){
        return instance;
    }

    /**
     * Indiquer la partie est multijoueurs
     * Si false, le nombre de joueurs est automatiquement passé à
     * Si true, le nombre de joueurs est automatiquement passé au nombre maximum de joueurs autorisés par parties
     * @param isMultiPlayer true pour une partie multijoueurs, false sinon
     */
    public void setMultiPlayer(boolean isMultiPlayer){
        this.isMultiPlayer = isMultiPlayer;
        if(isMultiPlayer) this.maxGamePlayers = MAX_PLAYERS;
        else{
            this.maxGamePlayers = 1;
            this.players.removeIf(p -> !p.equals(this.owner));
            if(this.owner != null && !this.players.contains(this.owner))
                this.players.add(this.owner);
        }
    }

    /**
     * Indique si la partie est multijoueurs
     * @return true si la partie est multijoueurs, false sinon
     */
    public boolean isMultiPlayer(){
        return this.isMultiPlayer;
    }

    /**
     * Indique si la partie contient autant de joueurs que le nombre de joueurs maximum
     * @return true si la partie contient autant de joueurs que le nombre de joueurs maximum, false sinon
     */
    public boolean isFull(){
        return (this.getTotalPlayers() == this.getMaxGamePlayers());
    }

    /**
     * Défini le nombre de joueurs maximum
     * Si number <= 0 ou > au nombre maximum de joueurs autorisé par parties, le changement n'est pas pris en compte
     * @param number Nombre de joueurs maximum
     */
    public void setMaxGamePlayers(int number){
        if(number > 0 && number <= MAX_PLAYERS)
            this.maxGamePlayers = number;
    }

    /**
     * Obtenir le nombre maximum de joueurs
     * @return Le nombre maximum de joueurs
     */
    public int getMaxGamePlayers() {
        return this.maxGamePlayers;
    }

    /**
     * Définir la durée
     * @param duration Durée
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * Obtenir la durée
     * @return Durée
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Définir la map
     * @param map La map
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * Obtenir la map
     * @return La map
     */
    public String getMap() {
        return this.map;
    }

    /**
     * Définir le nom de la partie
     * @param name Nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtenir le nom de la partie
     * @return Nom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Définir la description de la partie
     * @param description Descritpion
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtenir la description de la partie
     * @return Description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtenir le chef de la partie
     * @return Chef de la partie
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Définir le chef de la partie
     * @param owner Chef de la partie
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        if(!this.players.contains(this.owner))
            this.players.add(this.owner);
    }

    /**
     * Un joueur un rejoint la partie
     * @param player Joueur ayant rejoint
     */
    public void playerJoined(Player player){
        if(this.players.size() < this.maxGamePlayers)
            this.players.add(player);
    }

    /**
     * Un joueur un quitté la partie
     * @param player Joueur ayant quitté
     */
    public void playerLeft(Player player){
        this.players.remove(player);
    }

    /**
     * Obtenir la liste des joueurs
     * @return Une liste des joueurs
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Player> getAllPlayers(){
        return (ArrayList<Player>)this.players.clone();
    }

    /**
     * Obtenir le nombre de joueurs
     * @return Nombre de joueurs
     */
    public int getTotalPlayers(){
        return this.players.size();
    }
}

package game;

import editor.entity.Player;

import java.util.ArrayList;

public class GameConfiguration {

    private final static GameConfiguration instance = new GameConfiguration();

    private boolean isMultiPlayer;
    private int maxGamePlayers;
    private double duration;
    private String map;
    private String gameName;
    private Player owner;
    private ArrayList<Player> players;

    public final static int MAX_PLAYERS = 4;

    private GameConfiguration(){
        this.duration = 5.0;
        this.map = "demo";
        this.gameName = "";
        this.players = new ArrayList<>();
        this.setMultiPlayer(false);
    }

    public static GameConfiguration getInstance(){
        return instance;
    }

    public void setMultiPlayer(boolean isMultiPlayer){
        this.isMultiPlayer = isMultiPlayer;
        if(isMultiPlayer) this.maxGamePlayers = MAX_PLAYERS;
        else{
            this.maxGamePlayers = 1;
            this.players.removeIf(p -> !p.equals(this.owner));
            if(this.owner != null)
                this.players.add(this.owner);
        }
    }

    public boolean isMultiPlayer(){
        return this.isMultiPlayer;
    }

    public void setMaxGamePlayers(int number){
        if(number > 0 && number <= MAX_PLAYERS)
            this.maxGamePlayers = number;
    }

    public int getMaxGamePlayers() {
        return this.maxGamePlayers;
    }

    public void setDuration(double duration){
        this.duration = duration;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMap() {
        return this.map;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return this.gameName;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        if(!this.players.contains(this.owner))
            this.players.add(this.owner);
    }

    public void playerJoined(Player player){
        if(this.players.size() < this.maxGamePlayers)
            this.players.add(player);
    }

    public void playerLeft(Player player){
        this.players.remove(player);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Player> getAllPlayers(){
        return (ArrayList<Player>)this.players.clone();
    }

    public int getTotalPlayers(){
        return this.players.size();
    }
}

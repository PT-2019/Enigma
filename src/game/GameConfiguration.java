package game;

import editor.entity.player.Player;

public class GameConfiguration {

    private final static GameConfiguration instance = new GameConfiguration();

    private boolean isMultiPlayer;
    private int playerNumber;
    private double duration;
    private String map;
    private String gameName;
    private Player owner;

    private GameConfiguration(){
        this.setMultiPlayer(false);
        this.duration = 5.0;
        this.map = "demo";
        this.gameName = "";
    }

    public static GameConfiguration getInstance(){
        return instance;
    }

    public void setMultiPlayer(boolean isMultiPlayer){
        this.isMultiPlayer = isMultiPlayer;
        if(isMultiPlayer) this.playerNumber = 2;
        else this.playerNumber = 1;
    }

    public boolean isMultiPlayer(){
        return this.isMultiPlayer;
    }

    public void setPlayerNumber(int number){
        this.playerNumber = number;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setDuration(double duration){
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}

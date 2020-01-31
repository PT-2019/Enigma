package modifs;

import java.util.ArrayList;
import java.util.HashMap;

public class MapData {
    private String author;
    private String name;
    private String description;
    private int duration;
    private int maxPlayers;
    private boolean isMultiPlayer;

    public final static String AUTHOR = "author";
    public final static String DESCRIPTION = "description";
    public final static String NAME = "name";
    public final static String DURATION = "duration";
    public final static String MAX_PLAYERS = "maxPLayers";
    public final static String MULTI_PLAYERS = "multiPlayers";

    public MapData(){
        this.author = "non author";
        this.name = "unnamed";
        this.name = "";
        this.duration = 5;
        this.maxPlayers = 1;
        this.isMultiPlayer = false;
    }

    public void initFromData(HashMap<String,String> data){
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMax_players(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setMultiPlayer(boolean multiPlayer) {
        isMultiPlayer = multiPlayer;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getMax_players() {
        return maxPlayers;
    }

    public boolean isMultiPlayer() {
        return isMultiPlayer;
    }

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

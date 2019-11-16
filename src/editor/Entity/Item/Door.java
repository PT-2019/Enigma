package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Interface.Passage;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

public class Door implements Item, Passage, Lockable {

    private ArrayList<Enigma> enigmas;
    private boolean locked;
    private String dialog;
    private Texture texture;
    private Room room1;
    private Room room2;

    public Door(){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = true;
    }

    public Door(boolean locked){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = locked;
    }

    @Override
    public void interactsWith(Player p){
        for(Enigma e : this.enigmas){
            if(!e.isKnown()) e.discovered();
            else e.verifyConditions(p);
        }
    }

    @Override
    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public void showDialog() {}

    @Override
    public void addEnigma(Enigma e) {
        if(this.enigmas.contains(e)) throw new IllegalArgumentException("Cette énigme existe déjà dans la liste");
        this.enigmas.add(e);
    }

    @Override
    public void removeEnigma(Enigma e) {
        this.enigmas.remove(e);
    }

    @Override
    public Room getRoom1() {
        return this.room1;
    }

    @Override
    public Room getRoom2() {
        return this.room2;
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public String toString(){
        return "[Door  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2;
    }

    public String toLongString(){
        StringBuilder s = new StringBuilder("[Door  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", Room1 = " + this.room1 + ", Room2 = " + this.room2 + ", enigmas = {");
        int size = this.enigmas.size() - 1;
        int i = 0;
        for(Enigma e : this.enigmas) {
            s.append(e.toLongString());
            if(i < size) s.append(", ");
            i++;
        }
        s.append("}]");
        return s.toString();
    }
}

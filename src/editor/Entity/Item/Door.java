package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Interface.Passage;
import editor.Entity.Player.Player;
import editor.texture.Texture;
import org.lwjgl.Sys;

import java.util.ArrayList;

public class Door implements Item, Passage, Lockable {

    private ArrayList<Enigma> enigmas;
    private boolean locked;

    public Door(){
        this.enigmas = new ArrayList<Enigma>();
        this.locked = true;
    }

    @Override
    public void interactsWith(Player p){
        for(Enigma e : this.enigmas){
            if(!e.isKnown()) e.hasBeenDiscovered();
            e.verifyConditions(p);
        }
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public void showDialog() {}

    @Override
    public void addEnigma(Enigma e) {
        this.enigmas.add(e);
    }

    @Override
    public void removeEnigma(Enigma e) {
        this.enigmas.remove(e);
    }

    @Override
    public Room getRoom1() {
        return null;
    }

    @Override
    public Room getRoom2() {
        return null;
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
}

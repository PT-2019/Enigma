package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Interface.Passage;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

public class Chest implements Item, Lockable {

    private ArrayList<Enigma> enigmas;
    private boolean locked;
    private Texture texture;
    private String dialog;

    public Chest(){
        this.locked = false;
        this.enigmas = new ArrayList<Enigma>();
    }

    public Chest(boolean locked){
        this.locked = locked;
        this.enigmas = new ArrayList<Enigma>();
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
        return "[Chest  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture;
    }

    public String toLongString(){
        StringBuilder s = new StringBuilder("[Chest  : dialog = " + this.dialog + ", locked = " + this.locked + ", texture = " + this.texture + ", enigmas = {");
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

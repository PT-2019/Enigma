package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Content;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

public class Book implements Item, Content {

    private ArrayList<Enigma> enigmas;

    public Book(){
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
    public void addContent(String content) {}

    @Override
    public String getContent() {
        return null;
    }
}

package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Content;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

public class Book implements Item, Content {

    private ArrayList<Enigma> enigmas;
    private String content;
    private String dialog;
    private Texture texture;

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
        return this.texture;
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
    public void addContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String toString(){
        return "[Book  : dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture;
    }
    public String toLongString(){
        StringBuilder s = new StringBuilder("[Book  : dialog = " + this.dialog + ", content = " + this.content + ", texture = " + this.texture + ", enigmas = {");
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

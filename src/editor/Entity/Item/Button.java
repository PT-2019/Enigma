package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Player.Player;
import editor.texture.Texture;

import java.util.ArrayList;

public class Button extends Activatable {

    private ArrayList<Enigma> enigmas;
    private Texture texture;
    private String dialog;

    public Button() {
        super(false);
        this.enigmas = new ArrayList<Enigma>();
    }

    public Button(boolean activated) {
        super(activated);
        this.enigmas = new ArrayList<Enigma>();
    }

    @Override
    public void interactsWith(Player p) {
        this.activated = !this.activated;

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
    public boolean isActivated() {
        return this.activated;
    }

    @Override
    public String toString(){
        return "[Button  : dialog = " + this.dialog + ", activated = " + this.activated + ", texture = " + this.texture;
    }

    public String toLongString(){
        StringBuilder s = new StringBuilder("[Button  : dialog = " + this.dialog + ", activated = " + this.activated + ", texture = " + this.texture + ", enigmas = {");
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

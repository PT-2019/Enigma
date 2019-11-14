package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Player.Player;
import editor.texture.Texture;

public class Button extends Activatable {

    public Button() {
        super(false);
    }

    public Button(boolean activated) {
        super(activated);
    }

    @Override
    public void interactsWith(Player p) {
        this.activated = !this.activated;
    }

    @Override
    public Texture getTexture() {return null;}

    @Override
    public void showDialog() {}

    @Override
    public void addEnigma(Enigma e) {}

    @Override
    public void removeEnigma(Enigma e) {}

    @Override
    public boolean isActivated() {
        return this.activated;
    }
}

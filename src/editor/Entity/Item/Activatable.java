package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

public abstract class Activatable implements Item {

    protected boolean activated;

    @Override
    public abstract void interactsWith(Player p);
    @Override
    public abstract Texture getTexture();
    @Override
    public abstract void showDialog();
    @Override
    public abstract void addEnigma(Enigma e);
    @Override
    public abstract void removeEnigma(Enigma e);
    public abstract boolean isActivated();

}

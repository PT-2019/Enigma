package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Interface.Passage;
import editor.Entity.Player.Player;
import editor.texture.Texture;

public class Chest implements Item, Lockable {
    @Override
    public void interactsWith(Player p){}

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public void showDialog() {}

    @Override
    public void addEnigma(Enigma e) {}

    @Override
    public void removeEnigma(Enigma e) {}

    @Override
    public void lock() {}

    @Override
    public void unlock() {}

    @Override
    public boolean isLocked() {
        return false;
    }
}

package editor.Entity.Item;

import editor.Entity.Interface.Item;
import editor.Entity.Interface.Passage;
import editor.texture.Texture;

public class Door implements Item, Passage {
    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public void showDialog() {

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
    public boolean isLocked() {
        return false;
    }
}

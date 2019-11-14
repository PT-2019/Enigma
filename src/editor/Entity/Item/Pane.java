package editor.Entity.Item;

import editor.Enigma.Enigma;
import editor.Entity.Interface.Content;
import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;
import editor.texture.Texture;

public class Pane implements Content, Item {
    @Override
    public void interactsWith(Player p){}

    @Override
    public void addContent(String content) {}

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public Texture getTexture() {return null;}

    @Override
    public void showDialog() {}

    @Override
    public void addEnigma(Enigma e) {}

    @Override
    public void removeEnigma(Enigma e) {}
}

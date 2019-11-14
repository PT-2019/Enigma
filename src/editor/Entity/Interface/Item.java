package editor.Entity.Interface;

import editor.Enigma.Enigma;
import editor.Entity.Player.Player;
import editor.texture.Texture;

public interface Item extends EnigmaContainer, Entity {
    public void interactsWith(Player p);
    public Texture getTexture();
    public void showDialog();
    public void addEnigma(Enigma e);
    public void removeEnigma(Enigma e);
}

package editor.Entity.Interface;

import editor.Entity.Player.Player;
import editor.texture.Texture;

public interface Entity{
    public void interactsWith(Player p);
    public Texture getTexture();
    public void showDialog();
}

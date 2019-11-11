package editor.Entity.Interface;

import editor.texture.Texture;

public interface Item extends Entity {

    public Texture getTexture();
    public void showDialog();
}

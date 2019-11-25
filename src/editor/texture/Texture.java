package editor.texture;

import java.awt.*;

/**
 * Une texture possèdant une image la représentant
 * @see TextureArea
 * @see TextureProxy
 */
public class Texture {

    private int position;

    private Image image;

    public Texture(int pos,Image image){
        this.position = pos;
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }
}

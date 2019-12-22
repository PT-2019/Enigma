package editor.textures;

import java.awt.*;

/**
 * Une texture possèdant une image la représentant
 *
 * @see TextureArea
 * @see TextureProxy
 */
public class Texture {

    private final Image texture;
    private int position;

    public Texture(int pos, Image texture) {
        this.position = pos;
        this.texture = texture;
    }

    public Image getImage() {
        return texture;
    }

    public int getPosition() {
        return position;
    }
}

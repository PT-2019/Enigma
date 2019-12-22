package editor.utils.textures;

import java.awt.Image;

/**
 * Une texture possèdant une image la représentant
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 2.0
 * @since 1.0
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

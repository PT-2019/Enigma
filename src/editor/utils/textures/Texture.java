package editor.utils.textures;

import java.awt.Image;

/**
 * Une texture possèdant une image la représentant
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see TextureArea
 * @see TextureProxy
 * @since 1.0
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

	/**
	 * Un int de 0 a nombre de sous textures dans la texture.
	 * Peut commencer a une valeur différente selon son ordre de création par le proxy.
	 * Dans le proxy, cette valeur est unique.
	 * @return valeur unique qui représente la texture.
	 */
	public int getPosition() {
		return position;
	}
}

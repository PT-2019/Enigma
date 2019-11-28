package editor.textures;

import java.awt.*;

public class Texture {
	private final Image texture;

	public Texture(Image texture) {

		this.texture = texture;
	}

	public Image getImage() {
		return texture;
	}
}

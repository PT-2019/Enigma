package editor.io;

import editor.textures.Texture;
import editor.textures.TextureProxy;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Représentation du fichier .atlas (jSon) représentant une texture.
 * <p>
 * La méthode la plus interessante est {@link #getTexture(String)} qui renvoi
 * l'image correspond a cette String (nom de la sous-texture).
 *
 * @version 2.0 27 novembre 2019
 */
public class AtlasFile implements Serializable {

	/**
	 * chemin de l'image
	 */
	private final String image;
	/**
	 * map des index (noms) et de leur n+1 ligne
	 */
	private final HashMap<String, Integer> map;
	/**
	 * garde les textures déjà chargées
	 */
	private final HashMap<String, Texture> loadedSubTextures;
	/**
	 * le fichier sous la forme d'un tableau de string
	 */
	private final String[] jsonFile;

	/**
	 * Crée un fichier json
	 *
	 * @param imagePath chemin de l'image
	 * @param mapIndex  map des index (noms) et de leur n+1 ligne
	 * @param jsonFile  le fichier sous la forme d'une string
	 */
	public AtlasFile(String imagePath, HashMap<String, Integer> mapIndex, String[] jsonFile) {
		this.image = imagePath;
		this.map = mapIndex;
		this.jsonFile = jsonFile;
		this.loadedSubTextures = new HashMap<>();
	}

	/**
	 * Renvoi la sous texture d'une image correspondant a ce nom d'index
	 *
	 * @param name nom d'index
	 * @return la sous texture d'une image correspondant a ce nom d'index
	 * @since 2.0
	 */
	public Texture getTexture(String name) {
		if (!loadedSubTextures.containsKey(name)) {
			Image subTexture = JsonSubTexture.getSubTexture(jsonFile, image, map.get(name));
			this.loadedSubTextures.put(name, new Texture(map.get(name), subTexture)); //TODO: pb ici
		}

		return loadedSubTextures.get(name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("JsonFile\n" + "image='" + image + "'\n" + "map=\n");
		//print map
		for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
			sb.append(entry.getKey());
			sb.append(" ");
			sb.append(entry.getValue());
			sb.append(",");
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Class utilitaire qui charge la sous texture d'un fichier json.
	 *
	 * @version 2.0 27 novembre 2019
	 */
	private static final class JsonSubTexture {

		/**
		 * Retourne la sous-texture d'une image
		 *
		 * @param jsonFile le fichier json
		 * @param image    le chemin de l'image
		 * @param bounds   l'indice de la ligne des informations
		 * @return la sous-texture d'une image
		 */
		private static Image getSubTexture(final String[] jsonFile, final String image, Integer bounds) {
			Point pos = getPosition(jsonFile[bounds + 1]);//should be at line 2
			Dimension size = getSize(jsonFile[bounds + 2]);//should be at line 3
			return TextureProxy.getImage(pos.x, pos.y, size.width, size.height, image);
		}

		/**
		 * Retourne la taille d'une sous-texture
		 *
		 * @param json fichier json
		 * @return la taille d'une sous-texture
		 */
		private static Dimension getSize(final String json) {
			int width, height;
			String[] values = json.split("size: ")[1].split(",");

			width = Integer.parseInt(values[0]);
			height = Integer.parseInt(values[1].trim());

			return new Dimension(width, height);
		}

		/**
		 * Retourne la position d'une sous-texture
		 *
		 * @param json fichier json
		 * @return la position d'une sous-texture
		 */
		private static Point getPosition(String json) {
			int x, y;
			String[] values = json.split("xy: ")[1].split(",");

			x = Integer.parseInt(values[0]);
			y = Integer.parseInt(values[1].trim());

			return new Point(x, y);
		}
	}
}


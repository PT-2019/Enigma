package common.utils.textures;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * La classe Texture permet de récupérer une sous-texture ({@link TextureArea}
 * d'une Image.
 * <p>
 * Une texture doit d'abord être ajoutée {@link #addTexture(int, String, int, int, int)}.
 * On peut ensuite accéder a la sous-texture via un indice {@link #getImage(int)}.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0 28 novembre 2019
 * @since 1.0
 */
public class TextureProxy {

	/**
	 * Toutes les textures déjà chargées sont stockées ici
	 *
	 * @see TextureArea
	 */
	private static LinkedHashMap<String, TextureArea> textures = new LinkedHashMap<>();

	/**
	 * Renvoi la sous-texture
	 *
	 * @param x      la position x de la sous-texture dans l'image
	 * @param y      la position y de la sous-texture dans l'image
	 * @param width  largeur de la sous-texture dans l'image
	 * @param height hauteur de la sous-texture dans l'image
	 * @param path   chemin de l'image
	 * @return la sous-texture
	 * @throws IllegalStateException si la lecture échoue
	 */
	public static Image getImage(int x, int y, int width, int height, String path) {
		if (!textures.containsKey(path)) {
			textures.put(path, new TextureArea(width, height, path));
		}
		return textures.get(path).getImage(x, y, width, height);
	}

	public void addTexture(int tileSize, String path, int nbCol, int min, int max) {
		//on vérifie si il y a pas déjà la texture
		if (!textures.containsKey(path)) {
			textures.put(path, new TextureArea(tileSize, tileSize, path, nbCol, min, max));
		}
	}

	/**
	 * Charge la sous-texture (son image doit être chargé {@link #addTexture(int, String, int, int, int)}).
	 *
	 * @param id_texture son indice dans l'image
	 * @return Sous-Image correspondante ou
	 * null si la sous-texture n'a pas été trouvée
	 */
	public Image getImage(int id_texture) {
		Image img = null;
		for (TextureArea t : textures.values()) {
			if (t.getMax() >= id_texture && t.getMin() <= id_texture) {
				img = t.getImage(id_texture);
			}
		}
		return img;
	}

	/**
	 * Charge en mémoire toutes les textures décrites dans le fichier donné en argument.
	 * Voir le format du fichier en question.
	 *
	 * @param pathFile chemin du fichier
	 * @throws NumberFormatException causé par un fichier mal conçu
	 * @deprecated since 2.0 car le format a été changé
	 */
	@Deprecated()
	public void loadTexture(String pathFile) {
		BufferedReader buff;
		FileReader file;
		String line;
		StringBuilder tmpString = new StringBuilder();
		String[] info = new String[5];
		char currentChar;

		try {

			file = new FileReader(pathFile);
			buff = new BufferedReader(file);

			while ((line = buff.readLine()) != null) {

				for (int j = 0, i = 0; i < line.length(); i++) {
					currentChar = line.charAt(i);

					if (currentChar == ' ') {

						info[j] = tmpString.toString();
						j++;
						tmpString = new StringBuilder();
					} else {
						tmpString.append(currentChar);
					}
				}

				info[4] = tmpString.toString();

				this.addTexture(Integer.parseInt(info[1]), info[0], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]));

				tmpString = new StringBuilder();
			}

			file.close();
			buff.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<TextureArea> getTextures() {
		return new ArrayList<>(textures.values());
	}
}

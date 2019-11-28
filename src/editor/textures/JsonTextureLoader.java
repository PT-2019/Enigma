package editor.textures;

import java.awt.Image;
import java.util.HashMap;

/**
 * Charge une sous-texture depuis son nom et son fichier .atlas
 *
 * @version 2.0
 */
public class JsonTextureLoader {

	/**
	 * garde les anciennes textures pour éviter de devoir le recharger
	 * String = chemin du .atlas
	 * JSonFile = fichier .atlas
	 */
	private static HashMap<String, JsonFile> loadedTextures = new HashMap<>();

	/**
	 * Renvoi l'image correspond a la sous-texture name dans file.
	 *
	 * @param name nom de la clef (=son nom) de la sous-texture
	 * @param file le chemin du fichier .atlas
	 * @return la sous-image voulue
	 * @since 2.0
	 */
	public static Image getTexture(String name, String file){
		if(!JsonTextureLoader.loadedTextures.containsKey(file)){
			JsonFile json = JsonReader.importJson(file);
			JsonTextureLoader.loadedTextures.put(file, json);
		}
		return JsonTextureLoader.loadedTextures.get(file).getTexture(name);
	}
}

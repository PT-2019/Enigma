package editor.utils.json;

import api.utils.Utility;

import java.io.File;
import java.util.HashMap;

/**
 * Classe qui importe un fichier JSon (extension .atlas ou .pack) dans le programme.
 * <p>
 * Voici un exemple de fichier :
 * <p>
 * *ligne vide*
 * test.png
 * size: 2048,1024
 * format: RGBA8888
 * filter: Nearest,Nearest
 * repeat: none
 * tile000
 * rotate: false
 * xy: 70, 580
 * size: 32, 32
 * orig: 32, 32
 * offset: 0, 0
 * index: -1
 * tile 001
 * ...
 * <p>
 * Le logiciel libgdx-texture packer génère automatiquement ce genre de fichiers.
 *
 * @version 2.0
 */
public class AtlasReader {

	/**
	 * Importe un fichier json dans le programme et renvoi un objet le représentant
	 *
	 * @param path chemin du fichier
	 * @return le fichier lu sous la forme d'un JsonFile
	 * @throws IllegalStateException si le fichier n'est pas valide.
	 * @see AtlasFile
	 * @since 2.0
	 */
	public static AtlasFile importJson(final String path) {
		final int lastSlash = path.lastIndexOf(File.separator);
		String[] jsonFile = Utility.readFile(path).split("\n");//read file
		String imagePath;
		if (lastSlash > 0)//imagePath is relative to .atlas file so we append .atlas's path if needed
			imagePath = path.substring(0, lastSlash) + "/" + jsonFile[1];
		else imagePath = jsonFile[1];

		final StringBuilder sb = new StringBuilder();//string builder for errors
		HashMap<String, Integer> map = new HashMap<>();//map sub-texture name => line in the file

		if (!jsonFile[0].isEmpty())//first line should be empty
			sb.append("should start with an empty line.");

		for (int i = 6; i < jsonFile.length; i++) {//file should start a 6th line
			if (!jsonFile[i].startsWith("  ")) {
				map.put(jsonFile[i], i + 1);
			}
		}

		if (map.isEmpty())
			sb.append("Le fichier ne contient aucune donnée.");

		if (!sb.toString().isEmpty())
			throw new IllegalStateException(sb.toString());

		return new AtlasFile(imagePath, map, jsonFile);

	}
}


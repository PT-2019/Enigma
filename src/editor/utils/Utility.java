package editor.utils;

import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe lit un fichier {@link #readFile(String)} et le renvoi sous la forme d'une String.
 *
 * @version 2.0 27 novembre 2019
 */
public class Utility {

	/**
	 * Cette méthode lit un fichier et le renvoi sous la forme d'une String.
	 * Les saut de lignes sont conservés.
	 *
	 * @param path le chemin du fichier
	 * @return le fichier sous la forme d'une String
	 * @throws IllegalStateException si la lecture échoue
	 * @since 2.0
	 */
	public static String readFile(String path) {
		StringBuilder string = new StringBuilder();//pour notre fichier
		BufferedReader bufferReader;

		try {
			//ouverture du flux
			bufferReader = new BufferedReader(new FileReader(new File(path)));
			String line; // stocke la ligne courante

			while ((line = bufferReader.readLine()) != null) {
				string.append(line);//ajoute la ligne
				string.append("\n");//et son saut de ligne //System.lineSeparator()
			}
			bufferReader.close(); //fermeture du flux
		} catch (IOException e) {//+file not found exception
			throw new IllegalStateException("lecture de fichier raté");
		}

		return string.toString();
	}

	public static String normalize(String string) {
		string = string.toLowerCase();//lower case
		//escape non ascii chars
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\x00-\\x7F]", "");
		return string;
	}

	public static <T extends java.lang.Enum> T stringToEnum(String name, T[] enumValues) {
		name = name.toLowerCase();//lower case
		//escape non ascii chars
		name = Normalizer.normalize(name, Normalizer.Form.NFD);
		name = name.replaceAll("[^\\x00-\\x7F]", "");

		for (T screen : enumValues) {
			if (screen.name().toLowerCase().equals(name))
				return screen;
		}

		throw new IllegalArgumentException("name not found " + name);
	}

	public static Reader loadFile(String path) {
		try {
			return new BufferedReader(new FileReader(path));
		} catch (IOException e) {
			throw new IllegalStateException("error loading file");
		}
	}

	public static void printHashMap(HashMap<?, ?> hashMap) {
		for (Map.Entry<?, ?> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + "->");
			Object value = entry.getValue();
			if (value instanceof ArrayList) {
				System.out.println(Arrays.toString(((ArrayList) value).toArray()));
			} else if (value instanceof Array) {
				System.out.println(Arrays.toString(((Array) value).toArray()));
			} else {
				System.out.println(value);
			}
		}
	}
}


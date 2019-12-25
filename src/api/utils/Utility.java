package api.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe lit un fichier {@link #readFile(String)} et le renvoi sous la forme d'une String.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 2.0 27 novembre 2019
 */
@ConvenienceClass
public class Utility implements Serializable {

	/**
	 * Cette méthode lit un fichier et le renvoi sous la forme d'une String.
	 * Les saut de lignes sont conservés.
	 *
	 * @param path le chemin du fichier
	 * @return le fichier sous la forme d'une String
	 * @throws IllegalStateException si la lecture échoue
	 * @since 2.0
	 */
	@ConvenienceMethod
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

	/**
	 * Cette méthode normalize une chaîne de caractère : supprime les accents et la
	 * met en minuscule
	 *
	 * @param string la chaîne a normaliser
	 * @return la chaîne normalisée
	 * @since 2.0
	 */
	@ConvenienceMethod
	public static String normalize(String string) {
		string = string.toLowerCase();//lower case
		//escape non ascii chars
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\x00-\\x7F]", "");
		return string;
	}

	/**
	 * Retourne un enum depuis son nom sous la forme d'un string
	 *
	 * @param name       nom de la valeur de l'enum
	 * @param enumValues tableau des valeurs d'un enum
	 * @param <T>        un enum
	 * @return l'enum correspond
	 * @throws IllegalArgumentException si la valeur n'est pas trouvée
	 * @since 2.0
	 */
	@ConvenienceMethod
	public static <T extends java.lang.Enum> T stringToEnum(String name, T[] enumValues) {
		name = normalize(name);

		for (T screen : enumValues) {
			if (screen.name().toLowerCase().equals(name))
				return screen;
		}

		throw new IllegalArgumentException("name not found " + name);
	}

	/**
	 * Charge un fichier et retourne son Reader
	 *
	 * @param path le chemin du fichier
	 * @return son Reader
	 * @since 2.0
	 */
	@ConvenienceMethod
	public static Reader loadFile(String path) {
		try {
			return new BufferedReader(new FileReader(path));
		} catch (IOException e) {
			throw new IllegalStateException("error loading file");
		}
	}

	/**
	 * Affiche une hashMap
	 *
	 * @param hashMap la hashMap
	 * @since 2.0
	 */
	@ConvenienceMethod
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

	/**
	 * Retourne un skin depuis json et un atlas
	 *
	 * @param json  fichier json
	 * @param atlas fichier atlas
	 * @return le fichier skin
	 * @since 4.0
	 */
	@ConvenienceMethod
	public static Skin loadSkin(String json, String atlas) {
		return new Skin(Gdx.files.internal(json), new TextureAtlas(atlas));
	}
}


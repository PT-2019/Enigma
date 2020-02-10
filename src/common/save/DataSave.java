package common.save;

import common.data.GameData;
import common.data.MapData;
import data.config.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Sauvegarde les données d'une map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01 fevrier 2020
 * @since 6.0
 */
public class DataSave {

	/**
	 * Balise d'en-tête
	 */
	private final static String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	/**
	 * Balise GameData de début
	 */
	private final static String START_GAME_DATA = "<GameData>";
	/**
	 * Balise GameData de fin
	 */
	private final static String END_GAME_DATA = "</GameData>";
	/**
	 * Balise MapData de début
	 */
	private final static String START_MAP_DATA = "<MapData>";
	/**
	 * Balise MapData de fin
	 */
	private final static String END_MAP_DATA = "</MapData>";
	/**
	 * Indentation
	 */
	private final static String INDENTATION = "  ";

	/**
	 * Convertie une donnée et sa valeur en une chaine unique avec une syntaxe précise
	 *
	 * @param name  Nom de la donnée
	 * @param value Valeur de la donnée
	 * @return Data et sa valeur dans une syntaxe
	 */
	private static String putInSyntax(String name, String value) {
		return "<" + name + ">" + value + "</" + name + ">";
	}

	/**
	 * Décode le nom d'une donnée
	 *
	 * @param s Chaine
	 * @return Nom de la donnée
	 */
	private static String getNameFromSyntax(String s) {
		return s.substring(s.indexOf("<"), s.indexOf(">")).replace("<", "").trim();
	}

	/**
	 * Décode la valeur d'une donnée
	 *
	 * @param s    Chaine
	 * @param name Nom de la donnée
	 * @return Valeur de la donnée
	 */
	private static String getValueFromSyntax(String s, String name) {
		return s.replace("<" + name + ">", "").replace("</" + name + ">", "").trim();
	}

	/**
	 * Indique si une donnée est contenue dans la chaine
	 *
	 * @param s Chaine
	 * @return Valeur de la donnée
	 */
	private static boolean isData(String s) {
		return !(s.equals(HEADER) || s.equals(START_GAME_DATA) || s.equals(END_GAME_DATA) || s.equals(START_MAP_DATA) || s.equals(END_MAP_DATA));
	}

	/**
	 * Ecrit les données d'une map
	 *
	 * @param data Données
	 * @throws IOException En cas d'erreur d'écriture
	 */
	public static void writeMapData(MapData data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + data.getMapName() + Config.DATA_EXTENSION)));

		writer.write(HEADER);
		writer.newLine();
		writer.write(START_MAP_DATA);
		writer.newLine();

		DataSave.write(writer, data.getData());

		writer.write(END_MAP_DATA);
		writer.close();
	}

	/**
	 * Ecrit les données d'une partie
	 *
	 * @param data Données
	 * @throws IOException En cas d'erreur d'écriture
	 */
	public static void writeGameData(GameData data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + data.getMapName() + Config.DATA_EXTENSION)));

		writer.write(HEADER);
		writer.newLine();
		writer.write(START_GAME_DATA);
		writer.newLine();

		DataSave.write(writer, data.getData());

		writer.write(END_GAME_DATA);
		writer.close();
	}

	/**
	 * Ecrit des données
	 *
	 * @param writer Ecrivain
	 * @param data   Données
	 * @throws IOException En cas d'erreur d'écriture
	 */
	private static void write(BufferedWriter writer, HashMap<String, String> data) throws IOException {
		for (Map.Entry<String, String> entry : data.entrySet()) {
			writer.write(INDENTATION + DataSave.putInSyntax(entry.getKey(), entry.getValue()));
			writer.newLine();
		}
	}

	/**
	 * Lis les données d'une map
	 *
	 * @param path Chemin
	 * @return Les données
	 * @throws IOException En cas d'erreur de lecture
	 */
	public static MapData readMapData(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

		HashMap<String, String> data = DataSave.read(reader);

		reader.close();
		return new MapData(data);
	}

	/**
	 * Lis les données d'une partie
	 *
	 * @param path Chemin
	 * @return Les données
	 * @throws IOException En cas d'erreur de lecture
	 */
	public static GameData readGameData(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

		HashMap<String, String> data = DataSave.read(reader);

		reader.close();
		return new GameData(data);
	}

	/**
	 * Lis les données d'une map
	 *
	 * @param reader Lecteur
	 * @return Les données
	 * @throws IOException En cas d'erreur de lecture
	 */
	private static HashMap<String, String> read(BufferedReader reader) throws IOException {
		HashMap<String, String> attr = new HashMap<>();
		String read;

		while ((read = reader.readLine()) != null) {
			if (DataSave.isData(read)) {
				String name = DataSave.getNameFromSyntax(read);
				attr.put(name, DataSave.getValueFromSyntax(read, name));
			}
		}

		return attr;
	}
}

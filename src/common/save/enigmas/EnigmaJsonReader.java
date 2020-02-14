package common.save.enigmas;

import api.utils.Utility;
import common.enigmas.Enigma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lit un fichier pour en ressortir des objets instanciés a partir de ce qui à été lu
 *
 * @version 2.0
 */
public class EnigmaJsonReader {

	/**
	 * Syntaxe d'une indentation
	 */
	private final static String INDENTATION = "     ";

	/**
	 * Syntaxe d'un nouveau tableau de "classe"
	 */
	private final static String NEW_CLASS_TAB_SYNTAX = ": [";

	/**
	 * Syntaxe de fin d'un tableau de "classe"
	 */
	private final static String END_CLASS_TAB_SYNTAX = "]";

	/**
	 * Syntaxe d'un nouvel objet
	 */
	private final static String NEW_OBJECT_SYNTAX = "{";

	/**
	 * Syntaxe de fin d'un objet
	 */
	private final static String END_OBJECT_SYNTAX = "}";

	/**
	 * Syntaxe d'un attribut
	 */
	private final static String CLASSIC_ATTRIBUTE_SYNTAX = ": ";

	/**
	 * Index des crochets
	 */
	private final static int BRACKETS = 0;

	/**
	 * Index des accolades
	 */
	private final static int BRACES = 1;

	/**
	 * Extrait le texte avant regex
	 * Efface : tous les guillemets, tous les espaces vides avant le texte
	 *
	 * @param string Texte de base
	 * @param regex  Délimitation
	 * @return Le texte avant regex
	 */
	private static String extractBefore(String string, String regex) {
		string = string.substring(0, string.indexOf(regex));
		string = string.replace("\"", "");
		return string.trim();
	}

	/**
	 * Extrait le texte après regex
	 * Efface : tous les regex, tous les guillemets, toutes les virgules, tous les espaces vides avant le texte
	 *
	 * @param string Texte de base
	 * @param regex  Délimitation
	 * @return Le texte après regex
	 */
	private static String extractAfter(String string, String regex) {
		string = string.substring(string.indexOf(regex));
		string = string.replace(regex, "");
		string = string.replace("\"", "");
		string = string.replace(",", "");
		return string.trim();
	}

	/**
	 * Lit les énigmes d'un fichier
	 *
	 * @param filePath Chemin vers le fichier
	 * @return Liste des énigmes lues est instanciées depuis le fichier
	 * @throws IOException               En cas d'erreur de lecture
	 * @throws ClassNotFoundException    Si le classPath est incorrect
	 * @throws NoSuchMethodException     En cas de problème d'instanciation
	 * @throws IllegalAccessException    En cas de problème d'instanciation
	 * @throws InvocationTargetException En cas de problème d'instanciation
	 * @throws InstantiationException    En cas de problème d'instanciation
	 * @throws IllegalArgumentException  Si le fichier n'est pas un ".json"
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Enigma> readEnigmas(String filePath) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		ArrayList<Enigma> enigmas = (ArrayList) startReading(filePath);

		return (ArrayList<Enigma>) enigmas.clone();
	}

	/**
	 * Commence la lecture du fichier
	 *
	 * @param filePath Chemin vers le fichier
	 * @return Liste des objets lus est instanciés depuis le fichier
	 * @throws IOException               En cas d'erreur de lecture
	 * @throws ClassNotFoundException    Si le classPath est incorrect
	 * @throws NoSuchMethodException     En cas de problème d'instanciation
	 * @throws IllegalAccessException    En cas de problème d'instanciation
	 * @throws InvocationTargetException En cas de problème d'instanciation
	 * @throws InstantiationException    En cas de problème d'instanciation
	 * @throws IllegalArgumentException  Si le fichier n'est pas un ".json"
	 * @throws IllegalStateException     Si le fichier n'est pas dans un format valide
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Object> startReading(String filePath) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
		if (!filePath.endsWith(".json"))
			throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

		ArrayList<Object> objects = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String read;
		int line = 0;
		int[] count = new int[2];
		count[BRACKETS] = 0;
		count[BRACES] = 0;

		while ((read = reader.readLine()) != null) {
			read = read.trim();
			line++;

			if (read.contains(NEW_OBJECT_SYNTAX)) count[BRACES]++;

			if (read.contains(NEW_CLASS_TAB_SYNTAX)) {
				count[BRACKETS]++;

				if (extractBefore(read, NEW_CLASS_TAB_SYNTAX).contains("."))
					objects.add(readObject(reader, extractBefore(read, NEW_CLASS_TAB_SYNTAX), line, count));
				else objects.addAll(readList(reader, line, count));
			}

			if (read.contains(END_OBJECT_SYNTAX)) count[BRACES]--;

			if (read.contains(END_CLASS_TAB_SYNTAX)) count[BRACKETS]--;
		}

		if (count[BRACES] > 0) throw new IllegalStateException("Accolade fermante manquante");
		if (count[BRACES] < 0) throw new IllegalStateException("Accolade ouvrante manquante");
		if (count[BRACKETS] > 0) throw new IllegalStateException("Crochet fermant manquant");
		if (count[BRACKETS] < 0) throw new IllegalStateException("Crochet ovrant manquant");

		return (ArrayList<Object>) objects.clone();
	}

	/**
	 * Lit une liste d'objet dans un fichier
	 *
	 * @param reader Lecteur
	 * @param line   Numéro de ligne actuel
	 * @param count  Nombre d'accolades et crochets ouverts actuellement
	 * @return Liste des objets lus et instanciés
	 * @throws IOException               En cas d'erreur de lecture
	 * @throws ClassNotFoundException    Si le classPath est incorrect
	 * @throws NoSuchMethodException     En cas de problème d'instanciation
	 * @throws IllegalAccessException    En cas de problème d'instanciation
	 * @throws InvocationTargetException En cas de problème d'instanciation
	 * @throws InstantiationException    En cas de problème d'instanciation
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Object> readList(BufferedReader reader, int line, int[] count) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		ArrayList<Object> objects = new ArrayList<>();
		String read;
		int startingClassBracket = count[BRACKETS];
		int startingClassBrace = -1;

		while ((read = reader.readLine()) != null) {
			read = read.trim();
			line++;

			if (read.contains(NEW_OBJECT_SYNTAX)) {
				count[BRACES]++;
				if (startingClassBrace < 0) startingClassBrace = count[BRACES];
			}

			if (read.contains(NEW_CLASS_TAB_SYNTAX)) {
				count[BRACKETS]++;
				objects.add(readObject(reader, extractBefore(read, NEW_CLASS_TAB_SYNTAX), line, count));
			}

			if (read.contains(END_OBJECT_SYNTAX)) {
				if (count[BRACES] == startingClassBrace) {
					if (read.contains(END_OBJECT_SYNTAX + ",")) startingClassBrace = -1;
				}
				count[BRACES]--;
			}

			if (read.contains(END_CLASS_TAB_SYNTAX)) {
				if (count[BRACKETS] == startingClassBracket) {
					count[BRACKETS]--;
					break;
				}
				count[BRACKETS]--;
			}
		}

		return (ArrayList<Object>) objects.clone();
	}

	/**
	 * Lit un objet et l'instancie
	 *
	 * @param reader    Lecteur
	 * @param classPath Nom (chemin) complet de la classe
	 * @param line      Numéro de ligne actuel
	 * @param count     Nombre d'accolades et crochets ouverts actuellement
	 * @return L'objet lu et instancié
	 * @throws IOException               En cas d'erreur de lecture
	 * @throws ClassNotFoundException    Si le classPath est incorrect
	 * @throws NoSuchMethodException     En cas de problème d'instanciation
	 * @throws IllegalAccessException    En cas de problème d'instanciation
	 * @throws InvocationTargetException En cas de problème d'instanciation
	 * @throws InstantiationException    En cas de problème d'instanciation
	 */
	@SuppressWarnings("unchecked")
	public static Object readObject(BufferedReader reader, String classPath, int line, int[] count) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Object object;
		HashMap<String, Object> attributes = new HashMap<>();
		String read;
		int startingClassBracket = count[BRACKETS];

		while ((read = reader.readLine()) != null) {
			read = read.trim();
			line++;

			if (read.contains(NEW_OBJECT_SYNTAX)) count[BRACES]++;

			if (read.contains(NEW_CLASS_TAB_SYNTAX)) {
				count[BRACKETS]++;
				attributes.put(extractBefore(read, NEW_CLASS_TAB_SYNTAX), readList(reader, line, count));

			} else if (read.contains(CLASSIC_ATTRIBUTE_SYNTAX)) {
				attributes.put(extractBefore(read, CLASSIC_ATTRIBUTE_SYNTAX), extractAfter(read, CLASSIC_ATTRIBUTE_SYNTAX));
			}

			if (read.contains(END_OBJECT_SYNTAX)) count[BRACES]--;

			if (read.contains(END_CLASS_TAB_SYNTAX)) {
				if (count[BRACKETS] == startingClassBracket) {
					count[BRACKETS]--;
					break;
				}
				count[BRACKETS]--;
			}
		}

		Class c = Class.forName(classPath);
		Constructor constructor = c.getConstructor(Class.forName("java.util.Map"));
		object = constructor.newInstance(attributes);

		return object;
	}
}
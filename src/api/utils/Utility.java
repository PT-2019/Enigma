package api.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;
import common.utils.Logger;
import data.config.Config;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.*;

/**
 * Tout un paquet de méthodes utiles
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.2
 * @since 2.0 27 novembre 2019
 */
@ConvenienceClass
public class Utility implements Serializable {

	/**
	 * Obtenir le nom de toutes les maps en local
	 * Les noms sont issus des fichiers tmx présents
	 * @return Le nom des maps
	 * @since 6.1
	 */
	//TODO: move to real utility
	public static ArrayList<String> getAllMapName(){
		ArrayList<String> maps = new ArrayList<>();
		File file = new File(Config.MAP_FOLDER);
		String[] list = file.list();

		if(list != null){
			for(String s : list){
				if(s.endsWith(Config.MAP_EXTENSION))
					maps.add(s.replace(Config.MAP_EXTENSION,""));
			}
		}

		Collections.sort(maps);
		return maps;
	}

	/**
	 * Obtenir le nom de toutes les parties en local
	 * Les noms sont issus des fichiers tmx présents
	 * @return Le nom des parties
	 */
	public static ArrayList<String> getAllGameName() {
		ArrayList<String> maps = new ArrayList<>();
		File file = new File(Config.GAME_DATA_FOLDER);
		String[] list = file.list();

		if(list != null){
			for(String s : list){
				if(s.endsWith(Config.DATA_EXTENSION))
					maps.add(s.replace(Config.DATA_EXTENSION,""));
			}
		}

		Collections.sort(maps);
		return maps;
	}

	/**
	 * Obtenir le nom de toutes les fichiers dans un dossier
	 * Les noms sont issus des fichiers tmx présents
	 * @return Le nom des maps
	 * @since 6.1
	 */
	public static ArrayList<String> getAllFiles(String folder, String[] extensions, boolean removeExtension){
		ArrayList<String> files = new ArrayList<>();
		File file = new File(folder);
		String[] list = file.list();

		if(list != null){
			for(String s : list){
				for (String ext :extensions) {
					if(s.endsWith(ext)){
						if(removeExtension){
							s = s.replace(ext, "");
						}
						files.add(s);
						break;
					}
				}

			}
		}
		return files;
	}

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
	 * Cette méthode normalize une chaîne de caractère : supprime les caractères spéciaux et les nombres
	 *
	 * @param string la chaîne a normaliser
	 * @return la chaîne normalisée
	 * @since 2.0
	 */
	@ConvenienceMethod
	public static String normalize(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\x00-\\x7F]", "");
		string = string.replaceAll("[-+.^:,\"']","");
		string = string.replaceAll("[\\n\\t ]", "");
		return string.trim();
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
	public static <T extends Enum> T stringToEnum(String name, T[] enumValues) {
		name = normalize(name).toLowerCase();

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
	 * Affiche une Map
	 *
	 * @param hashMap la hashMap
	 * @since 2.0
	 */
	@ConvenienceMethod
	public static void printHashMap(Map<?, ?> hashMap) {
		for (Map.Entry<?, ?> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + "->");
			Object value = entry.getValue();
			if (value instanceof ArrayList) {
				System.out.println(Arrays.toString(((ArrayList) value).toArray()));
			} else if (value.getClass().isArray()) {
				System.out.println(Arrays.toString((Object[]) value));
			} else {
				System.out.println(value);
			}
		}
	}

	/**
	 * Appelle une méthode
	 * @param m object méthode
	 * @param className nom de la classe
	 * @param <T> type de retour
	 * @return appel une méthode et retourne son résultat
	 */
	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Method m, Class<T> className, Object ... args) {
		try {
			return (T) m.invoke(className, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("Invocation ratée."+e);
		}
	}

	/**
	 * Appel une méthode depuis son nom en string
	 * @param methodName nom d'une méthode
	 * @param object    class de la méthode
	 * @param args arguments
	 * @param <T> type du type de retour
	 * @return type de retour
	 * @since 4.1
	 */
	public static <T> T invokeMethod(String methodName, Class<T> object, Object ... args) {
		methodName = normalize(methodName);

		for (Method method : object.getDeclaredMethods()) {
			String name = normalize(method.getName());
			if (methodName.equals(name)) {
				return invokeMethod(method, object);
			}
		}

		throw new IllegalStateException("No such method");
	}

	/**
	 * Retourne une instance d'une classe
	 *
	 * @param aClass une classe avec un constructeur par default
	 * @return une instance de la classe
	 * @throws IllegalStateException si une erreur survient
	 * @since 4.2
	 */
	@ConvenienceMethod
	public static Object instance(Class<?> aClass) {
		Object object;
		try {
			Constructor declaredConstructor = aClass.getDeclaredConstructor();
			object = declaredConstructor.newInstance();
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException
				| InvocationTargetException e) {
			throw new IllegalStateException("create instance failed" + e);
		}
		return object;
	}

	/**
	 * Retourne une instance d'une classe
	 *
	 * @param aClass une classe avec un constructeur ayant 1 argument
	 * @param value  valeur de l'unique argument
	 * @return une instance de la classe
	 * @throws IllegalStateException si une erreur survient
	 * @since 4.2
	 */
	@ConvenienceMethod
	public static Object instance(Class<?> aClass, Object value) {
		Object object;
		try {
			Constructor declaredConstructor = aClass.getDeclaredConstructor(value.getClass());
			object = declaredConstructor.newInstance(value);
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException
				| InvocationTargetException e) {
			throw new IllegalStateException("Utility. create instance failed" + e);
		}
		return object;
	}

	/**
	 * Retourne une instance d'une classe
	 * @param aClass une classe avec un constructeur ayant 1 argument
	 * @param args  les arguments du constructeur
	 * @return une instance de la classe
	 *
	 * @throws IllegalStateException si une erreur survient
	 * @since 6.0
	 */
	@ConvenienceMethod
	public static Object instance(Class<?> aClass, Object ... args) {
		Object object;
		try {
			//récupération des classes
			if(args != null && args.length > 0) {
				//récupération des classes
				Class<?>[] classes = new Class[args.length];
				for (int i = 0; i < args.length; i++) {
					classes[i] = args[i].getClass();
				}

				Constructor<?> selected = null;

				//Regarde tous les constructeurs
				boolean valid;
				for (Constructor<?> constructor : aClass.getDeclaredConstructors()) {
					Class<?>[] params = constructor.getParameterTypes();
					//même nombre de paramètres
					if(params.length != classes.length) continue;
					valid = false;
					for (int i = 0; i < params.length; i++) {
						Class<?> c = classes[i];
						while(c != null){
							if(c.getName().equals(params[i].getName())) break;
							c = c.getSuperclass();
						}
						//un paramètre est pas bon
						if(c == null) break;
						valid = true;
					}
					//si bon, on quitte
					if(valid){
						selected = constructor;
						break;
					}
				}
				if(selected == null) throw  new NoSuchMethodException("<init>: No such constructor.");

				//crée instance
				object = selected.newInstance(args);
			} else {
				Constructor declaredConstructor = aClass.getDeclaredConstructor();
				object = declaredConstructor.newInstance();
			}
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException
				| InvocationTargetException e) {
			throw new IllegalStateException("Utility. create instance failed" + e);
		}
		return object;
	}

	/**
	 * Retourne une instance d'une classe
	 *
	 * @param aClass nom d'une classe avec un constructeur par default
	 * @return une instance de la classe
	 * @throws IllegalStateException si une erreur survient
	 * @since 4.2
	 */
	@ConvenienceMethod
	public static Object instance(String aClass) {
		try {
			return instance(Class.forName(aClass));
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Utility. Create instance failed" + e);
		}
	}

	/**
	 * Renvoi le chemin pour arriver a la racine depuis le bout.
	 * (remplace ../ autant que la profondeur)
	 *
	 * @param folder chemin
	 * @return le chemin pour arriver a la racine depuis le bout.
	 * @since 5.0
	 */
	@ConvenienceMethod
	@Deprecated
	public static String getRelativePath(String folder) {
		StringBuilder path = new StringBuilder();

		for (int i = 0; i < folder.length(); i++) {
			if (folder.charAt(i) == '/') {
				path.append("../");
			}
		}

		return path.toString();
	}

	/**
	 * Retourne la configuration de l'écran dans lequel est la fenêtre
	 *
	 * @param window fenêtre
	 * @return configuration de l'écran dans lequel est la fenêtre
	 * @throws IllegalArgumentException si moniteur pas trouvé
	 * @since 5.1
	 */
	public static GraphicsConfiguration getMonitorOf(Window window) {
		GraphicsDevice monitor = window.getGraphicsConfiguration().getDevice();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] monitors = env.getScreenDevices();

		//regarde si le moniteur fenêtre est l'object 'x' des moniteurs disponibles
		for (GraphicsDevice aMonitor : monitors) {
			if (aMonitor.equals(monitor)) {
				return aMonitor.getDefaultConfiguration();
			}
		}

		throw new IllegalArgumentException("monitor non trouvé!");
	}

	/**
	 * Renvoi la clef d'une valeur d'une HashMap
	 *
	 * @param map   une map
	 * @param value valeur
	 * @return la clef correspond a la valeur
	 * @since 5.2
	 */
	public static Object getKeyFromValue(Map<?, ?> map, Object value) {
		for (Map.Entry<?, ?> mapEntry : map.entrySet()) {
			if (mapEntry.getValue().equals(value))
				return mapEntry.getKey();
		}
		throw new IllegalArgumentException("There is not such value in the map.(" + value + ")");
	}

	/**
	 * Affichage de message concernant le débuggage
	 *
	 * @param className nom de la classe
	 * @param message   message
	 * @since 5.2
	 *
	 * @deprecated utiliser {@link Logger}
	 */
	@Deprecated
	public static void printDebug(String className, String message) {
		throw new UnsupportedOperationException("disabled");
	}

	/**
	 * Transforme un mot en SNAKE_CASE en CamelCase.
	 *
	 * @param name mot
	 *
	 * @return le mot en CamelCase
	 *
	 * @since 6.0
	 */
	@ConvenienceMethod
	public static String snakeCaseToCamelCase(String name) {
		if (name.contains("_")) {
			StringBuilder sb = new StringBuilder();
			boolean up = false; //si suivante est une majuscule
			for (int i = 0; i < name.length(); i++) {
				String c = (name.charAt(i) + "").toLowerCase(); //minuscule
				if (c.equals("_")) {
					up = true; //préviens de le prochain est une majuscule
				} else {
					if (up) {
						sb.append((c + "").toUpperCase());
						up = false;
					} else {
						sb.append(c);//ajoute à la chaîne
					}
				}
			}
			return sb.toString();
		}
		return name.toLowerCase();
	}
}


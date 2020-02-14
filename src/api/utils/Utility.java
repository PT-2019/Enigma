package api.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.utils.Array;

import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Tout un paquet de méthodes utiles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.7
 * @since 2.0 27 novembre 2019
 */
@ConvenienceClass
@SuppressWarnings("unused")
public class Utility implements Serializable {

	/**
	 * Obtenir le nom de toutes les fichiers dans un dossier
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @param folder          dossier
	 * @param extensions      extensions voulues
	 * @param removeExtension true pour supprimer les extensions
	 * @return Le nom des maps
	 * @since 6.3
	 */
	public static ArrayList<String> getAllFiles(String folder, String[] extensions, boolean removeExtension) {
		ArrayList<String> files = new ArrayList<>();
		File file = new File(folder);
		String[] list = file.list();

		if (list != null) {
			for (String s : list) {
				for (String ext : extensions) {
					if (s.endsWith(ext)) {
						if (removeExtension) {
							s = s.replace(ext, "");
						}
						files.add(s);
						break;
					}
				}

			}
		}

		Collections.sort(files);

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
		string = string.replaceAll("[-+.^:,\"']", "");
		string = string.replaceAll("[\\n\\t ]", "");
		return string.trim();
	}

	/**
	 * Cette méthode échappe une chaîne de caractère : supprime les caractères spéciaux et les nombres
	 *
	 * @param string une chaîne
	 * @return la chaîne échappée
	 * @since 6.3
	 */
	public static String escape(String string) {
		string = normalize(string);
		string = string.replaceAll("[^\\p{L}\\p{Z}]", "");
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
			System.out.print(entry.getKey() + "-> ");
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
	 *
	 * @param m         object méthode
	 * @param className nom de la classe
	 * @param <T>       type de retour
	 * @return appel une méthode et retourne son résultat
	 */
	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Method m, Class<T> className, Object... args) {
		try {
			return (T) m.invoke(className, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("Invocation ratée." + e);
		}
	}

	/**
	 * Appel une méthode depuis son nom en string
	 *
	 * @param methodName nom d'une méthode
	 * @param object     class de la méthode
	 * @param args       arguments
	 * @param <T>        type du type de retour
	 * @return type de retour
	 * @since 4.1
	 */
	public static <T> T invokeMethod(String methodName, Class<T> object, Object... args) {
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
		Constructor declaredConstructor = null;
		try {
			declaredConstructor = aClass.getDeclaredConstructor();
			object = declaredConstructor.newInstance();
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException
				| InvocationTargetException e) {
			String message = "Create instance failed. Error \"" + e.getCause()
					+ "\" in " + declaredConstructor;
			if (declaredConstructor == null) message = "Constructor not found." + e;
			throw new IllegalStateException(message);
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
		Constructor declaredConstructor = null;
		try {
			try {
				declaredConstructor = aClass.getDeclaredConstructor(value.getClass());
			} catch (NoSuchMethodException | SecurityException e) {
				return instance(aClass, value, null);
			}
			object = declaredConstructor.newInstance(value);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			String message = "Create instance failed. Error \"" + e.getCause()
					+ "\" in " + declaredConstructor;
			throw new IllegalStateException(message);
		}

		return object;
	}

	/**
	 * Retourne une instance d'une classe
	 *
	 * @param aClass une classe avec un constructeur ayant 1 argument
	 * @param args   les arguments du constructeur, null pour terminer si besoin
	 * @return une instance de la classe
	 * @throws IllegalStateException si une erreur survient
	 * @since 6.0
	 */
	@ConvenienceMethod
	public static Object instance(Class<?> aClass, Object... args) {
		Object object;
		Constructor<?> selected = null;
		try {
			//récupération des classes
			if (args != null && args.length > 0) {
				//récupération des classes
				Array<Class<?>> classes = new Array<>();
				for (Object arg : args) {
					if (arg != null) classes.add(arg.getClass());
				}

				//Regarde tous les constructeurs
				boolean valid;
				for (Constructor<?> constructor : aClass.getDeclaredConstructors()) {
					Class<?>[] params = constructor.getParameterTypes();
					//même nombre de paramètres
					if (params.length != classes.size) continue;
					valid = false;
					for (int i = 0; i < params.length; i++) {
						Class<?> c = classes.get(i);
						while (c != null) {
							if (c.getName().equals(params[i].getName())) break;
							c = c.getSuperclass();
						}
						//un paramètre est pas bon
						if (c == null) break;
						valid = true;
					}
					//si bon, on quitte
					if (valid) {
						selected = constructor;
						break;
					}
				}
				if (selected == null) throw new NoSuchMethodException("<init>: No such constructor.");

				//crée instance
				object = selected.newInstance(args);
			} else {
				Constructor declaredConstructor = aClass.getDeclaredConstructor();
				object = declaredConstructor.newInstance();
			}
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException
				| InvocationTargetException e) {
			String message = "Create instance failed. Error \"" + e.getCause()
					+ "\" in " + selected;
			throw new IllegalStateException(message);
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
	 * Affichage de message concernant le débogage
	 *
	 * @param className nom de la classe
	 * @param message   message
	 * @since 5.2
	 * @deprecated
	 */
	@Deprecated
	public static void printDebug(String className, String message) {
		throw new UnsupportedOperationException("disabled");
	}

	/**
	 * Transforme un mot en SNAKE_CASE en CamelCase.
	 *
	 * @param name mot
	 * @return le mot en CamelCase
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

	/**
	 * Retourne true si l'object o extends ou implémente une classe
	 *
	 * @param aClass une classe
	 * @param o      un object
	 * @param start  point de départ de la recherche
	 * @return true si l'object o extends ou implémente une classe
	 * @since 6.4
	 */
	private static boolean hasClass(Class<?> aClass, Object o, Class<?>[] start) {
		if (start == null || start.length == 0) return false;

		for (Class<?> value : start) {
			//rien
			if (value == null || value == Object.class) return false;

			//trouvé
			if (value.getName().equals(aClass.getName())) return true;

			//sinon on regarde sa superclass
			if (hasClass(aClass, o, new Class[]{value.getSuperclass()})) return true;

			//sinon on regarde ses interfaces
			if (hasClass(aClass, o, value.getInterfaces())) return true;
		}

		return false;
	}

	/**
	 * Retourne true si l'object o extends ou implémente une classe
	 *
	 * @param aClass une classe
	 * @param o      un object
	 * @return true si l'object o extends ou implémente une classe
	 * @since 6.4
	 */
	public static boolean hasClass(Class<?> aClass, Object o) {
		return hasClass(aClass, o, new Class[]{o.getClass()});
	}

	/**
	 * Renvoi la position pour aligner le composant au centre
	 *
	 * @param frame un composant
	 * @return la position pour aligner le composant au centre
	 * @since 6.5
	 */
	public static Point getAlignCenterPosition(Component frame) {
		Rectangle screenSize = frame.getGraphicsConfiguration().getBounds();
		int sizeW = screenSize.width;
		int sizeH = screenSize.height;
		int windowPosX;
		int windowPosY;
		windowPosX = (sizeW - frame.getWidth()) / 2;
		windowPosY = (sizeH - frame.getHeight()) / 2;
		return new Point(windowPosX + screenSize.x, windowPosY + screenSize.y);
	}

	/**
	 * Retourne la version non échappée d'une string. tel que la string suivante.
	 * "ZSdz ef e&#10;&#10;zse&#10;ev&#10;&#10;&#10;ez&#10;&#10;ze"
	 *
	 * @param asciiEscapedString un string contenant des caractères ascii échappés
	 * @return un string sans caractères échappés
	 * @since 6.6
	 */
	public static String asciiEscapedToNormalString(String asciiEscapedString) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < asciiEscapedString.length(); i++) {
			char read = asciiEscapedString.charAt(i);
			if (read != '&') {
				sb.append(read);
				continue;
			}
			i++;//zap le &
			read = asciiEscapedString.charAt(i);
			if (asciiEscapedString.charAt(i) != '#') {
				sb.append('&');
				sb.append(read);
				continue;
			}
			i++;//zap le #
			//lis la valeur ascii
			StringBuilder tmp = new StringBuilder();
			char c = asciiEscapedString.charAt(i);
			while (c != ';') {
				i++;
				tmp.append(c);
				c = asciiEscapedString.charAt(i);
			}
			//re-transforme en char
			sb.append((char) Integer.parseInt(tmp.toString()));
		}
		return sb.toString();
	}

	/**
	 * Retourne true si une string est différente de null, chaîne vide, est chaîne avec
	 * caractères blancs uniquement
	 *
	 * @param string une string
	 * @return true si une string est différente de null, chaîne vide, est chaîne avec
	 * caractères blancs uniquement
	 * @since 6.7
	 */
	public static boolean isStringValid(String string) {
		return string != null && !string.isEmpty() && !string.isBlank();
	}
}

package api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Lanceur de jeux vidéos
 *
 * @version 3.0 03 december 2019
 * @see Game
 */
public abstract class LibgdxGame extends Game implements Application {

	/**
	 * Static and singleton instance du jeu
	 **/
	private static LibgdxGame game;

	/**
	 * Conserve tous les écran qui ont étés instanciés et donc déjà chargés.
	 *
	 * Il seront libérés.
	 *
	 * On les charges avec la méthode {@link #loadScreen(String, Class)} ou
	 * {@link #load(String)} si le screen a été ajouté.
	 */
	private static HashMap<String, LibgdxScreen> screens = new HashMap<>();

	/**
	 * On peut vouloir charger un écran mais pas l'instancier directement.
	 * Cela ce fait avec la méthode add et il sont conservés ici.
	 *
	 * @see #addScreen(String, Class)
	 */
	private static HashMap<String, Class<? extends LibgdxScreen>> added = new HashMap<>();

	/**
	 * Conserve la taille de l'écran si on veut revenir de plein écran a l'ancienne
	 * taille.
	 * Doit être mis a jour par l'utilisateur. {@link #setScreenSize(int, int)}
	 * dans {@link LibgdxScreen#resize(int, int)}.
	 *
	 * @see #setScreenSize(int, int)
	 * @see #getScreenSize()
	 */
	private Rectangle screenSize;

	/**
	 * Permet la création de singletons
	 */
	protected LibgdxGame() {
	}

	/**
	 * Retourne un écran.
	 * S'il n'a pas été chargé (passé via {@link #addScreen(String, Class)}), alors il est instancié.
	 *
	 * @param key nom du screen {@link #setScreen(String)}
	 * @return l'instance de l'écran
	 *
	 * @throws IllegalStateException si une exception est levée dans le contructeur ou lors de son instanciation
	 */
	public static LibgdxScreen load(String key) {
		if (!screens.containsKey(key)) {
			if (!added.containsKey(key)) {
				throw new IllegalStateException("add screen first before load it");
			} else {
				try {
					Constructor declaredConstructor = added.get(key).getDeclaredConstructor();
					screens.put(key, (LibgdxScreen) declaredConstructor.newInstance());
				} catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
					Gdx.app.error("FilesManager", "create instance failed");
					throw new IllegalStateException("create instance failed");
				}
			}
		}

		return screens.get(key);
	}

	public static <T extends LibgdxScreen> LibgdxScreen loadScreen(String key, Class<T> screen) {
		addScreen(key, screen);
		return load(key);
	}

	public static <T extends LibgdxScreen> void addScreen(String key, Class<T> screen) {
		added.put(key, screen);
	}

	/**
	 * Convenience method to find if a screen has been loaded or not
	 *
	 * @param key id of the screen {@link #setScreen(String)}
	 * @return true if the screen exit
	 */
	public static boolean hasScreen(String key) {
		return screens.containsKey(key);
	}

	/**
	 * Set the current screen
	 *
	 * @param key id of the screen
	 * @throws IllegalArgumentException if key is invalid
	 */
	public static void setScreen(String key) {
		if (!screens.containsKey(key)) {
			load(key);
		}
		game.setScreen(screens.get(key));
	}

	/**
	 * Return the current screen
	 *
	 * @return the current screen
	 */
	public static LibgdxScreen getCurrentScreen() {
		return (LibgdxScreen) game.getScreen();
	}

	/**
	 * Return screen size
	 *
	 * @return a rectangle with should contains screen size
	 * @see #setScreenSize(int, int)
	 */
	public static Rectangle getScreenSize() {
		return game.screenSize;
	}

	/**
	 * Save screen size in case we want to go back from fullScreen to windowed for instance
	 * Should be updated by the user
	 *
	 * @param width  width of the screen
	 * @param height height of the screen
	 */
	public static void setScreenSize(int width, int height) {
		game.screenSize.setSize(width, height);
	}

	/**
	 * Method fired when game start. Hidden by using {@link #start()}
	 * Load a invent processor and fire start.
	 */
	@Override
	public void create() {
		game = this;
		screens = new HashMap<>();
		screenSize = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Gdx.gl20.glClearColor(0.20f,0.20f,0.20f,1.0f); //my grey <3
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.input.setInputProcessor(new InputMultiplexer());//ajout multi gestionnaire d'événements
		this.start();
	}

	/**
	 * Method fired when game start. Should contains initialisation code
	 * such as Initialisations of screens and setting current screen
	 */
	public abstract void start();

	/**
	 * Liberates all the screens
	 * Automatically called
	 */
	@Override
	public void dispose() {
		super.dispose();
		for (LibgdxScreen s : screens.values()) {
			if (s != null) {
				s.dispose();
			}
		}
	}
}

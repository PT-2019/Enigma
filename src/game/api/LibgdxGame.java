package game.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Rectangle;
import editor.api.Application;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Video games luncher.
 *
 * @version 3.0 03 december 2019
 * @see Game
 */
public abstract class LibgdxGame extends Game implements Application {

	/**
	 * Static and singleton instance of the game
	 **/
	private static LibgdxGame game;

	/**
	 * All game screens
	 * can be loaded using {@link #addScreen(String, Class)}
	 */
	private static HashMap<String, LibgdxScreen> screens = new HashMap<>();

	private static HashMap<String, Class<? extends LibgdxScreen>> added = new HashMap<>();

	/**
	 * Save screen size in case we want to go back from fullScreen
	 * to windowed
	 * Should be updated by the user {@link #setScreenSize(int, int)}
	 *
	 * @see #setScreenSize(int, int)
	 * @see #getScreenSize()
	 */
	private Rectangle screenSize;

	/**
	 * Just to make us able to create a singleton instance
	 */
	protected LibgdxGame() {
	}

	/**
	 * Load a screen a return it.
	 *
	 * @param key id (name) of the screen {@link #setScreen(String)}
	 * @return the screen
	 * <p>
	 * WARNING !!!
	 * <p>
	 * if there is an exception here, then you should check if there isn't an exception in your
	 * constructor (you won't see if there is one here)
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

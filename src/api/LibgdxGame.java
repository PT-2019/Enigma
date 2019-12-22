package api;

import api.utils.ConvenienceMethod;
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
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @see Game
 * @since 3.0 03 december 2019
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
	 * @throws IllegalStateException si une exception est levée dans le contructeur ou lors de son instanciation
	 * @since 3.0 03 december 2019
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

	/**
	 * Instancie un écran et le retourne.
	 *
	 * @param key    indice a associer à l'écran
	 * @param screen la version .class d'un écran
	 * @param <T>    une class qui extends LibgdxScreen
	 * @return l'écran instancié
	 * @see #addScreen(String, Class)
	 * @see #load(String)
	 * @since 4.0 20/12/2019
	 */
	@ConvenienceMethod
	public static <T extends LibgdxScreen> LibgdxScreen loadScreen(String key, Class<T> screen) {
		addScreen(key, screen);
		return load(key);
	}

	/**
	 * Conserve un écran pour sa future instanciation
	 *
	 * @param key    indice a associer à l'écran
	 * @param screen la version .class d'un écran
	 * @param <T>    une class qui extends LibgdxScreen
	 * @since 3.0 03 december 2019
	 */
	public static <T extends LibgdxScreen> void addScreen(String key, Class<T> screen) {
		added.put(key, screen);
	}

	/**
	 * Retourne si un screen a été chargé
	 *
	 * @param key indice a associer à l'écran {@link #setScreen(String)}
	 * @return true  si un screen a été chargé
	 * @since 3.0 03 december 2019
	 */
	@ConvenienceMethod
	public static boolean hasScreen(String key) {
		return screens.containsKey(key);
	}

	/**
	 * Défini cet écran comme l'écran actuel
	 *
	 * @param key indice a associer à l'écran
	 * @throws IllegalArgumentException si la clef est invalide
	 */
	public static void setScreen(String key) {
		if (!screens.containsKey(key)) {
			load(key);//throws IllegalArgumentException
		}
		game.setScreen(screens.get(key));
	}

	/**
	 * Return l'écran actuel
	 *
	 * @return l'écran actuel
	 */
	public static LibgdxScreen getCurrentScreen() {
		return (LibgdxScreen) game.getScreen();
	}

	/**
	 * Défini cet écran comme l'écran actuel
	 *
	 * @param screen l'écran a charger
	 */
	public static void setCurrentScreen(LibgdxScreen screen) {
		game.setScreen(screen);
	}

	/**
	 * Retourne la taille de l'écran si on veut revenir de plein écran a l'ancienne
	 * taille.
	 * Doit être mis a jour par l'utilisateur. {@link #setScreenSize(int, int)}
	 * dans {@link LibgdxScreen#resize(int, int)}.
	 *
	 * @return un rectangle qui contient  la taille de l'écran si on veut revenir de plein écran a l'ancienne
	 * taille.
	 * @see #setScreenSize(int, int)
	 */
	public static Rectangle getScreenSize() {
		return game.screenSize;
	}

	/**
	 * Chnage la taille de l'écran si on veut revenir de plein écran a l'ancienne
	 * taille.
	 * Doit être mis a jour par l'utilisateur.
	 *
	 * @param width  largeur de l'écran
	 * @param height hauteur de l'écran
	 * @see #getScreenSize()
	 */
	public static void setScreenSize(int width, int height) {
		game.screenSize.setSize(width, height);
	}

	/**
	 * Charge le jeu, appelée automatique par la libgdx
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
	 * Cette méthode est appelée au lancement du jeu.
	 * Le processus de gestion des évènements a déjà été créé ({@link InputMultiplexer}
	 *
	 * Ici les ressources du jeu sont crées. Les écrans sont ajoutés et chargés si voulu.
	 *
	 * On doit définir l'écran courrant
	 *
	 * @see #addScreen(String, Class)
	 * @see #load(String)
	 * @see #setScreen(String)
	 */
	public abstract void start();

	/**
	 * Libère tous les écran. Appelé automatiquement.
	 */
	@Override
	public void dispose() {
		super.dispose();
		for (LibgdxScreen s : screens.values()) {
			if (s != null) {
				s.dispose();
			}
		}
		free();
	}

	/**
	 * Ici les ressources initialisés dans start doivent être
	 * libérés.
	 *
	 * Les écrans sont déjà libérés.
	 */
	public abstract void free();
}

package game.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public abstract class LibgdxGame extends Game {

	/**
	 * Garde instance static unique du jeu
	 **/
	private static LibgdxGame game;

	private static ArrayList<Screen> screens;

	/**
	 * Change l'écran actuellement affiché par le gameEngine
	 *
	 * @param screen l'écran a afficher
	 */
	public static void setCurrentScreen(@NotNull Screen screen) {
		if (!screens.contains(screen))
			screens.add(screen);
		game.setScreen(Objects.requireNonNull(screen));
	}

	public static Screen getCurrentScreen(){
		return game.getScreen();
	}

	//TODO: make us able to get a screen from a name
	public static Screen getScreen(String name){
		throw new UnsupportedOperationException("non codé");
	}

	/**
	 * Cette méthode doit contenir le code a exécuter au lancement
	 * du jeu par le game engine.
	 *
	 * On peut par exemple, charger l'écran du menu
	 *
	 * @see LibgdxGame#setCurrentScreen(Screen)
	 */
	public abstract void start();

	@Override
	public void create() {
		game = this;
		screens = new ArrayList<>();
		Gdx.input.setInputProcessor(new InputMultiplexer());//ajout multi gestionnaire d'événements
		this.start();
	}

	@Override
	public void dispose() {
		super.dispose();
		for (Screen s : screens) {
			if (s != null) {
				s.dispose();
			}
		}
	}
}

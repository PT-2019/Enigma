package api.libgdx;

import api.IGameLogic;
import api.libgdx.utils.InputAdapter;
import api.utils.annotations.DoNothing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import common.map.AbstractMap;

import java.util.Iterator;

/**
 * Il s'agit d'un écran du jeu mais qui remplit toutes les fonctionnalités
 * d'un jeu entier : phase initialisation et libération et la gameloop.
 * <p>
 * Les méthodes de la libgdx sont cachés au mieux.
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @see #listen(InputProcessor)
 * @see InputAdapter
 * @since 3.0
 */
public abstract class LibgdxScreen implements Screen, InputAdapter, IGameLogic {

	/**
	 * Liste des processus en écoute
	 */
	private Array<InputProcessor> listened;

	public LibgdxScreen() {
		this.listened = new Array<>();
		this.init();
	}

	/**
	 * Cache l'écran mais les évènements doivent continuer d'être capturés
	 * <p>
	 * setVisible ou addAction(Actions.hide()) et addAction(Actions.show())
	 * devrait suffire sur les stages
	 *
	 * @param display true pour cacher l'écran sinon false
	 * @since 3.0 03 december 2019
	 */
	@DoNothing
	public void display(boolean display) {
	}

	/* listen méthods */

	/**
	 * When a event happens, if no one before was concerned
	 * by the event, all stage's observers will be called.
	 * <p>
	 * Warning! The events are called in the same order listens
	 * call were done.
	 * <p>
	 * So it would be wise to call listen on hud's stage before main's stage
	 * for instance.
	 *
	 * @param stage the stage witch should be adverted of events
	 * @since 3.0 03 december 2019
	 */
	protected void listen(InputProcessor stage) {
		this.listened.add(stage);
	}

	/**
	 * Return all listened stages.
	 * They could be removed.
	 *
	 * @return all listened stages.
	 * @since 3.0 03 december 2019
	 */
	public Iterator<InputProcessor> getListened() {
		return new Array.ArrayIterator<>(this.listened);
	}

	/* Screen methods are hidden, to make gameLogic (or game loop) more visible */

	@Override
	public void show() {
		InputMultiplexer gestionnaireProcessus = (InputMultiplexer) Gdx.input.getInputProcessor();
		gestionnaireProcessus.addProcessor(this);
		for (InputProcessor processor : new Array.ArrayIterator<>(this.listened)) {
			gestionnaireProcessus.addProcessor(processor);
		}
	}

	@Override
	public void render(float delta) {
		//screen clear
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//fake a gameLoop
		this.input();
		this.update(delta);
		this.render();
	}

	@Override
	public void hide() {
		InputMultiplexer gestionnaireProcessus = (InputMultiplexer) Gdx.input.getInputProcessor();
		gestionnaireProcessus.removeProcessor(this);
		for (InputProcessor processor : new Array.ArrayIterator<>(this.listened)) {
			gestionnaireProcessus.removeProcessor(processor);
		}
	}

	//don't care

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	/**
	 * Affiche un toast
	 * @param message message
	 * @since 6.0
	 */
	public void showToast(String message){}

	/**
	 * Retourne la map dans l'écran
	 * @return la map de l'écran
	 * @since 4.0
	 */
	public abstract AbstractMap getMap();
}
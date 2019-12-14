package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import game.utils.InputListener;

import java.util.Iterator;

/**
 * A typical game should have a init, game loop and dispose.
 * Game Loop means input, update, render.
 * <p>
 * Most Libgdx methods are hidden.
 * <p>
 * A screen could listener it's actors (don't forget {@link #listen(com.badlogic.gdx.InputProcessor)}
 * <p>
 * It could handle event {@link InputListener}
 *
 * @version 3.0 03 december 2019
 * @since 03 december 2019
 */
public abstract class LibgdxScreen implements Screen, InputListener, IGameLogic {

	private Array<InputProcessor> listened;

	public LibgdxScreen() {
		this.listened = new Array<>();
		this.init();
	}

	/**
	 * Initialisations, allocations...
	 */
	public abstract void init();

	/**
	 * poll user input
	 */
	public abstract void input();

	/**
	 * Update of the game
	 *
	 * @param dt elapsed time since last call
	 */
	public abstract void update(float dt);

	/**
	 * render (print) everything on the screen
	 */
	public abstract void render();

	/**
	 * Called each time the screen is resized
	 *
	 * @param width  screen width
	 * @param height screen height
	 */
	@Override
	public abstract void resize(int width, int height);

	/**
	 * Should hide or display the screen but event keep running
	 * <p>
	 * a setVisible or addAction(Actions.hide()) and addAction(Actions.show())
	 * should be enough.
	 *
	 * @param display true for displaying else false
	 */
	public abstract void display(boolean display);

	/**
	 * Close and frees
	 */
	@Override
	public abstract void dispose();

	/* Screen methods are hidden, to make gameLogic (or game loop) more visible */

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
	 */
	protected void listen(InputProcessor stage) {
		this.listened.add(stage);
	}

	/**
	 * Return all listened stages.
	 * They could be removed.
	 *
	 * @return all listened stages.
	 */
	public Iterator<InputProcessor> getListened() {
		return new Array.ArrayIterator<>(this.listened);
	}

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
}
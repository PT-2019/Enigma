package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class LibgdxScreen implements Screen, InputProcessor {

	private ArrayList<InputProcessor> listened;

	public LibgdxScreen() {
		this.listened = new ArrayList<>();
		this.init();
	}

	/**
	 * Initialisations, allocations, cr&#233;ations
	 */
	public abstract void init();

	/**
	 * R&#233;cup&#233;ration des interactions avec l'utilisateur
	 */
	public abstract void input();

	/**
	 * Mise-&#224;-jour des donn&#233;es
	 *
	 * @param dt le temps écoulé depuis le dernier appel
	 */
	public abstract void update(float dt);

	/**
	 * Called each time the screen is resized
	 *
	 * @param width  screen width
	 * @param height screen height
	 */
	@Override
	public abstract void resize(int width, int height);

	/**
	 * Mise-&#224;-jour de l'affichage
	 */
	public abstract void render();

	/**
	 * Fermetures et lib&#233;rations
	 */
	@Override
	public abstract void dispose();

	/* Screen methods are hidden, to make gameLogic (or game loop) more visible */

	public Iterator<Stage> getStages(){return  new ArrayList<Stage>().iterator(); }

	/**
	 * When a event happens, if no one before was concerned
	 * by the event, all stage's observers will be called.
	 *
	 * Warning! The events are called in the same order listens
	 * call were done.
	 *
	 * So it would be wise to call listen on hud's stage before main's stage
	 * for instance.
	 *
	 * @param stage the stage witch should be adverted of events
	 */
	protected void listen(InputProcessor stage) {
		this.listened.add(stage);
	}

	@SuppressWarnings("unchecked")
	public Iterator<InputProcessor> getListened(){
		return ((ArrayList<InputProcessor>) listened.clone()).iterator();
	}

	@Override
	public void show() {
		InputMultiplexer gestionnaireProcessus = (InputMultiplexer) Gdx.input.getInputProcessor();
		gestionnaireProcessus.addProcessor(this);
		for (InputProcessor processor : this.listened) {
			gestionnaireProcessus.addProcessor(processor);
		}
	}

	@Override
	public void render(float delta) {
		//screen clear
		//Gdx.gl20.glClearColor(0.20f,0.20f,0.20f,1.0f);
		Gdx.gl20.glClearColor(0, 0, 0, 1);
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
		for (InputProcessor processor : this.listened) {
			gestionnaireProcessus.removeProcessor(processor);
		}
	}

	// add a way for discontinuous input

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	//don't care

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
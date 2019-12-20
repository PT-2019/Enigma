package game.utils;

import com.badlogic.gdx.InputProcessor;
import editor.utils.ConvenienceClass;

/**
 * Convenience interface for input (to implements InputProcessor)
 * Contains methods to process keyboard, mouse, and scroll input.
 */
@ConvenienceClass
public interface InputListener extends InputProcessor {

	/**
	 * Key pressed
	 *
	 * @param keycode {@link com.badlogic.gdx.Input.Keys}
	 * @return true if the event has been handled else false
	 */
	@Override
	default boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * Key released
	 *
	 * @param keycode {@link com.badlogic.gdx.Input.Keys}
	 * @return true if the event has been handled else false
	 */
	@Override
	default boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Key typed
	 *
	 * @param character typed character
	 * @return true if the event has been handled else false
	 */
	@Override
	default boolean keyTyped(char character) {
		return false;
	}

	@Override
	default boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	default boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	default boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	default boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	default boolean scrolled(int amount) {
		return false;
	}
}


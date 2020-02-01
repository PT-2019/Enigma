package api.libgdx.utils;

import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.InputProcessor;

/**
 * Convenience interface pour la saisie pour éviter de devoir réécrire des méthodes
 * de input processor vides.
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
@ConvenienceClass
public interface InputAdapter extends InputProcessor {

	/**
	 * Touche appuyée
	 *
	 * @param keycode {@link com.badlogic.gdx.Input.Keys}
	 * @return true si l'événement a été géré
	 * @since 3.0
	 */
	@Override
	default boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * Touche relâchée
	 *
	 * @param keycode {@link com.badlogic.gdx.Input.Keys}
	 * @return true si l'événement a été géré
	 * @since 3.0
	 */
	@Override
	default boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Touche typed
	 *
	 * @param character typed character
	 * @return true si l'événement a été géré
	 * @since 3.0
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


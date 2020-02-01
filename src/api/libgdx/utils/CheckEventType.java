package api.libgdx.utils;

import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Vérifie le type d'évènement
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @since 4.0 23/12/2019
 */
@ConvenienceClass
public final class CheckEventType {

	/**
	 * Clic de la souris
	 *
	 * @param event l'évènement
	 * @return true si c'est un clic
	 * @since 4.0
	 */
	public static boolean isMouseClicked(Event event) {
		return event instanceof InputEvent && ((InputEvent) event).getType().equals(InputEvent.Type.touchDown);
	}

	/**
	 * Appui sur une touche
	 *
	 * @param event l'évènement
	 * @return true si c'est un appui sur une touche
	 * @since 4.0
	 */
	public static boolean isKeyPressed(Event event) {
		return event instanceof InputEvent && ((InputEvent) event).getType().equals(InputEvent.Type.keyTyped);
	}

}

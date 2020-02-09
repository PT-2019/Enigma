package editor.bar.edition.actions;

import common.enigmas.Enigma;
import common.entities.types.EnigmaContainer;
import common.map.AbstractMap;
import common.map.MapObject;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;

/**
 * Desc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.1
 * @since 6.0 01/02/2020
 */
public final class EditorActionFactory {

	/**
	 * Crée une action entité
	 *
	 * @param type type
	 * @param parent parent
	 * @param object object concerné par l'action
	 * @param <T> le type du premier argument de la méthode add
	 * @return une action
	 * @throws IllegalArgumentException si erreur
	 * @since 6.0
	 */
	public static <T> EditorAction entity(ActionTypes type, EditorActionParent<T> parent, Object object){
		if (type.equals(ActionTypes.ADD_ENTITY)) {
			return new EditorActionAddEntity(type, (AbstractMap) parent, (MapObject) object);
		} else if (type.equals(ActionTypes.REMOVE_ENTITY)) {
			return new EditorActionRemoveEntity(type, (AbstractMap) parent, (MapObject) object);
		}
		throw new IllegalArgumentException(type+" isn't an entity action.");
	}

	/**
	 * Crée une action des énigmes
	 *
	 * @param type type
	 * @param parent parent
	 * @param object object concerné par l'action
	 * @param update code pour la mise a jour.
	 * @param <T> le type du premier argument de la méthode add
	 * @return une action
	 * @throws IllegalArgumentException si erreur
	 * @since 6.0
	 */
	public static <T> EditorAction enigma(ActionTypes type, EditorActionParent<T> parent, Object object,
	                                      Runnable update){
		if(type.equals(ActionTypes.REMOVE_ENIGMA)){
			return new EditorActionRemoveEnigma(type, (EnigmaContainer) parent, (Enigma) object, update);
		} else if(type.equals(ActionTypes.ADD_ENIGMA)){
			throw new IllegalArgumentException(type+"not available yet");
		}

		throw new IllegalArgumentException(type+" isn't an enigma action.");
	}
}

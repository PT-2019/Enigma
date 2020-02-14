package editor.bar.edition.actions;

import common.enigmas.Enigma;
import common.entities.Consumable;
import common.entities.types.Container;
import common.entities.types.Content;
import common.entities.types.EnigmaContainer;
import common.entities.types.Living;
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
 * @version 6.1
 * @since 6.0 01/02/2020
 */
public final class EditorActionFactory {

	/**
	 * Crée une action entité
	 *
	 * @param type   type
	 * @param parent parent
	 * @param object object concerné par l'action
	 * @param <T>    le type du premier argument de la méthode add
	 * @return une action
	 * @throws IllegalArgumentException si erreur
	 * @since 6.0
	 */
	public static <T> EditorAction entity(ActionTypes type, EditorActionParent<T> parent, Object object) {
		if (type.equals(ActionTypes.ADD_ENTITY)) {
			return new EditorActionAddEntity(type, (AbstractMap) parent, (MapObject) object);
		} else if (type.equals(ActionTypes.REMOVE_ENTITY)) {
			return new EditorActionRemoveEntity(type, (AbstractMap) parent, (MapObject) object);
		}
		throw new IllegalArgumentException(type + " isn't an entity action.");
	}

	/**
	 * Crée une action des énigmes
	 *
	 * @param type   type
	 * @param parent parent
	 * @param object object concerné par l'action
	 * @return une action
	 * @throws IllegalArgumentException si erreur
	 * @since 6.0
	 */
	public static EditorAction actionWithinAMenu(ActionTypes type, Object parent, Object object) {
		//on recherche en fonction du type la classe a lancer
		//on peut et à juste tord ce demander a quoi sert la méthode plus haut.
		//il existe des méthodes dans utility pour généraliser tout ça mais
		//pas pour tout de suite

		if (type.equals(ActionTypes.REMOVE_ENIGMA)) {
			return new EditorActionRemoveEnigma(type, (EnigmaContainer) parent, (Enigma) object);
		} else if (type.equals(ActionTypes.ADD_ENIGMA)) {
			return new EditorActionAddEnigma(type, (EnigmaContainer) parent, (Enigma) object);
		} else if (type.equals(ActionTypes.ADD_SUB_ENTITY)) {
			return new EditorActionAddSubEntity(type, (Container) parent, (Consumable) object);
		} else if (type.equals(ActionTypes.REMOVE_SUB_ENTITY)) {
			return new EditorActionRemoveSubEntity(type, (Container) parent, (Consumable) object);
		} else if (type.equals(ActionTypes.SET_NAME)) {
			return new EditorActionSetName(type, (Living) parent, (String) object);
		} else if (type.equals(ActionTypes.SET_CONTENT)) {
			return new EditorActionSetContent(type, (Content) parent, (String) object);
		}

		throw new IllegalArgumentException(type + " isn't an actionWithinMenu.");
	}
}

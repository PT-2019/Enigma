package editor.bar.edition.actions;

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
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public final class EditorActionFactory {

	//TODO: on remarque les arguments : type, parent, object concerné
	//  sur le parent, on appelle toujours "add" et "remove" avec l'object.
	//  faire une interface, on utilisera la généricité si besoin.
	//  puis factory ces méthodes en une seule, le but ultime
	//  le nombre d'argument a remove peut être variable

	public static EditorAction create(ActionTypes type, AbstractMap map, MapObject object){

			if (type.equals(ActionTypes.ADD_ENTITY)) {

				return new EditorActionAddEntity(type, map, object);
			} else if (type.equals(ActionTypes.REMOVE_ENTITY)) {

				return new EditorActionRemoveEntity(type, map, object);
			}


		throw new IllegalArgumentException(type+"not available yet");
	}

}

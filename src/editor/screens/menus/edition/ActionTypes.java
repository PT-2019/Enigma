package editor.screens.menus.edition;

/**
 * Les types d'actions possibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
public enum ActionTypes {
/*
	/**
	 * Ajout d'une entité
	 * @see game.entity.map.MapTestScreen#loadEntity(
	 *      editor.entity.EntitySerializable,
	 *      com.badlogic.gdx.math.Vector2)
	 *
	ADD_ENTITY(ActionTypes.REMOVE_ENTITY),
	/**
	 * Suppression d'une entité
	 * @see game.entity.map.MapTestScreen#removeEntity(api.entity.GameObject)
	 *
	REMOVE_ENTITY(ActionTypes.ADD_ENTITY),
	/**
	 * Ajoute une énigme
	 *
	ADD_ENIGMA(ActionTypes.REMOVE_ENIGMA),
	/**
	 * Suppression d'une énigme
	 * Affiche un message qui confirme la suppression
	 *
	REMOVE_ENIGMA(ActionTypes.ADD_ENIGMA);*/;

	public final ActionTypes invert;

	/**
	 * Crée un type d'action
	 * @param invert l'action inverse
	 */
	ActionTypes(ActionTypes invert) {
		this.invert = invert;
	}
}

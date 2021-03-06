package editor.bar.edition.actions;

import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;
import common.map.AbstractMap;
import common.map.MapObject;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.EditorAction;

/**
 * Suppression d'une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
class EditorActionRemoveEntity implements EditorAction {

	private final ActionTypes type;
	private final AbstractMap map;
	private final GameObject entity;
	private final Vector2 pos;

	EditorActionRemoveEntity(ActionTypes type, AbstractMap map, MapObject entity) {
		this.type = type;
		this.map = map;
		this.entity = entity.getEntity();
		this.pos = entity.getPosition();
	}

	@Override
	public void doAction() {
		this.map.remove(this.entity);
	}

	@Override
	public void undoAction() {
		this.map.add(entity, pos);
	}

	@Override
	public ActionTypes getType() {
		return this.type;
	}

	@Override
	public void clear() {
		//si plus dans la map
		if (this.map.getEntities().getEntry(this.entity) == null) {
			this.map.freeId(this.entity); //free
		}
	}

	/**
	 * Retourne l'entité
	 *
	 * @return l'entité
	 */
	@Override
	public Object getActor() {
		return this.entity;
	}
}


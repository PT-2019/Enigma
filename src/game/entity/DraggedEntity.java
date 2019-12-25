package game.entity;

import api.entity.actor.GameActorDragAndDrop;
import editor.entity.EntitySerializable;
import game.utils.DragAndDrop;

/**
 * Une entité qui peut être déplacée.
 * Créée par {@link game.utils.DragAndDropBuilder} depuis {@link EntityContainer}
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 21/12/2019
 * @since 4.0 20/12/2019
 */
public class DraggedEntity extends GameActorDragAndDrop {

	private final EntitySerializable entity;

	/**
	 * Crée une entité déplaçable
	 *
	 * @param container patron de conception ^^ (son model)
	 */
	public DraggedEntity(EntityContainer container) {
		super();
		this.setTexture(container.getEntity().getPath());
		this.draggable = true;
		this.droppable = false;
		this.entity = container.getEntity();
		this.addListener(new DragAndDrop(this, container));
	}

	public EntitySerializable getEntity() {
		return entity;
	}
}

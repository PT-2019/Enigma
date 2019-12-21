package game.entity;

import editor.entity.EntitySerializable;
import api.actors.GameActorDragAndDrop;

public class DraggedEntity extends GameActorDragAndDrop {

	private final EntitySerializable entity;

	public DraggedEntity(EntitySerializable entitySerializable, EntityContainer container) {
		super();
		this.setTexture(entitySerializable.getPath());
		this.draggable = true;
		this.droppable = false;
		this.entity = entitySerializable;
		this.addListener(new DragAndDrop(this, container));
	}

	public EntitySerializable getEntity() {
		return entity;
	}
}

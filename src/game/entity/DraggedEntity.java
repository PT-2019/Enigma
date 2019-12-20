package game.entity;

import editor.entity.EntitySerializable;
import game.api.GameActorDragAndDrop;

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
}

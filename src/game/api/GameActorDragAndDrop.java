package game.api;

public abstract class GameActorDragAndDrop extends GameActorTextured {

	protected boolean draggable, droppable;

	public GameActorDragAndDrop() {
		super();
		this.draggable = false;
		this.droppable = false;
	}

	public boolean isDraggable(){ return this.draggable; }
	public boolean isDroppable(){ return this.droppable; }
}


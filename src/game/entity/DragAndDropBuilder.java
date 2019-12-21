package game.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.entity.EntitySerializable;

public class DragAndDropBuilder extends InputListener {

	private final EntityContainer container;
	private final Stage dnd;
	private final EntitySerializable entity;

	public DragAndDropBuilder(EntityContainer container, Stage dnd, EntitySerializable entity) {
		this.container = container;
		this.dnd = dnd;
		this.entity = entity;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		DraggedEntity draggedEntity = new DraggedEntity(entity, container);
		Vector2 v = this.container.getAbsolutePosition();
		draggedEntity.setPosition(v.x, v.y);
		this.dnd.addActor(draggedEntity);
		//on transmet le clic a l'entité clone
		draggedEntity.fire(event);
		return true;
	}
}

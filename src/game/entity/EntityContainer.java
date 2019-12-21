package game.entity;

import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.entity.EntitySerializable;
import api.actors.GameActorTextured;

//si on clique dessus, crée une entité sur le layer dnd
public class EntityContainer extends GameActorTextured {

	public EntityContainer(EntitySerializable entitySerializable, Stage dnd) {
		super();
		this.setTexture(entitySerializable.getPath());
		this.addListener(new DragAndDropBuilder(this, dnd, entitySerializable ));
	}
}

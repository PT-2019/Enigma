package game.entity;

import api.entity.actor.GameActorTextured;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.entity.EntitySerializable;
import game.utils.DragAndDropBuilder;

/**
 * Une entité a partir de laquelle on crée des {@link DraggedEntity} ie des entités déplaçables.
 * Au clic, une copie est générée sur le niveau drag and drop.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 20/12/2019
 * @since 4.0 20/12/2019
 */
public class EntityContainer extends GameActorTextured {

	private EntitySerializable entity;

	/**
	 * Crée un créateur de entity dans le stage du drag and drop
	 *
	 * @param entity l'entité qui doit être crée
	 * @param dnd    le stage du drag and drop
	 */
	public EntityContainer(EntitySerializable entity, Stage dnd) {
		super();
		this.entity = entity;
		this.setTexture(entity.getPath());
		this.addListener(new DragAndDropBuilder(this, dnd));
	}

	public EntitySerializable getEntity() {
		return entity;
	}
}

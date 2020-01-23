package game.utils;

import api.enums.EditorState;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.entity.DraggedEntity;
import game.entity.EntityContainer;
import game.screen.TestScreen;

/**
 * Constructeur d'objects drag and drop. Si on clique sur une entité, alors
 * il crée un copie dans le layer drag and drop et lui passe le focus du clic.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.3 24/12/2019
 * @see DragAndDrop
 * @since 4.0 20/12/2019
 */
public class DragAndDropBuilder extends InputListener {

	/**
	 * entity qui sera déplaçable
	 */
	private final EntityContainer container;
	/**
	 * le stage du drag and drop
	 */
	private final Stage dnd;

	/**
	 * Constructeur d'objects drag and drop.
	 *
	 * @param container entity qui sera déplaçable
	 * @param dnd       le stage du drag and drop
	 */
	public DragAndDropBuilder(EntityContainer container, Stage dnd) {
		this.container = container;
		this.dnd = dnd;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if(!TestScreen.isState(EditorState.NORMAL)) return false;
		//Crée une copie de l'entité
		DraggedEntity draggedEntity = new DraggedEntity(this.container);
		Vector2 v = this.container.getAbsolutePosition();
		draggedEntity.setPosition(v.x, v.y);

		//ajout au layer dnd
		this.dnd.addActor(draggedEntity);

		//on transmet le clic a l'entité clone
		draggedEntity.fire(event);

		return true;//évènement géré
	}
}

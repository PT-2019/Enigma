package game.dnd.inventory;

import api.libgdx.actor.GameActorTextured;
import api.libgdx.actor.GameActorUtilities;
import api.libgdx.utils.LibgdxUtility;
import api.utils.Observer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;
import common.map.MapTestScreen;
import common.utils.Logger;
import data.EditorState;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.dnd.DragAndDrop;
import game.dnd.DraggedEntity;
import game.dnd.EntityContainer;
import game.screens.TestScreen;

import java.awt.Cursor;

/**
 * Constructeur d'objects drag and drop. Si on clique sur une entité, alors
 * il crée un copie dans le layer drag and drop et lui passe le focus du clic.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see DragAndDrop
 * @since 4.0 20/12/2019
 */
public class InventoryDndBuilder extends InputListener {

	/**
	 * le stage du drag and drop
	 *
	 * @since 4.2
	 */
	private final Stage dnd;
	private final ButtonInventory container;

	/**
	 * Constructeur d'objects drag and drop.
	 *
	 * @param container entity qui sera déplaçable
	 * @param dnd       le stage du drag and drop
	 * @since 4.0
	 */
	public InventoryDndBuilder(ButtonInventory container, Stage dnd) {
		this.container = container;
		this.dnd = dnd;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		Item item = container.getItem();

		if(item == null){
			return true;
		}

		//Crée une copie de l'entité
		InventoryDraggedItem g = new InventoryDraggedItem(this.container);

		//ajout au layer dnd
		this.dnd.addActor(g);

		//on transmet le clic a l'entité clone
		g.fire(event);

		return true;//évènement géré
	}
}

package game.dnd.inventory;

import api.libgdx.actor.GameActorDragAndDrop;
import api.libgdx.actor.GameActorUtilities;
import com.badlogic.gdx.math.Vector2;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;
import game.dnd.DragAndDropBuilder;
import game.dnd.EntityContainer;

/**
 * Une entité qui peut être déplacée.
 * Créée par {@link DragAndDropBuilder} depuis {@link EntityContainer}
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 21/12/2019
 * @since 4.0 20/12/2019
 */
public class InventoryDraggedItem extends GameActorDragAndDrop {

	private final ButtonInventory buttonInventory;
	private InventoryDisplay inventoryDisplay;

	/**
	 * Crée une entité déplaçable
	 *
	 * @param container patron de conception ^^ (son model)
	 */
	public InventoryDraggedItem(ButtonInventory container, InventoryDisplay inventoryDisplay) {
		super();
		this.inventoryDisplay = inventoryDisplay;
		Vector2 v = GameActorUtilities.getAbsolutePosition(container);
		v.y -= container.getTexture().getRegionHeight();
		this.setPosition(v.x,v.y);
		this.setTexture(container.getTexture());
		this.setSize(InventoryDisplay.ACTOR_WIDTH,InventoryDisplay.ACTOR_HEIGHT);
		this.draggable = true;
		this.droppable = false;
		this.buttonInventory = container;
		this.addListener(new DragAndDropInventory(this));
	}

	public ButtonInventory getButtonInventory() {
		return buttonInventory;
	}

	public InventoryDisplay getInventoryDisplay() {
		return inventoryDisplay;
	}
}

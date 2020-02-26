package game.dnd.inventory;

import api.libgdx.actor.GameActorUtilities;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import common.entities.special.inventory.ButtonInventory;
import common.utils.Logger;
import editor.EditorLauncher;

import java.awt.Cursor;

/**
 * Observateur du drag and drop
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 20/12/2019
 * @since 4.0 20/12/2019
 */
public class DragAndDropInventory extends InputListener {

	private final InventoryDraggedItem dragged;

	/**
	 * Décalage du clic par rapport au sommet gauche de l'entité
	 */
	private float offsetX, offsetY;

	DragAndDropInventory(InventoryDraggedItem dragged) {
		this.dragged = dragged;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	// Drag Start
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (this.dragged.isDraggable()) {
			//cursor de drag
			if(EditorLauncher.getInstance() != null && EditorLauncher.getInstance().getWindow() != null)
				EditorLauncher.getInstance().getWindow().setCursor(new Cursor(Cursor.HAND_CURSOR));

			this.offsetX = x; //sauvegarde la décalage
			this.offsetY = y;

			this.dragged.toFront();//place au premier plan
			return true; //event traité
		}

		return false;
	}

	// Drop
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		//reset cursor
		//cursor de drag
		if(EditorLauncher.getInstance() != null && EditorLauncher.getInstance().getWindow() != null)
			EditorLauncher.getInstance().getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		//supprime
		this.dragged.remove();

		ButtonInventory buttonInventory = this.dragged.getButtonInventory();
		//récupère tous les boutons inventory
		Group g = buttonInventory.getParent();

		Vector2 pos = GameActorUtilities.getAbsolutePosition(this.dragged);//x,y de l'object bas gauche
		//correction parce que je veux haut à gauche
		pos.y += this.dragged.getHeight();

		ButtonInventory find = null;
		for(Actor c: g.getChildren()){
			if(c instanceof ButtonInventory){
				if(GameActorUtilities.contains(c, pos)){
					find = (ButtonInventory) c;
					break;
				}
			}
		}

		if(find == null) return;

		if(find.getItem() != null){
			return;
		}

		Logger.printDebug("DndInventory","Dépot dans une case.");

		//copie
		find.setItem(buttonInventory.getItem());
		find.refreshButton();

		this.dragged.getInventoryDisplay().setSelectItem(find);

		//supprime l'ancien
		buttonInventory.setItem(null);
		buttonInventory.refreshButton();

		this.dragged.addAction(Actions.fadeOut(0.3f));


	}

	// Drag
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//calcule entre la position du clic et la nouvelle position
		//déplace l'actor à sa nouvelle pos
		this.dragged.moveBy(x - this.offsetX, y - this.offsetY);
	}
}


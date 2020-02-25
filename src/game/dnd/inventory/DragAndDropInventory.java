package game.dnd.inventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

		System.out.println("dépot!");

		this.dragged.remove();
	}

	// Drag
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//calcule entre la position du clic et la nouvelle position
		//déplace l'actor à sa nouvelle pos
		this.dragged.moveBy(x - this.offsetX, y - this.offsetY);
	}
}


package game.dnd;

import api.libgdx.actor.GameActorUtilities;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import common.map.MapTestScreen;
import common.utils.Logger;
import data.EditorState;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.screens.TestScreen;

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
public class DragAndDrop extends InputListener {

	/**
	 * Le menu qui contenait l'entité avant drag and drop
	 */
	private final Group container;

	/**
	 * L'entité déplacée
	 */
	private final DraggedEntity dragged;

	/**
	 * Décalage du clic par rapport au sommet gauche de l'entité
	 */
	private float offsetX, offsetY;

	/**
	 * Observateur du drag and drop
	 *
	 * @param dragged   L'entité déplacée
	 * @param container Le menu qui contenait l'entité avant drag and drop
	 */
	DragAndDrop(DraggedEntity dragged, EntityContainer container) {
		this.dragged = dragged;
		this.container = container.getParent().getParent();
		this.offsetX = 0;
		this.offsetY = 0;
	}

	// Drag Start
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (this.dragged.isDraggable()) {
			//cursor de drag
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
		EditorLauncher.getInstance().getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		if(EditorLauncher.containsState(EditorState.ZOOM)){
			Logger.printDebug("DragAndDrop#up", "En mode Zoom");

			//fade out si pas placé
			this.dragged.addAction(
					Actions.sequence(
							Actions.fadeOut(0.2f),
							Actions.hide(),
							Actions.run(this.dragged::remove)
					)
			);

			EnigmaGame.getCurrentScreen().showToast(NeedToBeTranslated.DRAG_AND_DROP_FAILED_ZOOM);
			return;
		}

		Vector2 pos = GameActorUtilities.getAbsolutePosition(this.dragged);//x,y de l'object bas gauche
		//correction parce que je veux haut à gauche
		pos.y += this.dragged.getHeight();

		//si on la pas mis sur le menu donc partie de la map non visible car cachée par celui-ci
		boolean retour = !GameActorUtilities.contains(this.container, pos);
		if (retour) {//si pas caché
			MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
			//on regarde si on l'a mis sur la map
			retour = map.loadEntity(this.dragged.getEntity(), pos) != null;
		}/* else {
			Gdx.app.debug("DragAndDrop", "dans le menu");
		}*/

		if (retour) {//placé
			//disparaît
			this.dragged.remove();
		} else {
			//fade out si pas placé
			this.dragged.addAction(
					Actions.sequence(
							Actions.fadeOut(0.2f),
							Actions.hide(),
							Actions.run(this.dragged::remove)
					)
			);

			//message
			EnigmaGame.getCurrentScreen().showToast(NeedToBeTranslated.DRAG_AND_DROP_FAILED);
		}
	}

	// Drag
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//calcule entre la position du clic et la nouvelle position
		//déplace l'actor à sa nouvelle pos
		this.dragged.moveBy(x - this.offsetX, y - this.offsetY);
	}
}


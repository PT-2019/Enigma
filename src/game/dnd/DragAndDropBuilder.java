package game.dnd;

import api.utils.Observer;
import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import data.EditorState;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.screens.TestScreen;
import general.entities.GameObject;
import general.map.MapTestScreen;

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
public class DragAndDropBuilder extends InputListener {

	/**
	 * Si le déplacement est bloqué et le popup récupère le clic
	 *
	 * @since 5.0
	 */
	private static Observer forPopup = null;

	/**
	 * entity qui sera déplaçable
	 *
	 * @since 4.0
	 */
	private final EntityContainer container;
	/**
	 * le stage du drag and drop
	 *
	 * @since 4.2
	 */
	private final Stage dnd;

	/**
	 * Constructeur d'objects drag and drop.
	 *
	 * @param container entity qui sera déplaçable
	 * @param dnd       le stage du drag and drop
	 * @since 4.0
	 */
	public DragAndDropBuilder(EntityContainer container, Stage dnd) {
		this.container = container;
		this.dnd = dnd;
	}

	/**
	 * Si le déplacement est bloqué et le popup récupère le clic
	 *
	 * @param forPopup true si on bloque sinon false
	 * @since 5.0
	 */
	public static void setForPopup(Observer forPopup) {
		DragAndDropBuilder.forPopup = forPopup;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (!TestScreen.isState(EditorState.NORMAL)) {
			//POUR LE CONFORT, ON FORCE LE PASSAGE EN NORMAL
			TestScreen.setState(EditorState.NORMAL);
			EditorLauncher.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (forPopup != null) {
			Utility.printDebug("DnDBuilder", "Déplacement bloqué. Sélection.");
			MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
			GameObject obj = map.loadEntity(this.container.getEntity(), null);
			forPopup.update(obj);
			return true;
		}
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

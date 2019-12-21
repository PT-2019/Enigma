package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import editor.EditorLuncher;
import api.actors.GameActorDragAndDrop;
import api.actors.GameActorUtilities;
import game.screen.TestScreen;

import java.awt.Cursor;

public class DragAndDrop extends InputListener {

	private GameActorDragAndDrop actor;

	private final EntityContainer container;

	private float offsetX, offsetY;

	public DragAndDrop(GameActorDragAndDrop actor, EntityContainer container) {
		this.actor = actor;
		this.container = container;
		this.offsetX = 0;
		this.offsetY = 0;
	}

	// Drag Strart
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if(this.actor.isDraggable()) {
			//cursor de drag
			EditorLuncher.getInstance().getWindow().setCursor(new Cursor(Cursor.HAND_CURSOR));

			this.offsetX = x; //sauvegarde la décalage
			this.offsetY = y;

			this.actor.toFront();//place au premier plan
			return true; //event traité
		}

		return false;
	}

	// Drop
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		//reset cursor
		EditorLuncher.getInstance().getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		//si on la pas mis sur le menu donc partie de la map non visible car cachée par celui-ci
		//TODO: ne marche pas
		if(!GameActorUtilities.contains(this.container.getParent(), new Vector2(x, y))){
			//TODO: alors on regarde si on l'a mis sur la map

			MapLibgdx map = TestScreen.t.getMap();
			OrthographicCamera camera = map.getCamera();

			Rectangle c = new Rectangle();
			c.setPosition(Gdx.graphics.getWidth()/2f - camera.position.x ,
					Gdx.graphics.getHeight()/2f - camera.position.y);
			c.setSize(map.getMapWidth(), map.getMapHeight());

			System.out.println(camera.position+" "+c);

			//disparaît
			this.actor.remove();
		} else {
			Gdx.app.debug("DragAndDrop","dans le menu");

			//fade out
			this.actor.addAction(
					Actions.sequence(
							Actions.fadeOut(0.2f),
							Actions.hide(),
							Actions.run(()-> this.actor.remove())
					));
		}
	}

	// Drag
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//calcule entre la position du clic et la nouvelle position
		//déplace l'actor à sa nouvelle pos
		this.actor.moveBy(x - this.offsetX,y - this.offsetY);
	}
}


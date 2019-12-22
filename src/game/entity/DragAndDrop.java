package game.entity;

import api.actors.GameActorUtilities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import editor.EditorLuncher;
import game.screen.TestScreen;

import java.awt.*;

public class DragAndDrop extends InputListener {

    private final Group container;
    private DraggedEntity actor;
    private float offsetX, offsetY;

    public DragAndDrop(DraggedEntity actor, EntityContainer container) {
        this.actor = actor;
        this.container = container.getParent().getParent();
        this.offsetX = 0;
        this.offsetY = 0;
    }

    // Drag Strart
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (this.actor.isDraggable()) {
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

        Vector2 pos = GameActorUtilities.getAbsolutePosition(actor);//x,y de l'object bas gauche
        //correction parce que je veux haut à gauche
        pos.y += actor.getHeight();

        //si on la pas mis sur le menu donc partie de la map non visible car cachée par celui-ci
        boolean retour = !GameActorUtilities.contains(this.container, pos);
        if (retour) {//si pas caché
            MapLibgdx map = TestScreen.t.getMap();
            //on regarde si on l'a mis sur la map
            retour = map.loadEntity(actor.getEntity(), pos);
        } else {
            Gdx.app.debug("DragAndDrop", "dans le menu");
        }

        if (retour) {//placé
            //disparaît
            this.actor.remove();
        } else {
            //fade out
            this.actor.addAction(
                    Actions.sequence(
                            Actions.fadeOut(0.2f),
                            Actions.hide(),
                            Actions.run(() -> this.actor.remove())
                    )
            );
        }
    }

    // Drag
    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        //calcule entre la position du clic et la nouvelle position
        //déplace l'actor à sa nouvelle pos
        this.actor.moveBy(x - this.offsetX, y - this.offsetY);
    }
}


package game.gui;

import api.libgdx.utils.LibgdxUtility;
import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import data.EntitiesCategories;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.EditorAction;

import java.util.ArrayList;

/**
 * Popup d'informations en mode toast android
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 04/02/2020
 * @since 6.0 04/02/2020
 */
public class Toast extends Window implements Observer<ActionsManager> {

    /**
     * Size
     * @since 6.0
     */
    private static final float WIDTH = 300, HEIGHT = 70;

    /**
     * Crée un toast
     * @since 6.0
     */
    public Toast() {
        //chargement du style de la fenêtre
        super("", LibgdxUtility.loadSkin("assets/files/atlas/uiskin.json", "assets/files/atlas/uiskin.atlas"));
        //this.setBackground("default-select-selection"); // <-- cyan/bleu
        this.setBackground("default-round-down");

        ActionsManager instance = ActionsManager.getInstance();
        instance.addObserver(this);

        this.add("");

        this.setSize(WIDTH, HEIGHT);
        //title center
        this.getTitleLabel().setAlignment(Align.center);
        //top aligned
        this.setPosition(
                (Gdx.graphics.getWidth() - CategoriesMenu.WIDTH) / 2f
                        + CategoriesMenu.WIDTH,
                Gdx.graphics.getHeight()/2f + Gdx.graphics.getHeight()/2f * 0.7f  + HEIGHT/2, Align.center);
        this.setVisible(true);
        this.hide(true);
    }

    /**
     * "Cache le composant" mais il est toujours mis à jour (différent de setVisible)
     * @param b true pour cacher sinon false
     * @since 6.0
     */
    public void hide(boolean b){
        if(b) this.getColor().a = 0f;
        else this.getColor().a = 1f;
    }

    /**
     * Retourne l'opacité
     * @return opacité entre 0 et 1.
     * @since 6.0
     */
    private float getAlpha() {
        return this.getColor().a;
    }

    /**
     * Update du toast
     * @param text texte a afficher
     */
    public void update(String text){
        if(this.hasActions()){
            //change juste le texte et on reset le temps d'affichage si +de 50% visible
            if(this.getAlpha() >= 0.5f){
                this.clearActions();
                this.clear();
                this.addAction(Actions.fadeIn(0.01f));
                this.add(text);
                this.addAction(Actions.sequence(Actions.delay(2f), Actions.fadeOut(0.5f)));

                return;
            }
        }
        this.addAction(
                Actions.sequence(
                        Actions.run(() -> {
                            this.clear();
                            this.add(text);
                            this.addAction(
                                    Actions.sequence(
                                            Actions.fadeIn(0.3f),
                                            Actions.delay(1f),
                                            Actions.fadeOut(0.5f)
                                    )
                            );
                        })
                )
        );
    }

    /**
     * Update du toast selon action faite
     * @param object observateur des actions
     * @since 6.0
     */
    @Override
    public void update(ActionsManager object) {
        ActionsManager.LastAction lastAction = object.getLastAction();
        if(lastAction == null) return;
        String text = lastAction.type.toString();
        if(text.length() > 0){
            text +=": ";
        }
        text += lastAction.last.getType().toString();
        this.update(text);
    }
}

package editor.entity.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Cette classe permet d'écouter les cliques de la souris sur les cases
 * de la map.
 */
public class CaseListener extends ClickListener {

    /**
     * PopUp qui représente graphiquement toutes les informations de la case cliquée
     */
    private CasePopUp popUp;

    public CaseListener (CasePopUp pop){
        super(Input.Buttons.LEFT);
        popUp = pop;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        CaseView actor =(CaseView) event.getTarget();
        popUp.setCell(actor.getCell());
        popUp.display();
    }
}

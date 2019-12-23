package editor.entity.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * TODO: comment CaseListener and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CaseListener extends ClickListener {

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

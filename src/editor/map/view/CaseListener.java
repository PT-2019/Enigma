package editor.map.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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

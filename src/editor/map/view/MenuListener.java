package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import editor.map.Case;

import javax.swing.*;

public class MenuListener extends ClickListener {

    private Case c;

    private EntityPopMenu menu;

    private JComponent component;

    public MenuListener(Case c,JComponent component ,EntityPopMenu menu){
        super(Input.Buttons.RIGHT);
        this.c = c;
        this.menu = menu;
        this.component = component;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        menu.show(component, Gdx.input.getX(),Gdx.input.getY());
    }
}

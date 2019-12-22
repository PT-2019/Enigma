package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import game.entity.MapLibgdx;
import game.utils.Bounds;

import javax.swing.*;
import java.awt.*;

public class TestMapControl implements InputProcessor {

    private boolean ispush;

    private EntityPopMenu menu;

    private JComponent component;

    private OrthographicCamera camera;

    private MapLibgdx map;

    public TestMapControl(MapLibgdx map) {
        camera = map.getCamera();
        ispush = false;
        this.map = map;
        this.component = (JComponent) map.getContainer();
        //this.menu = new EntityPopMenu(r,col,cam);
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.CONTROL_LEFT)
            ispush = true;
        if (keycode == Input.Keys.PLUS && ispush)
            this.plusCamera();
        if (keycode == Input.Keys.MINUS && ispush)
            this.minCamera();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            camera.translate(-32, 0);
            return true;
        }
        if (keycode == Input.Keys.RIGHT){
            camera.translate(32, 0);
            return true;
        }
        if(keycode ==Input.Keys.UP){
            camera.translate(0,32);
            return true;
        }
        if(keycode == Input.Keys.DOWN) {
            camera.translate(0, -32);
            return true;
        }
        if (keycode == Input.Keys.CONTROL_LEFT) {
            ispush = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT){
            menu.show(component, Gdx.input.getX(),Gdx.input.getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (amount == 1) {
                camera.zoom += 0.1;
                updateMapBounds(-1);
            } else {
                camera.zoom -= 0.1;
                updateMapBounds(1);
            }
            return true;
        }
        return false;
    }

    private void updateMapBounds(float zoom){
        float left = map.getMapBounds().left, right = map.getMapBounds().right;
        float top =  map.getMapBounds().top, bot =  map.getMapBounds().bot;

        left -= zoom * 27;
        right += zoom * 27;
        top -= zoom * 18;
        bot += zoom * 18;

        map.setMapBounds(new Bounds(left, right, top, bot));

        System.out.println(map.getMapBounds());
    }


    private void plusCamera(){
        camera.zoom -= 0.05;
    }

    private void minCamera(){
        camera.zoom += 0.05;
    }
}

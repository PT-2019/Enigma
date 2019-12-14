package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapControlleur implements InputProcessor {

    private boolean ispush;

    private OrthographicCamera camera;

    private TiledMap tiledMap;

    public MapControlleur (Camera cam,TiledMap map){
        camera =(OrthographicCamera) cam;
        tiledMap = map;
        ispush = false;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.CONTROL_LEFT)
            ispush = true;
        if (keycode == Input.Keys.PLUS && ispush)
            this.plusCamera();
        if (keycode == Input.Keys.MINUS && ispush)
            this.minCamera();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        if (keycode == Input.Keys.CONTROL_LEFT)
            ispush = false;
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
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
            if (amount==1){
                this.minCamera();
            }else{
                this.plusCamera();
            }
        }
        return false;
    }

    private void plusCamera(){
        camera.zoom -= 0.05;
    }

    private void minCamera(){
        camera.zoom += 0.05;
    }
}

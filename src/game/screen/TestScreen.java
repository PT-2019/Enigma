package game.screen;

import api.LibgdxScreen;
import api.enums.keys.CameraKeys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import game.EnigmaGame;
import game.entity.CategoriesMenu;
import game.entity.MapLibgdx;

public class TestScreen extends LibgdxScreen {

    //TODO: fast but remove
    public static TestScreen t;
    private Stage main;
    private Stage hud;
    private Stage dnd;
    private MapLibgdx map;

    @Override
    public void init() {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        t = this;

        this.main = new Stage();
        this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.map = new MapLibgdx("assets/map/Empty.tmx");
        this.main.addActor(this.map);

        this.main.getCamera().position.set(map.getMapWidth() / 2, map.getMapHeight() / 2, 0);

        this.dnd = new Stage();

        this.hud = new Stage();
        this.hud.addActor(new CategoriesMenu(dnd));

        this.listen(this.dnd);
        this.listen(this.hud);
        this.listen(this.main);
        this.listen(this.map);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (CameraKeys.CAMERA_LEFT.isKey(keycode)) {
            main.getCamera().translate(-32, 0, 0);
            return true;
        }
        if (CameraKeys.CAMERA_RIGHT.isKey(keycode)) {
            main.getCamera().translate(32, 0, 0);
            return true;
        }
        if (CameraKeys.CAMERA_UP.isKey(keycode)) {
            main.getCamera().translate(0, 32, 0);
            return true;
        }
        if (CameraKeys.CAMERA_DOWN.isKey(keycode)) {
            main.getCamera().translate(0, -32, 0);
            return true;
        }

        return false;
    }

    @Override
    public void input() {
    }

    @Override
    public void update(float dt) {
        this.dnd.act(dt);
        this.hud.act(dt);
        this.main.act(dt);
    }

    @Override
    public void render() {
        this.main.draw();
        this.hud.draw();
        this.dnd.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.dnd.getViewport().setScreenSize(width, height);
        this.hud.getViewport().setScreenSize(width, height);
        this.main.getViewport().setScreenSize(width, height);
        this.main.getViewport().update(width, height);
        EnigmaGame.setScreenSize(width, height);
    }

    public MapLibgdx getMap() {
        return map;
    }

    @Override
    public void display(boolean display) {
    }

    @Override
    public void dispose() {
        this.main.dispose();
        this.dnd.dispose();
        this.hud.dispose();
    }
}

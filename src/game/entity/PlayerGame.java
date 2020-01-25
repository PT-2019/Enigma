package game.entity;

import api.entity.actor.GameActorAnimation;
import com.badlogic.gdx.InputProcessor;

public class PlayerGame extends GameActorAnimation implements InputProcessor {

    public PlayerGame(){
        this.setAnimation("assets/entities/monsters/019.png",4,4,(float)0.3,4,4,0);
    }

    @Override
    public boolean keyDown(int i) {

        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}

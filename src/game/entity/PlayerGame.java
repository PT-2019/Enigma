package game.entity;

import api.entity.actor.GameActorAnimation;
import api.enums.Direction;
import api.enums.keys.CameraKeys;
import com.badlogic.gdx.InputProcessor;
import game.entity.map.MapGame;

public class PlayerGame extends GameActorAnimation implements InputProcessor {

    private CameraKeys[] camKey;

    private MapGame map;

    public PlayerGame(MapGame map){
        this.setAnimation("assets/entities/players/$Lanto181.png",3,4,(float)0.4,3,4,0);
        camKey = CameraKeys.values();
        this.setKeyFrame(1);
        this.setAnimationPaused(true);
        this.map = map;
    }

    @Override
    public boolean keyDown(int i) {
        float x = this.getX();
        float y = this.getY();

        if(this.isAnimationPaused()){
            if (camKey[0].isKey(i)){
                this.setFacedDirection(Direction.LEFT);
                if(map.isWalkable(x - 20,y,this)){
                    this.setPosition(x - 20 ,y);
                    this.setKeyFrame(3);
                    this.setAnimationPaused(false);
                }
            }else if (camKey[1].isKey(i)){
                this.setFacedDirection(Direction.RIGHT);
                if(map.isWalkable(x + 20,y,this)){
                    this.setPosition(x + 20 ,y);
                    this.setKeyFrame(6);
                    this.setAnimationPaused(false);
                }
            }else if (camKey[2].isKey(i)){
                this.setFacedDirection(Direction.BACK);
                if (map.isWalkable(x,y + 20,this)){
                    this.setPosition(x ,y + 20);
                    this.setKeyFrame(9);
                    this.setAnimationPaused(false);
                }
            }else if (camKey[3].isKey(i)){
                if (map.isWalkable(x,y - 20,this)){
                    this.setPosition(x ,y - 20);
                    this.setKeyFrame(0);
                    this.setAnimationPaused(false);
                }
            }
        }
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

package game.entity;

import api.entity.actor.GameActorAnimation;
import api.enums.Direction;
import api.enums.keys.CameraKeys;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import game.entity.map.MapGame;

public class PlayerGame extends GameActorAnimation implements InputProcessor {

    private MapGame map;

    public static final int SPEED = 10;

    public PlayerGame(MapGame map){
        this.setAnimationPaused(true);
        this.map = map;
    }

    /**
     * Méthode qui permet de centrer la caméra sur le joueur
     */
    public void center(){
        /*Camera cam = this.getStage().getCamera();
        cam.position.x = this.getX();
        cam.position.y = this.getY();*/
    }

    @Override
    public boolean keyDown(int i) {
        float x = this.getX();
        float y = this.getY();

        if (CameraKeys.CAMERA_LEFT.isKey(i)){
            if(map.isWalkable(x - PlayerGame.SPEED,y,this)){
                this.setPosition(x - PlayerGame.SPEED ,y);

                if(isAnimationPaused() || this.facedDirection != Direction.LEFT){
                    this.setKeyFrame(3);
                }
                this.setFacedDirection(Direction.LEFT);
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_RIGHT.isKey(i)){
            if(map.isWalkable(x + PlayerGame.SPEED,y,this)){
                this.setPosition(x + PlayerGame.SPEED ,y);
                if(isAnimationPaused()|| this.facedDirection != Direction.RIGHT){
                    this.setKeyFrame(6);
                }
                this.setFacedDirection(Direction.RIGHT);
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_UP.isKey(i)){
            if (map.isWalkable(x,y + PlayerGame.SPEED,this)){
                this.setPosition(x ,y + PlayerGame.SPEED);
                if(isAnimationPaused() || this.facedDirection != Direction.TOP){
                    this.setKeyFrame(9);
                }
                this.setFacedDirection(Direction.TOP);
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_DOWN.isKey(i)){
            if (map.isWalkable(x,y - PlayerGame.SPEED,this)){
                this.setPosition(x ,y - PlayerGame.SPEED);
                if(isAnimationPaused() || this.facedDirection != Direction.BOTTOM){
                    this.setKeyFrame(0);
                }
                this.setFacedDirection(Direction.BOTTOM);
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }

        if (Input.Keys.E == i || Input.Keys.ENTER == i){
            //interaction
            System.out.println("interact");
        }
        return false;
    }

    @Override
    public void act(float delta){
        super.act(delta);

        if (this.facedDirection == Direction.LEFT){
            if (this.getKeyFrameIndex() > 5){
                this.setKeyFrame(4);
                this.setAnimationPaused(true);
            }
            if (isAnimationPaused()){
                this.setKeyFrame(5);
            }
        }

        if (this.facedDirection == Direction.RIGHT){
            if (this.getKeyFrameIndex() > 7){
                this.setKeyFrame(6);
                this.setAnimationPaused(true);
            }
            if (isAnimationPaused()){
                this.setKeyFrame(8);
            }
        }

        if (this.facedDirection == Direction.BOTTOM){
            if (this.getKeyFrameIndex() > 1){
                this.setKeyFrame(0);
                this.setAnimationPaused(true);
            }
            if (isAnimationPaused()){
                this.setKeyFrame(2);
            }
        }

        if(this.facedDirection == Direction.TOP){
            if ( this.getKeyFrameIndex() > 10){
                this.setKeyFrame(9);
                this.setAnimationPaused(true);
            }
            if (isAnimationPaused()){
                this.setKeyFrame(11);
            }
        }
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

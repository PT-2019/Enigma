package general.entities.players;

import api.libgdx.actor.GameActorAnimation;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import datas.Direction;
import datas.keys.CameraKeys;
import general.map.MapGame;

public class PlayerGame extends GameActorAnimation implements InputProcessor {

    private MapGame map;

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
            this.setFacedDirection(Direction.LEFT);
            if(map.isWalkable(x - 15,y,this)){
                this.setPosition(x - 15 ,y);

                if(isAnimationPaused()){
                    this.setKeyFrame(3);
                }
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_RIGHT.isKey(i)){
            this.setFacedDirection(Direction.RIGHT);
            if(map.isWalkable(x + 5,y,this)){
                this.setPosition(x + 5 ,y);
                if(isAnimationPaused()){
                    this.setKeyFrame(6);
                }
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_UP.isKey(i)){
            this.setFacedDirection(Direction.TOP);
            if (map.isWalkable(x,y + 15,this)){
                this.setPosition(x ,y + 15);
                if(isAnimationPaused()){
                    this.setKeyFrame(9);
                }
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_DOWN.isKey(i)){
            this.setFacedDirection(Direction.BOTTOM);
            if (map.isWalkable(x,y - 15,this)){
                this.setPosition(x ,y - 15);
                if(isAnimationPaused()){
                    this.setKeyFrame(0);
                }
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
            if ( this.getKeyFrameIndex() > 11){
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

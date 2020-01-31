package general.entities.players;

import api.libgdx.actor.GameActorAnimation;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import datas.Direction;
import datas.keys.CameraKeys;
import general.map.GameMap;

/**
 * Cette classe permet de déplacer le joueur et d'actionner son animation
 * @see GameActorAnimation
 */
public class PlayerGame extends GameActorAnimation implements InputProcessor {

    /**
     * La map dans la laquelle est placé le joueur
     */
    private GameMap map;

    /**
     * Vitesse de déplacement du personnage
     */
    public static final int SPEED = 10;

    /**
     *
     * @param map
     */
    public PlayerGame(GameMap map){
        this.setAnimationPaused(true);
        this.map = map;
    }

    /**
     * Méthode pour centrer la caméra du stage qui a le player sur le personnage
     */
    public void center(){
        Camera cam = this.getStage().getCamera();
        cam.translate(-cam.position.x,-cam.position.y,0);
    }

    /**
     * Actionné lorsqu'une touche est appuyé
     * @param i
     * @return
     */
    @Override
    public boolean keyDown(int i) {
        //position actuelle
        float x = this.getX();
        float y = this.getY();

        if (CameraKeys.CAMERA_LEFT.isKey(i)){
            //on vérifie que la case où l'on compte se rendre est disponible
            if(map.isWalkable(x - PlayerGame.SPEED,y,this)){
                this.setPosition(x - PlayerGame.SPEED ,y);
                //pour changer de sprite proprement
                if(isAnimationPaused() || this.facedDirection != Direction.LEFT){
                    this.setKeyFrame(3);
                }
                //pour centrer la caméra sur le personnage
                this.getStage().getCamera().translate(-10,0,0);

                this.setFacedDirection(Direction.LEFT);
                //l'animation n'est plus en pause pour la faire tourner
                this.setAnimationPaused(false);
                this.setAnimationLooping(true);
            }
        }else if (CameraKeys.CAMERA_RIGHT.isKey(i)){
            if(map.isWalkable(x + PlayerGame.SPEED,y,this)){
                this.setPosition(x + PlayerGame.SPEED ,y);
                if(isAnimationPaused()|| this.facedDirection != Direction.RIGHT){
                    this.setKeyFrame(6);
                }
                this.getStage().getCamera().translate(10,0,0);
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
                this.getStage().getCamera().translate(0,10,0);
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
                this.getStage().getCamera().translate(0,-10,0);
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

    /**
     * Méthode qui est appelé par le stage associé, permet d'afficher les animations
     * @param delta
     */
    @Override
    public void act(float delta){
        super.act(delta);

        if (this.facedDirection == Direction.LEFT){
            //on controle les sprites affichés
            if (this.getKeyFrameIndex() > 5){
                this.setKeyFrame(4);
                //on met en pause
                this.setAnimationPaused(true);
                //on met un sprite immobile
                this.setKeyFrame(5);
            }
        }

        if (this.facedDirection == Direction.RIGHT){
            if (this.getKeyFrameIndex() > 7){
                this.setKeyFrame(6);
                this.setAnimationPaused(true);
                this.setKeyFrame(8);
            }
        }

        if (this.facedDirection == Direction.BOTTOM){
            if (this.getKeyFrameIndex() > 1){
                this.setKeyFrame(0);
                this.setAnimationPaused(true);
                this.setKeyFrame(2);
            }
        }

        if(this.facedDirection == Direction.TOP){
            if ( this.getKeyFrameIndex() > 10){
                this.setKeyFrame(9);
                this.setAnimationPaused(true);
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

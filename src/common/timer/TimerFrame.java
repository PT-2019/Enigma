package common.timer;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Classe qui gère le timer du jeu
 */
public class TimerFrame extends Window {

    private static String SKIN_PATH = "assets/files/atlas/uiskin2.json";

    private static String ATLAS_PATH = "assets/files/atlas/uiskin.atlas";

    private float secondTimer;

    private float minuteTimer;

    /**
     * On retient la dernière valeur où on a rafraichi le timer
     */
    private float period;
    /**
     * temps actuelle
     */
    private float currentTime;

    private Label time;

    public TimerFrame() {
        super("",LibgdxUtility.loadSkin(SKIN_PATH,ATLAS_PATH));

        this.setSize(Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/12);
        this.setPosition(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight());
        //on met un temps par default
        minuteTimer = 60f;
        secondTimer = 0f;
        period = 1f;
        time = new Label("",this.getSkin());
        this.updateTime();
        this.add(time).expand();
        this.setVisible(true);
    }

    public TimerFrame(float sec,float min){
        super("",LibgdxUtility.loadSkin(SKIN_PATH,ATLAS_PATH));
        this.setSize(Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/12);
        this.setPosition(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight());
        minuteTimer = min;
        secondTimer = sec;
        period = 1f;
        time = new Label("",this.getSkin());
        this.updateTime();
        this.add(time).expand();
        this.setVisible(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch,parentAlpha);

        currentTime += Gdx.graphics.getRawDeltaTime();
        if (currentTime > period){
            currentTime -= period;

            if (secondTimer == 0){
                secondTimer = 59;
                minuteTimer--;
            }else {
                secondTimer--;
            }
            this.updateTime();
        }
    }

    private void updateTime(){
        time.setText((int)minuteTimer + ":" + (int)secondTimer);
    }
}

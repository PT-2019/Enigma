package game.screens;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Ecran de fin de jeu
 */
public class EndScreen extends Window {

    private static String SKIN_PATH = "assets/files/atlas/uiskin2.json";

    private static String ATLAS_PATH = "assets/files/atlas/uiskin.atlas";

    private static String RETURN = "Retour au menu";

    public EndScreen() {
        super("", LibgdxUtility.loadSkin(SKIN_PATH,ATLAS_PATH));

        this.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/4);
        this.setPosition((Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/3)/2,Gdx.graphics.getHeight()/2);
        this.setVisible(false);
    }

    public void showVictory(){
        this.clear();
        this.add(new Label("Victoire !",getSkin())).expand();
        this.row();
        this.add(new TextButton(RETURN,getSkin()));
        this.setVisible(true);
    }

    public void showGameOver(){
        this.clear();
        this.add(new Label("GameOver...",getSkin())).expand();
        this.row();
        this.add(new TextButton(RETURN,getSkin()));
        this.setVisible(true);
    }
}

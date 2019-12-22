package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import editor.map.Case;
import editor.map.Room;

import java.awt.*;
import java.util.Map;

public class CollisionView extends ShapeRenderer {

    private boolean visible;

    private int tile;

    private int heigth;

    private int width;

    private Case[] cases;


    public CollisionView(Case[] c, int t,int width,int heigth){
        visible = false;
        cases = c;
        this.width = width;
        this.heigth = heigth;
        tile = t;
    }

    public void draw(){
        if (visible){
            this.begin(ShapeType.Filled);
            this.setColor(1,0,0,0.5f);
            for (int i = 0; i < heigth ; i++) {
                for (int j = 0; j < width; j++) {
                    if (!cases[i*width+j].isWalkable()){
                        int y = (tile*(heigth-1))-i*tile;
                        this.rect(j*tile,y,tile,tile);
                    }
                }
            }
            this.end();
        }
    }

    public void setVisible(boolean b){
        visible = b;
    }
}

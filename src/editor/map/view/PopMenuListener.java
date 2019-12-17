package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopMenuListener implements ItemListener, ActionListener {

    private RoomView view;

    /**
       Camera qui se base sur la taille de la map, permet d'avoir le point
       de vue de toute la map
     */
    private Camera camera;

    /**
     * Largeur de la caméra sans le zoom du jeu
     */
    private float viewportWidth;

    /**
     * Longeur camera sans zoom du jeu
     */
    private float viewportHeight;

    private CollisionView collision;

    public PopMenuListener(RoomView v,Camera cam,CollisionView col){
        view = v;
        camera = cam;
        collision = col;

        viewportWidth = v.getTile();
        viewportHeight =  v.getHeightSize();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBoxMenuItem tmp = (JCheckBoxMenuItem) e.getItem();
        if(tmp.getActionCommand().equals("Afficher")){
            if (e.getStateChange() == ItemEvent.SELECTED) {
                view.setVisible(true);
            }else{
                view.setVisible(false);
            }
        }else{
            if (e.getStateChange() == ItemEvent.SELECTED) {
                collision.setVisible(true);
            }else{
                collision.setVisible(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Zoom du jeu")){
            ((OrthographicCamera) camera).setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            ((OrthographicCamera) camera).zoom = 1;
        }else{
            System.out.println(viewportWidth);
            System.out.println(viewportHeight);
            ((OrthographicCamera) camera).setToOrtho(false, viewportWidth,viewportHeight);
            ((OrthographicCamera) camera).zoom = 1;
        }
    }
}

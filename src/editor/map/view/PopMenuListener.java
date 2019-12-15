package editor.map.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopMenuListener implements ItemListener, ActionListener {

    private RoomView view;

    private Camera camera;

    private float zoom;

    private CollisionView collision;

    public PopMenuListener(RoomView v,Camera cam,CollisionView col){
        view = v;
        camera = cam;
        zoom = ((OrthographicCamera) camera).zoom;
        collision = col;
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
            ((OrthographicCamera) camera).zoom = zoom;
        }
    }
}

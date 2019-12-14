package editor.map.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopMenuListener implements ItemListener, ActionListener {

    private RoomView view;

    private Camera camera;

    private float zoom;

    public PopMenuListener(RoomView v,Camera cam){
        view = v;
        camera = cam;
        zoom = ((OrthographicCamera) camera).zoom;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.setVisible(true);
        }else{
            view.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Zoom du jeu")){
            ((OrthographicCamera) camera).zoom = zoom;
        }
    }
}

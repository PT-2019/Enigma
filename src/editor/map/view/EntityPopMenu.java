package editor.map.view;

import com.badlogic.gdx.graphics.Camera;
import editor.map.view.PopMenuListener;
import editor.map.view.*;

import javax.swing.*;

public class EntityPopMenu extends JPopupMenu {

    public EntityPopMenu(RoomView v, Camera cam){
        JMenu zoom = new JMenu("Zoom");
        JMenu eng = new JMenu("Enigme");
        JMenu room = new JMenu("Rooms");
        JMenuItem gameZoom = new JMenuItem("Zoom du jeu");
        JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
        this.add(zoom);
        this.add(eng);
        this.add(room);
        room.add(dips);
        zoom.add(gameZoom);

        PopMenuListener roomListener = new PopMenuListener(v,cam);
        dips.addItemListener(roomListener);
        gameZoom.addActionListener(roomListener);
    }
}

package editor.entity.dispose;

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
        JMenuItem map= new JMenuItem("Voir toute la map");
        JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
        this.add(zoom);
        this.add(eng);
        this.add(room);
        room.add(dips);
        zoom.add(gameZoom);
        zoom.add(map);

        PopMenuListener roomListener = new PopMenuListener(v,cam);
        dips.addItemListener(roomListener);
        gameZoom.addActionListener(roomListener);
        map.addActionListener(roomListener);
    }
}

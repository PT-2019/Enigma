package editor.entity.dispose;

import editor.map.view.PopMenuListener;
import editor.map.view.*;

import javax.swing.*;

public class EntityPopMenu extends JPopupMenu {

    public EntityPopMenu(RoomView v){
        JMenu test = new JMenu("test");
        JMenu eng = new JMenu("Enigme");
        JMenu room = new JMenu("Rooms");
        JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
        this.add(test);
        this.add(eng);
        this.add(room);
        room.add(dips);

        PopMenuListener roomListener = new PopMenuListener(v);
        dips.addItemListener(roomListener);
    }
}

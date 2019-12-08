package editor.entity.dispose;

import javax.swing.*;

public class EntityPopMenu extends JPopupMenu {

    public EntityPopMenu(){
        JMenu test = new JMenu("test");
        JMenu eng = new JMenu("Enigme");
        JMenu room = new JMenu("Rooms");

        this.add(test);
        this.add(eng);
        this.add(room);
    }
}

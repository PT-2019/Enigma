package editor.map.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopMenuListener implements ItemListener {

    private RoomView view;

    public PopMenuListener(RoomView v){
        view = v;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.setVisible(true);
        }else{
            view.setVisible(false);
        }
    }
}

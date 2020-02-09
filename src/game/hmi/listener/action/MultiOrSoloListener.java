package game.hmi.listener.action;

import game.hmi.content.Create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiOrSoloListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(Create.getInstance().getState() == Create.SOLO_STATE)
            Create.getInstance().refresh(Create.MULTI_STATE);
        else if(Create.getInstance().getState() == Create.MULTI_STATE)
            Create.getInstance().refresh(Create.SOLO_STATE);
    }
}

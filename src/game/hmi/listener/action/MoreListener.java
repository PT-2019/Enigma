package game.hmi.listener.action;

import game.hmi.content.SeeMore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreListener implements ActionListener {
    private SeeMore more;

    public MoreListener(SeeMore more){
        this.more = more;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.more.show(true);
    }
}

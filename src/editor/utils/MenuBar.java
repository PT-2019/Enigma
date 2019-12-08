package editor.utils;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private UI ui;

    public MenuBar(){
        super();
        this.ui = new UI(UI.DEFAULT_ENIGMA_UI);
        this.setBackground(this.ui.getBackground());
        this.setForeground(this.ui.getForeground());
        this.setBorder(this.ui.getBorder());
    }

    public MenuBar(UI ui){
        super();
        this.ui = ui;
        this.setBackground(this.ui.getBackground());
        this.setForeground(this.ui.getForeground());
        this.setBorder(this.ui.getBorder());
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public UI getUi(){
        return this.ui;
    }
}

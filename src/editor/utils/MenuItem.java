package editor.utils;

import javax.swing.*;

public class MenuItem extends JMenuItem {
    private UI ui;

    public MenuItem(String title){
        super(title);
        this.ui = new UI(UI.DEFAULT_ENIGMA_UI);
        this.setBackground(this.ui.getBackground());
        this.setForeground(this.ui.getForeground());
        this.setBorder(this.ui.getBorder());
    }

    public MenuItem(String title, UI ui){
        super(title);
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

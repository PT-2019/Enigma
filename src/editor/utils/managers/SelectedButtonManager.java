package editor.utils.managers;

import editor.utils.EnigmaButton;

public class SelectedButtonManager {

    private EnigmaButton selected;

    public void add(EnigmaButton b){
        b.addMouseListener(new SelectedManager(this));
    }

    public void setSelected(EnigmaButton b) {
        if (this.selected != null){
            this.selected.getButtonUI().setIsSelected(false);
            this.selected.repaint();
        }
        b.getButtonUI().setIsSelected(true);
        b.repaint();
        this.selected = b;
    }

    public EnigmaButton getSelected(){
        return this.selected;
    }
}

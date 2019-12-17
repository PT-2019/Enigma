package editor.utils;

import editor.utils.managers.SelectedManager;

import java.util.ArrayList;

public class EnigmaSelectedButtonManager {

    private EnigmaButton selected;

    public void add(EnigmaButton b){
        b.addMouseListener(new SelectedManager(this));
    }

    public void setSelected(EnigmaButton b){
        this.selected.getButtonUI().setIsSelected(false);
        this.selected.revalidate();
        b.getButtonUI().setIsSelected(true);
        b.revalidate();
        this.selected = b;
    }

    public EnigmaButton getSelected(){
        return this.selected;
    }
}

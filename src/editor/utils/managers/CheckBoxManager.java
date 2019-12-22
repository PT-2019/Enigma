package editor.utils.managers;

import editor.utils.EnigmaButton;
import editor.utils.ui.EnigmaButtonUI;

import java.util.ArrayList;

public class CheckBoxManager implements MultipleButtonManager {

    private ArrayList<EnigmaButton> selected;

    public CheckBoxManager(){
        this.selected = new ArrayList<>();
    }

    @Override
    public void add(EnigmaButton b) {
        if(b.getButtonUI() == null) b.setButtonUI(new EnigmaButtonUI());
        b.addMouseListener(new SelectedManager(this));
    }

    @Override
    public void setSelected(EnigmaButton b){
        if(b.getButtonUI().isSelected()){
            b.getButtonUI().setIsSelected(false);
            b.repaint();
            this.selected.remove(b);
        }else{
            b.getButtonUI().setIsSelected(true);
            b.repaint();
            this.selected.add(b);
        }
    }

    @Override
    public EnigmaButton[] getSelected() {
        return (EnigmaButton[]) this.selected.toArray();
    }
}
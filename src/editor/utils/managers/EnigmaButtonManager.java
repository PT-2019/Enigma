package editor.utils.managers;

import editor.utils.EnigmaButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaButtonManager implements MouseListener {

    private EnigmaButton button;

    public EnigmaButtonManager(EnigmaButton button){
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent){}

    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.button.getButtonUI() != null) this.button.getButtonUI().setIsHovered(true);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.button.getButtonUI() != null) this.button.getButtonUI().setIsHovered(false);
    }
}

package editor.hud.managers;

import editor.hud.EnigmaTextArea;

import java.awt.event.*;

public class EnigmaTextAreaManager implements MouseListener, FocusListener {

    private EnigmaTextArea textArea;

    public EnigmaTextAreaManager(EnigmaTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent){}

    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsHovered(true);
            this.textArea.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsHovered(false);
            this.textArea.repaint();
        }
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        this.textArea.repaint();
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.textArea.repaint();
    }
}

package editor.utils.managers;

import editor.utils.EnigmaTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaTextAreaManager implements MouseListener, ActionListener {

    private EnigmaTextArea textArea;
    private Timer focusTimer;

    public EnigmaTextAreaManager(EnigmaTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.repaint();
            this.focusTimer = new Timer(100,this);
            this.focusTimer.setRepeats(true);
            this.focusTimer.start();
        }
    }

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
    public void actionPerformed(ActionEvent actionEvent) {
        if(!this.textArea.hasFocus()) this.focusTimer.stop();
        this.textArea.repaint();
    }
}

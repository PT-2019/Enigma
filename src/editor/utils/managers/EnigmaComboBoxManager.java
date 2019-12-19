package editor.utils.managers;

import editor.utils.EnigmaComboBox;
import editor.utils.EnigmaTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaComboBoxManager implements MouseListener, ActionListener {

    private EnigmaComboBox comboBox;
    private Timer focusTimer;

    public EnigmaComboBoxManager(EnigmaComboBox comboBox){
        this.comboBox = comboBox;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        if(this.comboBox.getComboBoxUI() != null){
            this.comboBox.repaint();
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
        if(this.comboBox.getComboBoxUI() != null){
            this.comboBox.getComboBoxUI().setIsHovered(true);
            this.comboBox.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.comboBox.getComboBoxUI() != null){
            this.comboBox.getComboBoxUI().setIsHovered(false);
            this.comboBox.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!this.comboBox.hasFocus()) this.focusTimer.stop();
        this.comboBox.repaint();
    }
}

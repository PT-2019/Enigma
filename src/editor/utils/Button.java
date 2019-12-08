package editor.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JLabel {
    
    private UI ui;
    private boolean onIt;
    private boolean clickable;
    private ActionListener actionListener;

    public Button(String title){
        super(title);
        this.setOpaque(true);
        this.onIt = false;
        this.clickable = true;
        this.setHorizontalAlignment(JLabel.CENTER);
        this.addMouseListener(new ButtonManager(this));
        this.ui = new UI(UI.DEFAULT_ENIGMA_UI);
        this.setBackground(this.ui.getBackground());
        this.setForeground(this.ui.getForeground());
        this.setBorder(this.ui.getBorder());
    }

    public Button(String title, UI ui){
        super(title);
        this.setOpaque(true);
        this.onIt = false;
        this.clickable = true;
        this.setHorizontalAlignment(JLabel.CENTER);
        this.ui = ui;
        this.addMouseListener(new ButtonManager(this));
        this.setBackground(this.ui.getBackground());
        this.setForeground(this.ui.getForeground());
        this.setBorder(this.ui.getBorder());
    }

    public void setClickable(boolean clickable){
        this.clickable = clickable;
    }

    public void addActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public UI getUi(){
        return this.ui;
    }

    void clicked(){
        if(this.clickable) {
            this.actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, this.getText()));
        }
    }

     void exited(){
         if(this.clickable) {
             this.onIt = false;
             this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             this.setBackground(this.ui.getBackground());
             this.setForeground(this.ui.getForeground());
             this.setBorder(this.ui.getBorder());
         }
    }

    void hovered(){
        if(this.clickable){
            this.onIt = true;
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.setBackground(this.ui.getHoveredBackground());
            this.setForeground(this.ui.getHoveredForeground());
            this.setBorder(this.ui.getHoveredBorder());
        }
    }

    void pressed(){
        if(this.clickable) {
            this.setBackground(this.ui.getPressedBackground());
            this.setForeground(this.ui.getPressedForeground());
            this.setBorder(this.ui.getPressedBorder());
        }
    }

    void released(){
        if(this.clickable) {
            if (this.onIt) {
                this.setBackground(this.ui.getHoveredBackground());
                this.setForeground(this.ui.getHoveredForeground());
                this.setBorder(this.ui.getHoveredBorder());
            } else {
                this.setBackground(this.ui.getBackground());
                this.setForeground(this.ui.getForeground());
                this.setBorder(this.ui.getBorder());
            }
        }
    }
}

class ButtonManager implements MouseListener {

    private Button button;

    public ButtonManager(Button button){
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.button.clicked();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.button.pressed();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        this.button.released();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.button.hovered();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.button.exited();
    }
}

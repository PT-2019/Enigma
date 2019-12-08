package editor.utils;

import editor.utils.ui.ButtonUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {

    private ButtonUI ui;

    public Button(){
        super();
        this.addMouseListener(new ButtonManager(this));
        this.setOpaque(true);
    }

    public Button(String title){
        super(title);
        this.addMouseListener(new ButtonManager(this));
        this.setOpaque(true);
    }

    public void setButtonUI(ButtonUI ui) {
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public ButtonUI getButtonUI(){
        return this.ui;
    }
}

class ButtonManager implements MouseListener {

    private Button button;

    public ButtonManager(Button button){
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

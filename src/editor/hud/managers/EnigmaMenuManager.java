package editor.hud.managers;

import editor.hud.EnigmaMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaMenuManager implements MouseListener {

    private EnigmaMenu menu;

    public EnigmaMenuManager(EnigmaMenu menu){
        this.menu = menu;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.menu.getMenuUI() != null){
            this.menu.getMenuUI().setIsHovered(true);
            this.menu.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.menu.getMenuUI() != null){
            this.menu.getMenuUI().setIsHovered(false);
            this.menu.repaint();
        }
    }
}

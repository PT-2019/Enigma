package editor.utils.managers;

import editor.utils.MenuItem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuItemManager implements MouseListener {

    private MenuItem menu;

    public MenuItemManager(MenuItem menu){
        this.menu = menu;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent){}

    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.menu.getMenuItemUI() != null){
            this.menu.getMenuItemUI().setIsHovered(true);
            this.menu.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.menu.getMenuItemUI() != null){
            this.menu.getMenuItemUI().setIsHovered(false);
            this.menu.repaint();
        }
    }
}

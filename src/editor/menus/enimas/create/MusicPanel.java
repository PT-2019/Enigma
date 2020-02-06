package editor.menus.enimas.create;

import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.listeners.MusicListener;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe est a utiliser uniquement pour le choix de musique
 */
public class MusicPanel extends JPanel {

    private JLabel link;

    public MusicPanel(String text, JRadioButton button){
        this.link = new JLabel(text);

        this.link.addMouseListener(new MusicListener());
        this.setLayout(new GridLayout(1,2));
        this.add(button);
    }

    public void dispLink(){
        this.add(link);
        this.revalidate();
    }

    public void remove(){
        this.remove(link);
        this.revalidate();
    }

}

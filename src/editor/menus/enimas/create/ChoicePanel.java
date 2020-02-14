package editor.menus.enimas.create;

import editor.menus.enimas.create.listeners.AnswerListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panneau permettant de faire des choix pour une opération ou autre
 */
public class ChoicePanel extends JPanel {

    private JLabel link;

    private ConditionPanel parent;

    public ChoicePanel (String text, JRadioButton button, ConditionPanel parent){
        this.link = new JLabel(text);
        this.link.addMouseListener(new AnswerListener());
        this.setLayout(new GridLayout(1,2));
        //Le bouton n'as plus de MouseListener pour une raison inconnu
        //donc on lui en remet un
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setSelected(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(button);
        this.parent = parent;
    }

    public void dispLink(){
        this.add(link);
        this.revalidate();
    }

    public void remove(){
        this.remove(link);
        this.revalidate();
    }

    @Override
    public ConditionPanel getParent() {
        return parent;
    }
}

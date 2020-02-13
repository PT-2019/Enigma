package editor.menus.enimas.create;

import common.enigmas.condition.Condition;
import editor.menus.enimas.create.listeners.AnswerListener;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau permettant de faire des choix pour une opération ou autre
 */
public class ChoicePanel extends JPanel {

    private JLabel link;

    private ConditionPanel parent;

    public ChoicePanel (String text, JRadioButton button, ConditionPanel parent){
        link = new JLabel(text);

        this.link.addMouseListener(new AnswerListener());
        this.setLayout(new GridLayout(1,2));
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

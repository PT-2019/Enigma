package editor.enigma.create.listeners;

import editor.enigma.Advice;
import editor.enigma.create.EnigmaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class ClueListener implements ActionListener {

    private EnigmaView parent;

    private JTextArea cluefield;

    private JTextField timeField;

    public ClueListener(EnigmaView parent, JTextField timeField,JTextArea cluefield) {
        this.parent = parent;
        this.cluefield = cluefield;
        this.timeField = timeField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int time = Integer.parseInt(timeField.getText());
        parent.getEnigma().addAdvice(new Advice(cluefield.getText(),time));
        Iterator<Advice> it = parent.getEnigma().getAllAdvices();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        CardLayout layout = parent.getCardLayout();
        layout.previous(parent.getPanel());
    }
}

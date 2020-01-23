package editor.enigma.create.enigma;

import api.entity.GameObject;
import editor.enigma.Advice;
import editor.enigma.create.listeners.ClueListener;
import editor.view.cases.panel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Interface graphique permettant d'ajouter des indices
 */
public class CluePanel extends EnigmaPanel {

    public CluePanel(EnigmaView parent){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        MenuPanel menu = new MenuPanel("\nAjouter une Condition \n à l'énigme","zeafazefzfae",parent);

        JLabel clue = new JLabel("Indice :");
        JTextArea cluefield = new JTextArea();
        JLabel time = new JLabel("Temps en minute apparition de l'indice :");
        JTextField timeField= new JTextField();
        JButton submit = new JButton("Ajouter");

        submit.addActionListener(new ClueListener(parent,timeField,cluefield));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		this.add(menu, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(clue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,20,0,20);
        this.add(cluefield,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(time,gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50,20,50,20);
        this.add(timeField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(100,20,100,20);
        this.add(submit,gbc);
    }

    @Override
    public void update(GameObject object) {}
}

package editor.enigma.create;

import api.entity.GameObject;

import javax.swing.*;
import java.awt.*;

/**
 * Menu des enigmes
 */
public class EnigmaMenu extends EnigmaPanel {

    public EnigmaMenu(EnigmaView parent){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel titl = new JLabel("Titre : ");
        JTextField title = new JTextField();
        JLabel desc = new JLabel("Description : ");
        JTextArea description = new JTextArea();
        JButton submit = new JButton("Sauvegarder Enigme");
        JButton clue = new JButton("Ajouter un indice");
        JButton condition = new JButton("Ajouter une étape");
        JButton operation = new JButton("Ajouter une conséquence");
        NavigationEnigmaListener buttonListener = new NavigationEnigmaListener(title,description);
        clue.addActionListener(buttonListener);
        condition.addActionListener(buttonListener);
        operation.addActionListener(buttonListener);
        submit.addActionListener(buttonListener);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(titl,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(title,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(desc,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(description,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(clue,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(condition,gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(operation,gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(submit,gbc);
        this.parent = parent;
    }

    @Override
    public void update(GameObject object) {

    }
}

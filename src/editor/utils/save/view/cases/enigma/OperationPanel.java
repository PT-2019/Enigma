package editor.utils.save.view.cases.enigma;

import editor.utils.save.view.cases.panel.MenuPanel;
import editor.utils.save.view.listeners.OperationListener;
import javax.swing.*;
import java.awt.*;

/**
 * Interface graphique de la création des opérations
 */
public class OperationPanel extends EnigmaPanel {

    /**
     * Groupes de bouton
     */
    private ButtonGroup groups;

    private OperationListener listener;

    public OperationPanel(EnigmaView parent){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        MenuPanel menu = new MenuPanel("\nAjouter une Condition \n à l'énigme","zeafazefzfae");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(menu,gbc);
        groups = new ButtonGroup();
        JRadioButton activated = new JRadioButton("Give");
        JRadioButton hands = new JRadioButton("Summon");
        JRadioButton inventory = new JRadioButton("Unlock");
        //on ajoute les boutons au groupe
        groups.add(activated);
        groups.add(hands);
        groups.add(inventory);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        //ajoute les boutons au panneau
        panel.add(activated);
        panel.add(hands);
        panel.add(inventory);
        //listener pour les boutons
        listener = new OperationListener(parent,this);
        activated.addItemListener(listener);
        hands.addItemListener(listener);
        inventory.addItemListener(listener);
        JButton submit = new JButton("Valider");
        submit.addActionListener(listener);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(panel,gbc);
        JLabel selection = new JLabel("Veuillez sélectionner un objet sur la carte : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(selection,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(250,20,150,20);
        this.add(submit,gbc);
    }
}

package editor.enigma.create.enigma;

import api.entity.GameObject;
import editor.enigma.create.listeners.OperationListener;
import editor.view.cases.panel.MenuPanel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Interface graphique de la création des opérations
 */
public class OperationPanel extends EnigmaPanel {

	/**
	 * Groupes de bouton
	 */
	private ButtonGroup groups;

	private OperationListener listener;

    /**
     * Les informations sur l'entité sur laquelle l'opération sera faite
     */
    private JLabel entityName;

    public OperationPanel(EnigmaView parent){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        MenuPanel menu = new MenuPanel("\nAjouter une Condition \n à l'énigme","zeafazefzfae",parent);
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
        listener = new OperationListener(parent);
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

        entityName = new JLabel("Vous n'avez pas encore choisi d'entité");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(entityName,gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(250,20,150,20);
        this.add(submit,gbc);
    }

    @Override
    public void update(GameObject object) {
        listener.setGameObject(object);
        entityName.setText(object.toString());
    }
}

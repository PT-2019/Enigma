package editor.enigma.create.enigma;


import api.entity.GameObject;
import api.hud.ResetComponent;
import api.utils.Observer;
import editor.enigma.create.listeners.ConditionListener;
import editor.view.cases.panel.MenuPanel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Panneau qui affiche le menu pour créer une condition
 */
public class ConditionPanel extends EnigmaViewPanel implements Observer, ResetComponent {

	/**
	 * boolean pour savoir si on a afficher le panneau
	 */
	private boolean activateSearchItem;

	private JPanel optionPanel;

	private JPanel answerPanel;

	/**
	 * Groupes de bouton
	 */
	private ButtonGroup groups;

	private ConditionListener listener;

	private GridBagConstraints gbc;

	/**
	 * Les informations sur l'entité responsable de la condition
	 */
	private JLabel entityName;

	public ConditionPanel(EnigmaView parent) {
		super(parent);
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		MenuPanel menu = new MenuPanel("\nAjouter une Condition \n à l'énigme", "zeafazefzfae", parent, this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(menu, gbc);

		groups = new ButtonGroup();
		JRadioButton activated = new JRadioButton("Activé");
		JRadioButton answer = new JRadioButton("Réponse");
		JRadioButton hands = new JRadioButton("Posseder dans la main");
		JRadioButton inventory = new JRadioButton("Posseder dans l'inventaire");
		//on ajoute les boutons au groupe
		groups.add(activated);
		groups.add(answer);
		groups.add(hands);
		groups.add(inventory);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		//ajoute les boutons au panneau
		panel.add(activated);
		panel.add(answer);
		panel.add(hands);
		panel.add(inventory);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(panel, gbc);

		//listener pour les boutons
		listener = new ConditionListener(parent, this);
		activated.addItemListener(listener);
		answer.addItemListener(listener);
		hands.addItemListener(listener);
		inventory.addItemListener(listener);

		JButton submit = new JButton("Valider");
		submit.addActionListener(listener);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 20, 40, 20);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(submit, gbc);
		activateSearchItem = false;
		entityName = new JLabel("");
		optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout());
		optionPanel.add(entityName, BorderLayout.SOUTH);
	}

	/**
	 * Affiche le panneau lorsque l'utilisateur doit trouver une entité
	 */
	public void displaySearchItem() {
		if (answerPanel != null) {
			this.remove(answerPanel);
			answerPanel = null;
		}
		optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout());
		JLabel selection = new JLabel("Veuillez sélectionner un objet sur la carte : ");
		optionPanel.add(selection, BorderLayout.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		entityName = new JLabel("Vous n'avez pas encore choisi d'entité");
		optionPanel.add(entityName, BorderLayout.SOUTH);
		this.add(optionPanel, gbc);
		activateSearchItem = true;
		this.revalidate();
	}

	/**
	 * Affiche le panneau lorsque l'utilisateur doit taper la réponse de la question de l'enigme
	 */
	public void displayAnswer() {
		activateSearchItem = false;
		answerPanel = new JPanel();
		JLabel info = new JLabel("Taper la réponse : ");
		JTextArea answer = new JTextArea();
		answerPanel.setLayout(new GridLayout(2, 1));
		answerPanel.add(info);
		answerPanel.add(answer);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(answerPanel, gbc);
		this.revalidate();
	}

	public boolean isActivateSearchItem() {
		return activateSearchItem;
	}

	@Override
	public void update(GameObject g) {
		listener.setGameObject(g);
		if(g == null) entityName.setText("Vous n'avez pas encore choisi d'entité");
		else entityName.setText(g.toString());
	}

	@Override
	public void clean() {

	}

	@Override
	public void initComponent() {

	}
}

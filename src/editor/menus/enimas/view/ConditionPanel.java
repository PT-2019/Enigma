package editor.menus.enimas.view;

import api.hud.base.ResetComponent;
import api.utils.Observer;
import api.utils.Utility;
import editor.menus.enimas.listeners.ConditionListener;
import editor.popup.cases.panel.MenuPanel;
import game.dnd.DragAndDropBuilder;
import general.entities.GameObject;
import general.hud.EnigmaButton;
import general.hud.EnigmaLabel;
import general.hud.EnigmaPanel;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Panneau qui affiche le menu pour créer une condition
 */
public class ConditionPanel extends EnigmaViewPanel implements Observer<GameObject>, ResetComponent {

	public static final String NOT_AVAILABLE_OPERATION = "Opération non disponible";
	public static final String ASK_SELECT = "Veuillez sélectionner un objet (carte uniquement)";
	public static final String ASK_COND = "Veuillez sélectionner une condition.";
	public static final String NOT_AVAILABLE_CONDITION = "Opération non disponible";
	private static final String TITLE = "Ajouter une Condition à l'énigme";
	private static final String INVALID_ENTITY = "Entité Invalide. ";
	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	private final EnigmaLabel entityName, selection;
	/**
	 * Groupe des bouton de choix de l'opération
	 */
	private ButtonGroup groups;
	/**
	 * Observateur de ce menu
	 */
	private ConditionListener listener;

	/*/**
	 * boolean pour savoir si on a afficher le panneau
	 *
	private boolean activateSearchItem;
	private JPanel optionPanel;
	private JPanel answerPanel;
	private GridBagConstraints gbc;*
	*/

	public ConditionPanel(EnigmaView parent) {
		super(parent);
		this.groups = new ButtonGroup();
		this.listener = new ConditionListener(parent, this);

		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridLayout(Conditions.values().length, 1));

		for (Conditions cond : Conditions.values()) {
			JRadioButton r = new JRadioButton(cond.value);
			r.setToolTipText(cond.tooltip);
			r.setName(cond.name());
			//on ajoute les boutons au groupe
			groups.add(r);
			//ajoute les boutons au panneau
			panel.add(r);
			//listener pour les boutons
			r.addItemListener(this.listener);
		}

		EnigmaPanel p2 = new EnigmaPanel();
		p2.setLayout(new GridBagLayout());

		EnigmaButton submit = new EnigmaButton("Valider");
		submit.addActionListener(listener);
		selection = new EnigmaLabel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 0, 0, 0);
		p2.add(selection, gbc);

		entityName = new EnigmaLabel(ASK_COND);
		entityName.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		p2.add(entityName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 0, 0);
		p2.add(submit, gbc);

		this.setLayout(new BorderLayout());
		this.add(new MenuPanel(TITLE, "", parent, this), BorderLayout.NORTH);
		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(panelS, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);

		/*
		activateSearchItem = false;
		entityName = new JLabel("");
		optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout());
		optionPanel.add(entityName, BorderLayout.SOUTH);*/
	}

	/**
	 * Affiche le panneau lorsque l'utilisateur doit trouver une entité
	 */
	@Deprecated
	public void displaySearchItem() {
		/*if (answerPanel != null) {
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
		this.revalidate();*/
	}

	/**
	 * Affiche le panneau lorsque l'utilisateur doit taper la réponse de la question de l'enigme
	 */
	@Deprecated
	public void displayAnswer() {
		/*activateSearchItem = false;
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
		this.revalidate();*/
	}

	@Deprecated
	public boolean isActivateSearchItem() {
		//return activateSearchItem;
		return false;
	}

	@Override
	public void update(GameObject object) {
		if (!EnigmaView.isAvailable(this)) return;

		this.listener.setGameObject(object);
		JRadioButton currentButton = this.listener.getCurrentButton();

		String msg = "";
		Conditions operations = null;
		boolean wrong = false;

		if (currentButton == null) {
			msg += ASK_COND;
		} else {
			operations = Utility.stringToEnum(currentButton.getName(), Conditions.values());
			//s'il avait sélectionné une entité, on la vérifie
			if (object != null) {
				if (!operations.isValid(object)) {
					object = null;
					wrong = true;
				}
			}
		}

		if (object == null && wrong) {
			msg += INVALID_ENTITY;
			msg += operations.restrict;
			this.entityName.setText(msg);
		} else if (object == null && operations != null) {
			this.entityName.setText(ASK_SELECT);
		} else if (object != null) {
			msg += object.getReadableName() + " (id=" + object.getID() + ")";
			this.entityName.setText(msg);
		} else {
			this.entityName.setText(msg);
		}
	}

	@Override
	public void clean() {
		this.selection.setText("");
		this.entityName.setText(ASK_COND);
		this.listener.clean();
		this.groups.clearSelection();
		DragAndDropBuilder.setForPopup(null);
	}

	@Override
	public void initComponent() {
	}

	public EnigmaLabel getEntityName() {
		return this.entityName;
	}
}

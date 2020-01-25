package editor.enigma.create.enigma;

import api.entity.GameObject;
import api.entity.types.Living;
import api.entity.types.Lockable;
import api.entity.types.NeedContainer;
import api.hud.ResetComponent;
import api.utils.Observer;
import api.utils.Utility;
import editor.enigma.create.listeners.OperationListener;
import editor.entity.NPC;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.cases.panel.MenuPanel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Interface graphique de la création des opérations
 */
public class OperationPanel extends EnigmaViewPanel implements Observer, ResetComponent {

	public static final String NOT_SELECTED = "Vous n'avez pas encore choisi d'entité";
	public static final String ASK_SELECT = "Veuillez sélectionner un objet (carte ou menu) : ";
	private final EnigmaLabel selection;

	private enum Operations {
		GIVE("Give","Donne un object à l'utilisateur", "Objects uniquement (livre...)."),
		SUMMON("Summon", "Invoque une entité", "Seulement des personnages, pas de héros."),
		UNLOCK("Unlock", "Dévérouille un object", "Seulement un object décors fermable."),
		;

		final String name;
		final String tooltip;
		final String restrict;

		Operations(String name, String tooltip, String restrict) {
			this.name = name;
			this.tooltip = tooltip;
			this.restrict = restrict;
		}

		public boolean isValid(GameObject object) {
			if(this.equals(GIVE)){
				return (object instanceof NeedContainer);
			} else if(this.equals(SUMMON)){
				if(object instanceof Living){
					if(object instanceof NPC){
						return !((NPC) object).isHero();
					}
					return true;
				}
			} else if(this.equals(UNLOCK)){
				return (object instanceof Lockable);
			}


			return false;
		}
	}

	/**
	 * Groupes de bouton
	 */
	private ButtonGroup groups;

	private OperationListener listener;

	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	private EnigmaLabel entityName;

	public OperationPanel(EnigmaView parent) {
		super(parent);
		this.groups = new ButtonGroup();
		this.listener = new OperationListener(parent, this);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(Operations.values().length, 1));

		for (Operations op : Operations.values()) {
			JRadioButton r = new JRadioButton(op.name);
			r.setToolTipText(op.tooltip);
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
		gbc.insets = new Insets(15,0,0,0);
		p2.add(selection, gbc);

		entityName = new EnigmaLabel();
		entityName.getComponentUI().setAllForegrounds(Color.YELLOW,Color.YELLOW,Color.YELLOW);
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
		gbc.insets = new Insets(10,0,0,0);
		p2.add(submit, gbc);

		this.setLayout(new BorderLayout());
		this.add(new MenuPanel("Ajouter une Condition à l'énigme", "", parent, this), BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);
	}

	@Override
	public void initComponent() {
	}

	public void clean() {
		this.selection.setText("");
		this.entityName.setText("");
		this.listener.clean();
		this.groups.clearSelection();
	}

	@Override
	public void update(GameObject object) {
		listener.setGameObject(object);
		JRadioButton currentButton = listener.getCurrentButton();

		final String OP_MSG = "Sélectionnez une opération.";
		String msg = "";
		Operations operations = null;

		if(currentButton == null){
			msg += OP_MSG;
		} else {
			operations = Utility.stringToEnum(currentButton.getText(), Operations.values());
			if(!operations.isValid(object)){
				object = null;
			}
		}

		if(object == null){
			msg += "Entité Invalide. ";
			if(operations != null){
				msg += operations.restrict;
			}
			entityName.setText(msg);
		} else {
			msg += object.getReadableName()+" (id="+object.getID()+")";
			entityName.setText(msg);
		}
	}

	public EnigmaLabel getEntityName() {
		return entityName;
	}

	public EnigmaLabel getSelection() {
		return selection;
	}
}

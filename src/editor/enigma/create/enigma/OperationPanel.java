package editor.enigma.create.enigma;

import api.entity.GameObject;
import api.hud.ResetComponent;
import api.utils.Observer;
import api.utils.Utility;
import editor.enigma.create.listeners.OperationListener;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.cases.panel.MenuPanel;
import game.utils.DragAndDropBuilder;
import org.jetbrains.annotations.Nullable;

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
 * Interface graphique de la création des opérations
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0
 * @since 4.0
 */
public class OperationPanel extends EnigmaViewPanel implements Observer, ResetComponent {

	public static final String NOT_SELECTED = "Vous n'avez pas encore choisi d'entité";
	public static final String ASK_SELECT = "Veuillez sélectionner un objet (carte ou menu)";
	public static final String ASK_OP = "Veuillez sélectionner une opération.";
	private static final String INVALID_ENTITY = "Entité Invalide. ";
	private static final String TITLE = "Ajouter une Opération à l'énigme";
	public static final String NOT_AVAILABLE_OPERATION = "Opération non disponible";

	/**
	 * Groupe des bouton de choix de l'opération
	 */
	private ButtonGroup groups;

	/**
	 * Observateur de ce menu
	 */
	private OperationListener listener;

	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	private final EnigmaLabel entityName, selection;

	public OperationPanel(EnigmaView parent) {
		super(parent);
		this.groups = new ButtonGroup();
		this.listener = new OperationListener(parent, this);

		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridLayout(Operations.values().length, 1));

		for (Operations op : Operations.values()) {
			JRadioButton r = new JRadioButton(op.value);
			r.setToolTipText(op.tooltip);
			r.setName(op.name());
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

		entityName = new EnigmaLabel(ASK_OP);
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
		this.add(new MenuPanel(TITLE, "", parent, this), BorderLayout.NORTH);
		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(panelS, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);
	}

	@Override
	public void initComponent() {
	}

	/**
	 * Reset de toutes les informations pour la prochaine utilisation
	 * @since 5.0
	 */
	@Override
	public void clean() {
		this.selection.setText("");
		this.entityName.setText(ASK_OP);
		this.listener.clean();
		this.groups.clearSelection();
		DragAndDropBuilder.setForPopup(null);
	}

	/**
	 * Met a jour le panneau des opérations, si par exemple une entité a étée sélectionnée
	 * @param object une entité
	 * @since 4.0
	 */
	@Override
	public void update(@Nullable GameObject object) {
		if(!EnigmaView.isAvailable(this)) return;

		this.listener.setGameObject(object);
		JRadioButton currentButton = this.listener.getCurrentButton();

		String msg = "";
		Operations operations = null;
		boolean wrong = false;

		if(currentButton == null){
			msg += ASK_OP;
		} else {
			operations = Utility.stringToEnum(currentButton.getName(), Operations.values());
			//s'il avait sélectionné une entité, on la vérifie
			if(object != null) {
				if (!operations.isValid(object)) {
					object = null;
					wrong = true;
				}
			}
		}

		if(object == null && wrong){
			msg += INVALID_ENTITY;
			msg += operations.restrict;
			this.entityName.setText(msg);
		} else if(object == null && operations != null){
			this.entityName.setText(ASK_SELECT);
		} else if(object != null) {
			msg += object.getReadableName()+" (id="+object.getID()+")";
			this.entityName.setText(msg);
		} else {
			this.entityName.setText(msg);
		}
	}

	public EnigmaLabel getEntityName() {
		return this.entityName;
	}

	public EnigmaLabel getSelection() {
		return this.selection;
	}
}

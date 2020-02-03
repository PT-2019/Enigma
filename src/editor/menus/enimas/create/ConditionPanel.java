package editor.menus.enimas.create;

import api.utils.Observer;
import api.utils.Utility;
import common.entities.GameObject;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.enimas.create.listeners.ConditionListener;
import editor.popup.listeners.CaseListener;
import game.dnd.DragAndDropBuilder;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Menu d'ajout d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class ConditionPanel extends AbstractSubPopUpView implements Observer<GameObject> {

	public static final String NOT_AVAILABLE_CONDITION = NeedToBeTranslated.NOT_AVAILABLE_CONDITION;
	private static final String ASK_SELECT = NeedToBeTranslated.ASK_SELECT;
	public static final String ASK_COND = NeedToBeTranslated.ASK_COND;
	public static final String TITLE = NeedToBeTranslated.ADD_CONDITION;
	private static final String INVALID_ENTITY = NeedToBeTranslated.INVALID_ENTITY;

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

	ConditionPanel(AbstractPopUpView parent, ManageEnigmasAddView addView) {
		super("", parent, false);

		this.groups = new ButtonGroup();
		this.listener = new ConditionListener(parent, addView,this);

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

		this.content.setLayout(new BorderLayout());
		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.content.add(panelS, BorderLayout.CENTER);
		this.content.add(p2, BorderLayout.SOUTH);
	}

	@Override
	public void clean() {
		this.selection.setText("");
		this.entityName.setText(ASK_COND);
		this.listener.clean();
		this.groups.clearSelection();
		DragAndDropBuilder.setForPopup(null);
		//EnigmaView.setAvailable(null);
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void update(GameObject object) {
		if (!CaseListener.isAvailable(this)) return;

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
			msg += INVALID_ENTITY+" ";
			msg += operations.restrict;
			this.entityName.setText(msg);
		} else if (object == null && operations != null) {
			this.entityName.setText(ASK_SELECT + " (" + operations.menuDrag.msg + ")");
		} else if (object != null) {
			msg += object.getReadableName() + " (id=" + object.getID() + ")";
			this.entityName.setText(msg);
		} else {
			this.entityName.setText(msg);
		}
	}

	public EnigmaLabel getEntityName() {
		return this.entityName;
	}
}

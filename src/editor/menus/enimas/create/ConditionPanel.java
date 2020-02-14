package editor.menus.enimas.create;

import api.utils.Observer;
import api.utils.Utility;
import common.enigmas.condition.Condition;
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
 * Menu d'ajout d'une condition d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class ConditionPanel extends AbstractSubPopUpView implements Observer<GameObject> {

	/**
	 * Textes
	 */
	public static final String NOT_AVAILABLE_CONDITION = NeedToBeTranslated.NOT_AVAILABLE_CONDITION;
	public static final String ASK_COND = NeedToBeTranslated.ASK_COND;
	public static final String TITLE = NeedToBeTranslated.ADD_CONDITION;
	private static final String ASK_SELECT = NeedToBeTranslated.ASK_SELECT;
	private static final String INVALID_ENTITY = NeedToBeTranslated.INVALID_ENTITY;
	private static final String SUBMIT = NeedToBeTranslated.SUBMIT;
	public static final String ANSWER_CHOICE = NeedToBeTranslated.ANSWER;
    private static final String ANSWER_SELECTED = NeedToBeTranslated.ANSWER_SELECT;
    private static final String ANSWER_UNSELECTED = NeedToBeTranslated.ANSWER_UNSELECT;

	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	private final EnigmaLabel entityName, selection;

	/**
	 * Groupe des bouton de choix de l'opération
	 */
	private final ButtonGroup groups;
	/**
	 * Observateur de ce menu
	 */
	private ConditionListener listener;

	/**
	 * Panneau pour choisir la réponse dans une condition answer
	 */
	private ChoicePanel choicePanel;

	/**
	 * Menu d'ajout d'une condition d'une énigme
	 * @param parent parent
	 * @param addView addView
	 */
	ConditionPanel(AbstractPopUpView parent, ManageEnigmasAddView addView) {
		super("", parent, false);

		//Groupe des bouton de choix de l'opération
		this.groups = new ButtonGroup();
		//listener de la sélection d'une opération
		this.listener = new ConditionListener(addView, this);

		EnigmaPanel panel = new EnigmaPanel();
		GridBagLayout gb = new GridBagLayout();
		panel.setLayout(gb);
		GridBagConstraints gbc = new GridBagConstraints();
		int x=0,y=0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;

		for (Conditions cond : Conditions.values()) {
			JRadioButton r = new JRadioButton(cond.value);
			//tooltip
			r.setToolTipText(cond.tooltip);
			r.setName(cond.name());
			//on ajoute les boutons au groupe
			this.groups.add(r);
			//ajoute les boutons au panneau
			if (cond == Conditions.ANSWER ) {
				this.choicePanel = new ChoicePanel(ANSWER_CHOICE,r, this);
				panel.add(this.choicePanel,gbc);
			}else{
				panel.add(r,gbc);
			}
			y++;
			gbc.gridy = y;
			//listener pour les boutons
			r.addItemListener(this.listener);
		}

		EnigmaPanel p2 = new EnigmaPanel();
		p2.setLayout(new GridBagLayout());

		EnigmaButton submit = new EnigmaButton(SUBMIT);
		submit.addActionListener(this.listener);
		this.selection = new EnigmaLabel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 0, 0, 0);
		p2.add(this.selection, gbc);

		this.entityName = new EnigmaLabel(ASK_COND);
		this.entityName.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		p2.add(this.entityName, gbc);

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
		this.choicePanel.remove();
		DragAndDropBuilder.setForPopup(null);
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void update(GameObject object) {
		if (CaseListener.isNotAvailable(this)) return;

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
			msg += INVALID_ENTITY + " ";
			msg += operations.restrict;
			this.entityName.setText(msg);
		} else if (object == null && operations != null) {
			this.entityName.setText(ASK_SELECT + " (" + operations.menuDrag.msg + ")");
		} else if (object != null) {
			msg += object.getReadableName() + " (id=" + object.getID() + ")";
			//si c'est une answer il faut rajouter les informations sur le texte
			if (operations == Conditions.ANSWER){
                if (this.listener.getAnswer() != null){
                    msg += ANSWER_SELECTED;
                }else{
                    msg += ANSWER_UNSELECTED;
                }
            }
			this.entityName.setText(msg);
		} else {
			this.entityName.setText(msg);
		}
	}

	/**
	 * Méthode spécialement pour créer une answer, on a besoin d'une réponse à cette condition
	 * @param object
	 */
	public void update(String object){
		this.listener.setAnswer(object);
        String msg = "";

		if (this.listener.getObject() != null){
		    msg = this.entityName.getText();
		    msg = msg.replace(ANSWER_SELECTED,"");
			msg = msg.replace(ANSWER_UNSELECTED,"");
		    msg += ANSWER_SELECTED;
            this.entityName.setText(msg);
        }else {
		    msg += ANSWER_SELECTED;
            this.entityName.setText(msg);
        }
	}

	/**
	 * Renvoi le label avec le message de la sélection de l'entité
	 * @return le label avec le message de la sélection de l'entité
	 */
	public EnigmaLabel getEntityName() {
		return this.entityName;
	}
}

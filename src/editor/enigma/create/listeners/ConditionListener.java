package editor.enigma.create.listeners;

import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Content;
import api.entity.utils.Activatable;
import editor.enigma.condition.Activated;
import editor.enigma.condition.Answer;
import editor.enigma.condition.Condition;
import editor.enigma.condition.HaveInHands;
import editor.enigma.condition.HaveInInventory;
import editor.enigma.create.enigma.ConditionPanel;
import editor.enigma.create.enigma.EnigmaView;

import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

/**
 * Se charge de créer la condition à partir de EnigmaView et ConditionPanel
 *
 * @see EnigmaView
 * @see ConditionPanel
 */
public class ConditionListener implements ActionListener, ItemListener {

	private EnigmaView parent;

	private ConditionPanel panel;

	/**
	 * Attribut pour connaitre la condition choisi par l'utilisateur
	 */
	private JRadioButton currentButton;

	private GameObject object;

	public ConditionListener(EnigmaView parent, ConditionPanel panel) {
		this.parent = parent;
		this.panel = panel;
		this.object = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Condition cond = null;

		if (currentButton == null) {
			System.out.println("choissisez une option");
			throw new NullPointerException();
		}
		//en fonction du bouton radio actionné on choisi différente opération
		if (currentButton.getText().equals("Activé")) {
			if (object instanceof Activatable)
				cond = new Activated((Activatable) object);

		} else if (currentButton.getText().equals("Réponse")) {
			if (object instanceof Content)
				cond = new Answer((Content) object,"");

		} else if (currentButton.getText().equals("Posseder dans la main")) {
			if (object instanceof Item)
				cond = new HaveInHands((Item) object);
		} else {
			if (object instanceof Item)
				cond = new HaveInInventory((Item) object);
		}

		if (cond == null) {
			System.out.println("Mauvaise entité associé a une mauvaise condition");
		}
		parent.getEnigma().addCondition(cond);
		Iterator<Condition> it = parent.getEnigma().getAllConditions();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		CardLayout layout = parent.getCardLayout();
		layout.show(parent.getPanel(), "menu");
		parent.setModal(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			currentButton = (JRadioButton) e.getItem();

			if (currentButton.getText().equals("Réponse")) {
				panel.displaySearchItem();
				panel.displayAnswer();
			} else {
				if (!panel.isActivateSearchItem()) {
					panel.displaySearchItem();
				}
			}
		}
	}

	public void setGameObject(GameObject g) {
		object = g;
	}
}

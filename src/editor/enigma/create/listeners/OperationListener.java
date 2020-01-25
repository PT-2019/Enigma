package editor.enigma.create.listeners;

import api.entity.Entity;
import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Lockable;
import api.utils.Utility;
import editor.enigma.create.enigma.EnigmaView;
import editor.enigma.create.enigma.OperationPanel;
import editor.enigma.operation.Give;
import editor.enigma.operation.Operation;
import editor.enigma.operation.Summon;
import editor.enigma.operation.Unlock;

import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

/**
 * Se charge de créer les opérations lors d'une action de l'utilisateur à partir de EnigmaView et OperationPanel
 *
 * @see EnigmaView
 * @see editor.enigma.create.enigma.OperationPanel
 */
public class OperationListener implements ActionListener, ItemListener {

	private EnigmaView parent;
	private final OperationPanel operationPanel;

	private JRadioButton currentButton;

	private GameObject object;

	public OperationListener(EnigmaView parent, OperationPanel operationPanel) {
		this.parent = parent;
		this.operationPanel = operationPanel;
		this.object = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Operation ope = null;

		System.out.println(currentButton);

		if (currentButton == null) {
			operationPanel.getEntityName().setText("Sélectionnez une opération.");
			return;
		}

		if (currentButton.getText().equals("Give")) {
			if (object instanceof Item)
				ope = new Give((Item) object);
		} else if (currentButton.getText().equals("Summon")) {
			if(object instanceof Entity)
				ope = new Summon((Entity) object,  operationPanel.getFrame().getCell());
		} else if (currentButton.getText().equals("Unlock")) {
			if (object instanceof Lockable)
				ope = new Unlock((Lockable) object);
		}

		if(ope == null) return;

		parent.getEnigma().addOperation(ope);
		Utility.printDebug("enigmaAdded", ope.toLongString());
		operationPanel.clean();
		CardLayout layout = parent.getCardLayout();
		layout.show(parent.getPanel(), "menu");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			currentButton = (JRadioButton) e.getItem();
			operationPanel.getSelection().setText(OperationPanel.ASK_SELECT);
			operationPanel.getEntityName().setText(OperationPanel.NOT_SELECTED);
			object = null;
		}
	}

	public void clean(){
		object = null;
		currentButton = null;
	}

	public JRadioButton getCurrentButton() {
		return currentButton;
	}

	public void setGameObject(GameObject object) {
		this.object = object;
	}
}

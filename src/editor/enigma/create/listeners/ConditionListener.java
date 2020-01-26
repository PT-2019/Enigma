package editor.enigma.create.listeners;

import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Content;
import api.entity.utils.Activatable;
import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import editor.enigma.condition.Activated;
import editor.enigma.condition.Answer;
import editor.enigma.condition.Condition;
import editor.enigma.condition.HaveInHands;
import editor.enigma.condition.HaveInInventory;
import editor.enigma.create.enigma.ConditionPanel;
import editor.enigma.create.enigma.Conditions;
import editor.enigma.create.enigma.EnigmaView;
import game.EnigmaGame;
import game.entity.map.MapTestScreen;
import game.screen.TestScreen;
import game.utils.DragAndDropBuilder;

import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Se charge de créer la condition à partir de EnigmaView et ConditionPanel
 *
 * @see EnigmaView
 * @see ConditionPanel
 */
public class ConditionListener implements ActionListener, ItemListener {

	/** parent, operations board */
	private final ConditionPanel panel;
	/** Attribut pour connaitre la condition choisi par l'utilisateur */
	private JRadioButton currentButton;
	/** parent menu **/
	private EnigmaView parent;
	/** operation submited **/
	private boolean validate;
	/** selected object **/
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
			panel.getEntityName().setText(ConditionPanel.ASK_COND);
			return;
		}

		//TODO: on devrait pouvoir ajouter des nouvelles opérations plus facilement
		// voir la classe Conditions, le problème est le nombre variable d'argument du constructeur
		// même si il y a toujours un object apparemment

		//en fonction du bouton radio actionné on choisi différente opération
		if (this.currentButton.getName().equals(Conditions.ACTIVATED.name())) {
			if (this.object instanceof Activatable)
				cond = new Activated((Activatable) this.object);
		} else if (this.currentButton.getName().equals(Conditions.ANSWER.name())) {
			if(this.object instanceof Content)
				cond = new Answer((Content) object);
		} else if (this.currentButton.getName().equals(Conditions.HAVE_IN_HANDS.name())) {
			if (this.object instanceof Item)
				cond = new HaveInHands((Item) object);
		} else if (this.currentButton.getName().equals(Conditions.HAVE_IN_INVENTORY.name())) {
			if (this.object instanceof Item)
				cond = new HaveInInventory((Item) object);
		} else {
			this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_OPERATION);
		}

		if (cond == null) return;

		parent.getEnigma().addCondition(cond);
		Utility.printDebug("ConditionAdded", cond.toLongString());
		this.validate = true;
		this.panel.clean();
		CardLayout layout = this.parent.getCardLayout();
		layout.show(this.parent.getPanel(), "menu");
		parent.setModal(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			currentButton = (JRadioButton) e.getItem();
			if(currentButton.getName().equals(Conditions.ANSWER.name())){
				//TODO: saisie réponse
				this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_CONDITION);
			} else {
				EnigmaView.setAvailable(this.panel);
				this.panel.update(this.object);
				DragAndDropBuilder.setForPopup(this.panel);
			}
		}
	}

	public JRadioButton getCurrentButton() { return this.currentButton; }

	public void setGameObject(GameObject object) {
		if(this.object != null && !this.validate) {//si j'avais un object temporaire, je le supprime
			//il existe déjà un object
			Vector2 pos = this.object.getGameObjectPosition();
			//this.object est un temporaire
			if(pos == null || pos.x < 0 || pos.y < 0) {
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				//supprime de la map
				map.removeEntity(this.object);
				//l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
				if(object != null && object.getID() > this.object.getID())
					object.setID(this.object.getID());//transfert de l'ID du supprimé
			}
		}
		this.object = object;
	}

	public void clean() {
		this.setGameObject(null);
		this.currentButton = null;
	}
}

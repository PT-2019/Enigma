package editor.menus.enimas.create.listeners;

import common.utils.Logger;
import com.badlogic.gdx.math.Vector2;
import common.enigmas.condition.Activated;
import common.enigmas.condition.Answer;
import common.enigmas.condition.Condition;
import common.enigmas.condition.HaveInHands;
import common.enigmas.condition.HaveInInventory;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Activatable;
import common.entities.types.Content;
import common.map.MapTestScreen;
import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.ConditionPanel;
import editor.menus.enimas.create.Conditions;
import editor.menus.enimas.create.ManageEnigmasAddView;
import editor.popup.listeners.CaseListener;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Se charge de créer la condition à partir de EnigmaView et ConditionPanel
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 *
 * @see editor.menus.enimas.create.ConditionPanel
 */
public class ConditionListener implements ActionListener, ItemListener {

	/**
	 * parent, operations board
	 */
	private final ConditionPanel panel;
	/**
	 * Attribut pour connaitre la condition choisi par l'utilisateur
	 */
	private JRadioButton currentButton;
	/**
	 * parent menu
	 **/
	private AbstractPopUpView parent;

	private ManageEnigmasAddView addView;
	/**
	 * operation submited
	 **/
	private boolean validate;
	/**
	 * selected object
	 **/
	private GameObject object;

	public ConditionListener(AbstractPopUpView parent, ManageEnigmasAddView addView, ConditionPanel panel) {
		this.addView = addView;
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
			if (this.object instanceof Content)
				cond = new Answer((Content) object, "");
		} else if (this.currentButton.getName().equals(Conditions.HAVE_IN_HANDS.name())) {
			if (this.object instanceof Item)
				cond = new HaveInHands((Item) object);
		} else if (this.currentButton.getName().equals(Conditions.HAVE_IN_INVENTORY.name())) {
			if (this.object instanceof Item)
				cond = new HaveInInventory((Item) object);
		} else {
			this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_CONDITION);
		}

		if (cond == null) return;

		this.addView.getEnigma().addCondition(cond);
		Logger.printDebug("ConditionAdded", cond.toLongString());
		this.validate = true;
		this.panel.clean();
		this.addView.setCard(ManageEnigmasAddView.MENU, ManageEnigmasAddView.TITLE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			currentButton = (JRadioButton) e.getItem();
			if (currentButton.getName().equals(Conditions.ANSWER.name())) {
				//TODO: saisie réponse
				this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_CONDITION);
			} else {
				Conditions.lock(Conditions.valueOf(currentButton.getName()), this.panel);
				CaseListener.setAvailable(this.panel);
				this.panel.update(this.object);
				//DragAndDropBuilder.setForPopup(this.panel);
			}
		}
	}

	public JRadioButton getCurrentButton() {
		return this.currentButton;
	}

	public void setGameObject(GameObject object) {
		if (this.object != null && !this.validate) {//si j'avais un object temporaire, je le supprime
			//il existe déjà un object
			Vector2 pos = this.object.getGameObjectPosition();
			//this.object est un temporaire
			if (pos == null || pos.x < 0 || pos.y < 0) {
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				//supprime de la map
				map.removeEntity(this.object);
				//l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
				if (object != null && object.getID() > this.object.getID())
					object.setID(this.object.getID());//transfert de l'ID du supprimé
			}
		}
		this.object = object;
	}

	public void clean() {
		this.setGameObject(null);
		Conditions.unlock(null);
		this.currentButton = null;
	}
}
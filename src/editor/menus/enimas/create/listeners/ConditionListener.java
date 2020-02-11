package editor.menus.enimas.create.listeners;

import com.badlogic.gdx.math.Vector2;
import common.enigmas.condition.Activated;
import common.enigmas.condition.Answer;
import common.enigmas.condition.Condition;
import common.enigmas.condition.HaveInHands;
import common.enigmas.condition.HaveInInventory;
import common.enigmas.condition.RoomDiscovered;
import common.enigmas.condition.RoomUndiscovered;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.special.Room;
import common.entities.types.Activatable;
import common.entities.types.Content;
import common.map.MapTestScreen;
import common.utils.Logger;
import editor.menus.enimas.create.ConditionPanel;
import editor.menus.enimas.create.Conditions;
import editor.menus.enimas.create.ManageEnigmasAddView;
import editor.popup.listeners.CaseListener;
import game.EnigmaGame;
import game.screens.TestScreen;
import org.jetbrains.annotations.Nullable;

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
 * @version 6.0 02/02/2020
 * @see editor.menus.enimas.create.ConditionPanel
 * @since 6.0 02/02/2020
 */
public class ConditionListener implements ActionListener, ItemListener {

	/**
	 * parent, operations board
	 */
	private final ConditionPanel panel;
	/**
	 * Attribut pour connaître la condition choisi par l'utilisateur
	 */
	private JRadioButton currentButton;

	private ManageEnigmasAddView addView;
	/**
	 * operation submitted
	 **/
	private boolean validate;
	/**
	 * selected object
	 **/
	private GameObject object;

	public ConditionListener(ManageEnigmasAddView addView, ConditionPanel panel) {
		this.addView = addView;
		this.panel = panel;
		this.object = null;
		this.currentButton = null;
		this.validate = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Condition cond = null;

		//si pas de sélection
		if (currentButton == null) {
			panel.getEntityName().setText(ConditionPanel.ASK_COND);
			return;
		}

		String selectedCondition = this.currentButton.getName();

		//en fonction du bouton radio actionné on choisi la bonne condition

		//activé
		if (selectedCondition.equals(Conditions.ACTIVATED.name())) {
			if (this.object instanceof Activatable)
				cond = new Activated((Activatable) this.object);
		//réponse
		} else if (selectedCondition.equals(Conditions.ANSWER.name())) {
			if (this.object instanceof Content)
				cond = new Answer((Content) object, "");
		//avoir
		} else if (selectedCondition.equals(Conditions.HAVE_IN_HANDS.name())) {
			if (this.object instanceof Item)
				cond = new HaveInHands((Item) object);
		} else if (selectedCondition.equals(Conditions.HAVE_IN_INVENTORY.name())) {
			if (this.object instanceof Item)
				cond = new HaveInInventory((Item) object);
		//room
		} else if (selectedCondition.equals(Conditions.ROOM_UNDISCOVERED.name())) {
			if (this.object instanceof Room)
				cond = new RoomUndiscovered((Room) object);
		} else if (selectedCondition.equals(Conditions.ROOM_DISCOVERED.name())) {
			if (this.object instanceof Room)
				cond = new RoomDiscovered((Room) object);
		//sinon condition non disponible
		} else {
			this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_CONDITION);
		}

		if (cond == null) return;

		//ajoute la condition
		this.addView.getEnigma().addCondition(cond);
		Logger.printDebug("ConditionAdded", cond.toLongString());
		//condition ajoutée
		this.validate = true;
		//suppression des info
		this.panel.clean();
		//retour menu
		this.addView.setCard(ManageEnigmasAddView.MENU, ManageEnigmasAddView.TITLE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			//Bouton actuellement sélectionné
			this.currentButton = (JRadioButton) e.getItem();

			//récupère la condition
			Conditions conditions = Conditions.valueOf(this.currentButton.getName());

			//si disponible
			if(conditions.available){
				//verrouille l'accès a un second popup
				CaseListener.setAvailable(this.panel);
				//active les bons modes de sélection
				Conditions.lock(Conditions.valueOf(currentButton.getName()), this.panel);
				//préviens de l'activation
				this.panel.update(this.object);
			} else {
				this.panel.getEntityName().setText(ConditionPanel.NOT_AVAILABLE_CONDITION);
			}
		}
	}

	/**
	 * Bouton actuellement sélectionné
	 *
	 * @return Bouton actuellement sélectionné
	 */
	public JRadioButton getCurrentButton() {
		return this.currentButton;
	}

	/**
	 * Object sélectionné
	 *
	 * @param object Object sélectionné
	 */
	public void setGameObject(@Nullable GameObject object) {
		if (this.object != null && !this.validate) {//si j'avais un object temporaire, je le supprime
			//il existe déjà un object
			Vector2 pos = this.object.getGameObjectPosition();
			//this.object est un temporaire
			if (pos == null || pos.x < 0 || pos.y < 0) {
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				//supprime de la map
				int id = this.object.getID();
				map.removeEntity(this.object);
				//l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
				if (object != null && object.getID() > id) {
					//libère l'id
					map.freeId(object);
					object.setID(id);//transfert de l'ID du supprimé*/
				} else {
					//on veut supprimer l'object
					map.freeId(this.object);
				}
			}
		}
		this.object = object;
	}

	/**
	 * clean du listener
	 */
	public void clean() {
		//libère l'object temporaire
		this.setGameObject(null);
		//déverrouille l'éditeur
		Conditions.unlock(null);
		//supprime la sélection
		this.currentButton = null;
		//condition plus validée
		this.validate = false;
		//reset du panneau des conditions
		this.panel.update((GameObject) null);
	}
}
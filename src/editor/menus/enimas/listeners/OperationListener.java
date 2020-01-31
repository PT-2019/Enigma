package editor.menus.enimas.listeners;

import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import editor.menus.enimas.view.EnigmaView;
import editor.menus.enimas.view.OperationPanel;
import editor.menus.enimas.view.Operations;
import game.EnigmaGame;
import game.screens.TestScreen;
import general.enigmas.operation.Give;
import general.enigmas.operation.Operation;
import general.enigmas.operation.Summon;
import general.enigmas.operation.Unlock;
import general.entities.Entity;
import general.entities.GameObject;
import general.entities.Item;
import general.entities.types.Lockable;
import general.map.MapTestScreen;

import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Se charge de créer les opérations lors d'une action de l'utilisateur à partir de EnigmaView et OperationPanel
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see EnigmaView
 * @see editor.menus.enimas.view.OperationPanel
 * @since 4.2
 */
public class OperationListener implements ActionListener, ItemListener {

	/**
	 * parent, operations board
	 */
	private final OperationPanel operationPanel;
	/**
	 * selected operation
	 */
	private JRadioButton currentButton;
	/**
	 * parent menu
	 **/
	private EnigmaView parent;
	/**
	 * operation submited
	 **/
	private boolean validate;
	/**
	 * selected object
	 **/
	private GameObject object;

	public OperationListener(EnigmaView parent, OperationPanel operationPanel) {
		this.parent = parent;
		this.operationPanel = operationPanel;
		this.object = null;
		this.currentButton = null;
		this.validate = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Operation ope = null;

		if (currentButton == null) {
			operationPanel.getEntityName().setText(OperationPanel.ASK_OP);
			return;
		}

		//TODO: on devrait pouvoir ajouter des nouvelles opérations plus facilement
		// voir la classe Operations, le problème est le nombre variable d'argument du constructeur
		// même si il y a toujours un object apparemment

		if (this.currentButton.getName().equals(Operations.GIVE.name())) {
			if (this.object instanceof Item)
				ope = new Give((Item) this.object);
		} else if (this.currentButton.getName().equals(Operations.SUMMON.name())) {
			if (this.object instanceof Entity)
				ope = new Summon((Entity) this.object, operationPanel.getFrame().getCell());
		} else if (this.currentButton.getName().equals(Operations.UNLOCK.name())) {
			if (this.object instanceof Lockable)
				ope = new Unlock((Lockable) this.object);
		} else {
			this.operationPanel.getEntityName().setText(OperationPanel.NOT_AVAILABLE_OPERATION);
		}

		if (ope == null) return;

		this.parent.getEnigma().addOperation(ope);
		Utility.printDebug("OperationAdded", ope.toLongString());
		this.validate = true;
		this.operationPanel.clean();
		CardLayout layout = this.parent.getCardLayout();
		layout.show(this.parent.getPanel(), "menu");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			this.currentButton = (JRadioButton) e.getItem();
			EnigmaView.setAvailable(this.operationPanel);
			this.operationPanel.update(this.object);
			//DragAndDropBuilder.setForPopup(this.operationPanel);
		}
	}

	public void clean() {
		this.setGameObject(null);
		this.currentButton = null;
	}

	public JRadioButton getCurrentButton() {
		return currentButton;
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
}

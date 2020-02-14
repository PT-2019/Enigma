package editor.menus.enimas.create.listeners;

import com.badlogic.gdx.math.Vector2;
import common.enigmas.operation.ChangeMainMusic;
import common.enigmas.operation.Give;
import common.enigmas.operation.HideRoom;
import common.enigmas.operation.LaunchSound;
import common.enigmas.operation.Operation;
import common.enigmas.operation.ShowRoom;
import common.enigmas.operation.Summon;
import common.enigmas.operation.Unlock;
import common.entities.Entity;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.special.MusicEditor;
import common.entities.special.Room;
import common.entities.types.Lockable;
import common.map.MapTestScreen;
import common.utils.Logger;
import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.ManageEnigmasAddView;
import editor.menus.enimas.create.MusicPanel;
import editor.menus.enimas.create.OperationPanel;
import editor.menus.enimas.create.Operations;
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
 * Se charge de créer les opérations lors d'une action de l'utilisateur à partir de EnigmaView et OperationPanel
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see editor.popup.listeners.CaseListener
 * @see editor.menus.enimas.create.OperationPanel
 * @since 4.2
 */
public class OperationListener implements ActionListener, ItemListener {

	/**
	 * parent, operations board
	 */
	private final OperationPanel operationPanel;

	private final ManageEnigmasAddView addView;
	/**
	 * selected operation
	 */
	private JRadioButton currentButton;
	/**
	 * parent menu
	 **/
	private AbstractPopUpView parent;
	/**
	 * operation submitted
	 **/
	private boolean validate;
	/**
	 * selected object
	 **/
	private GameObject object;

	public OperationListener(AbstractPopUpView parent, OperationPanel operationPanel, ManageEnigmasAddView addView) {
		this.parent = parent;
		this.operationPanel = operationPanel;
		this.addView = addView;
		this.object = null;
		this.currentButton = null;
		this.validate = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Operation ope = null;

		if (this.currentButton == null) {
			this.operationPanel.getEntityName().setText(OperationPanel.ASK_OP);
			return;
		}

		String selectedOperation = this.currentButton.getName();

		//en fonction du bouton radio actionné on choisi la bonne opération

		//give
		if (selectedOperation.equals(Operations.GIVE.name())) {
			if (this.object instanceof Item)
				ope = new Give((Item) this.object);
		//summon
		} else if (selectedOperation.equals(Operations.SUMMON.name())) {
			if (this.object instanceof Entity)
				ope = new Summon((Entity) this.object, parent.getPopUp().getCell());
		//unlock
		} else if (selectedOperation.equals(Operations.UNLOCK.name())) {
			if (this.object instanceof Lockable)
				ope = new Unlock((Lockable) this.object);
		//room
		} else if (selectedOperation.equals(Operations.SHOW_ROOM.name())) {
			if (this.object instanceof Room)
				ope = new ShowRoom((Room) this.object);
		} else if (selectedOperation.equals(Operations.HIDE_ROOM.name())) {
			if (this.object instanceof Room)
				ope = new HideRoom((Room) this.object);
		}else if(selectedOperation.equals(Operations.SOUND.name())){
			if (this.object instanceof MusicEditor) {
				this.object.setTemp(false);//plus temporaire
				ope = new LaunchSound((MusicEditor) this.object);
				//on rajoute l"objet musique à la map pour pouvoir le sauvegarder dans le tmx
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				map.set(this.object, new Vector2(0, 0));
			}
		}else if(selectedOperation.equals(Operations.MAIN_MUSIC.name())){
			if (this.object instanceof MusicEditor) {
				this.object.setTemp(false);//plus temporaire
				((MusicEditor) this.object).setMainMusic(true);
				ope = new ChangeMainMusic((MusicEditor) this.object);
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				//on met le vecteur à 0 car on s'en fiche de la position
				map.set(this.object, new Vector2(0, 0));
			}
		} else { //sinon condition non disponible
			this.operationPanel.getEntityName().setText(OperationPanel.NOT_AVAILABLE_OPERATION);
		}

		if (ope == null) return;

		//ajoute la opération
		this.addView.getEnigma().addOperation(ope);
		Logger.printDebug("OperationAdded", ope.toLongString());
		//condition ajoutée
		this.validate = true;
		//suppression des info
		this.operationPanel.clean();
		//retour menu
		this.addView.setCard(ManageEnigmasAddView.MENU, ManageEnigmasAddView.TITLE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.setGameObject(null); //vide
		if (e.getStateChange() == ItemEvent.SELECTED) {
			//Bouton actuellement sélectionné
			this.currentButton = (JRadioButton) e.getItem();
			//verrouille l'accès a un second popup
			CaseListener.setAvailable(this.operationPanel);
			//active les bons modes de sélection
			Operations.lock(Operations.valueOf(currentButton.getName()), this.operationPanel);
			//préviens de l'activation
			this.operationPanel.update(this.object);

			if (this.currentButton.getParent() instanceof MusicPanel) {
				MusicPanel parent = (MusicPanel) this.currentButton.getParent();
				parent.dispLink();
			}
		}else{
			if (this.currentButton != null){
				if (this.currentButton.getParent() instanceof MusicPanel) {
					((MusicPanel) this.currentButton.getParent()).remove();
				}
			}
		}
	}

	/**
	 * Nettoyage
	 */
	public void clean() {
		//libère l'object temporaire
		this.setGameObject(null);
		//déverrouille l'éditeur
		Operations.unlock(null);
		//supprime la sélection
		this.currentButton = null;
		//condition plus validée
		this.validate = false;
		//reset du panneau des opérations
		this.operationPanel.update((GameObject) null);
	}

	/**
	 * Bouton actuellement sélectionné
	 *
	 * @return Bouton actuellement sélectionné
	 */
	public JRadioButton getCurrentButton() {
		return currentButton;
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
			if (pos == null || this.object.isTemp()) {
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

	@Override
	public String toString() {
		return "OperationListener{" + "currentButton=" + (currentButton==null?"":currentButton.getName()) +
				", validate=" + validate + ", object=" + object + '}';
	}
}
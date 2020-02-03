package editor.menus.enimas.create.listeners;

import common.utils.Logger;
import com.badlogic.gdx.math.Vector2;
import common.enigmas.operation.Give;
import common.enigmas.operation.Operation;
import common.enigmas.operation.Summon;
import common.enigmas.operation.Unlock;
import common.entities.Entity;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Lockable;
import common.map.MapTestScreen;
import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.ManageEnigmasAddView;
import editor.menus.enimas.create.OperationPanel;
import editor.menus.enimas.create.Operations;
import editor.popup.listeners.CaseListener;
import game.EnigmaGame;
import game.screens.TestScreen;

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
     * operation submited
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
                ope = new Summon((Entity) this.object, parent.getPopUp().getCell());
        } else if (this.currentButton.getName().equals(Operations.UNLOCK.name())) {
            if (this.object instanceof Lockable)
                ope = new Unlock((Lockable) this.object);
        } else {
            this.operationPanel.getEntityName().setText(OperationPanel.NOT_AVAILABLE_OPERATION);
        }

        if (ope == null) return;

        this.addView.getEnigma().addOperation(ope);
        Logger.printDebug("OperationAdded", ope.toLongString());
        this.validate = true;
        this.operationPanel.clean();
        this.addView.setCard(ManageEnigmasAddView.MENU, ManageEnigmasAddView.TITLE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.currentButton = (JRadioButton) e.getItem();
            CaseListener.setAvailable(this.operationPanel);
            Operations.lock(Operations.valueOf(currentButton.getName()), this.operationPanel);
            this.operationPanel.update(this.object);
            //DragAndDropBuilder.setForPopup(this.operationPanel);
        }
    }

    public void clean() {
        this.setGameObject(null);
        this.currentButton = null;
        Operations.unlock(null);
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
                System.out.println("remove");
                map.removeEntity(this.object);
                //l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
                if (object != null && object.getID() > this.object.getID())
                    object.setID(this.object.getID());//transfert de l'ID du supprimé
            }
        }
        this.object = object;
    }
}
package editor.enigma.create.listeners;

import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Lockable;
import api.enums.TypeEntite;
import editor.enigma.create.EnigmaView;
import editor.enigma.create.OperationPanel;
import editor.enigma.operation.Give;
import editor.enigma.operation.Operation;
import editor.enigma.operation.Unlock;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EnumMap;
import java.util.Iterator;

/**
 * Se charge de créer les opérations lors d'une action de l'utilisateur à partir de EnigmaView et OperationPanel
 * @see EnigmaView
 * @see OperationPanel
 */
public class OperationListener implements ActionListener, ItemListener {

    private EnigmaView parent;

    private JRadioButton currentButton;

    private GameObject object;

    public OperationListener(EnigmaView parent){
        this.parent = parent;
        this.object = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EnumMap<TypeEntite,Boolean> map = object.getImplements();
        Operation ope = null;

        if(currentButton == null){
            System.out.println("choissisez une option");
            throw new NullPointerException();
        }

        if(currentButton.getText().equals("Give")){
            if(map.get(TypeEntite.item)){
                Class c = object.getClass();
                try {
                    Item tmp =(Item) c.newInstance();
                    ope = new Give(tmp);
                } catch (InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(currentButton.getText().equals("Summon")){
            System.out.println("pas implémenter");
        }else if(currentButton.getText().equals("Unlock")){
            if(map.get(TypeEntite.lockable)){
                Class c = object.getClass();
                try {
                    Lockable tmp =(Lockable) c.newInstance();
                    ope = new Unlock(tmp);
                } catch (InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }

        parent.getEnigma().addOperation(ope);
        Iterator<Operation> it = parent.getEnigma().getAllOperations();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        CardLayout layout = parent.getCardLayout();
        layout.show(parent.getPanel(),"menu");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            currentButton = (JRadioButton) e.getItem();
        }
    }

    public void setGameObject(GameObject object) {
        this.object = object;
    }
}

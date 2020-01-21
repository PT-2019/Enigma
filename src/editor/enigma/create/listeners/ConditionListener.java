package editor.enigma.create.listeners;

import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Content;
import api.entity.utils.Activatable;
import api.enums.TypeEntite;
import editor.enigma.condition.*;
import editor.enigma.create.ConditionPanel;
import editor.enigma.create.EnigmaView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EnumMap;
import java.util.Iterator;

/**
 * Se charge de créer la condition à partir de EnigmaView et ConditionPanel
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

    public ConditionListener(EnigmaView parent,ConditionPanel panel){
        this.parent = parent;
        this.panel = panel;
        this.object = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EnumMap<TypeEntite,Boolean> map = object.getImplements();
        Condition cond = null;

        if(currentButton == null){
            System.out.println("choissisez une option");
            throw new NullPointerException();
        }
        //en fonction du bouton radio actionné on choisi différente opération
        if(currentButton.getText().equals("Activé")){
            if(map.get(TypeEntite.activatable)){
                Class c = object.getClass();
                try {
                    Activatable tmp =(Activatable) c.newInstance();
                    cond = new Activated(tmp);
                } catch (InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(currentButton.getText().equals("Réponse")){
            Class c = object.getClass();
            try {
                Content tmp =(Content) c.newInstance();
                System.out.println("pas encore implémenter");
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }else if(currentButton.getText().equals("Posseder dans la main")){
            Class c = object.getClass();
            try {
                Item tmp =(Item) c.newInstance();
                cond = new HaveInHands(tmp);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }else{
            Class c = object.getClass();
            try {
                Item tmp =(Item) c.newInstance();
                cond = new HaveInInventory(tmp);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        if (cond == null){
            System.out.println("Mauvaise entité associé a une mauvaise condition");
        }
        parent.getEnigma().addCondition(cond);
        Iterator<Condition> it = parent.getEnigma().getAllConditions();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        CardLayout layout = parent.getCardLayout();
        layout.previous(parent.getPanel());
        layout.previous(parent.getPanel());
        parent.setModal(true);
}

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            currentButton = (JRadioButton) e.getItem();

            if (currentButton.getText().equals("Réponse")) {
                panel.displayAnswer();
            } else {
                if (!panel.isActivateSearchItem()) {
                    panel.displaySearchItem();
                }
            }
        }
    }

    public void setGameObject(GameObject g){
        object = g;
    }
}

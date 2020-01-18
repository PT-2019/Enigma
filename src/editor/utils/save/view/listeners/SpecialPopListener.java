package editor.utils.save.view.listeners;

import api.entity.GameObject;
import editor.utils.save.view.cases.AbstractPopUp;
import editor.utils.save.view.cases.CasePopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui consiste à définir l'objet pour la création d'énigmes
 * @see CasePopUp
 */
public class SpecialPopListener implements ActionListener {

    /**
     * Popup à détruire lors de l'activation
     */
    private AbstractPopUp popDispose;

    /**
     * Popup qui garde en mémoire l'objet gameobject
     */
    private CasePopUp popup;

    /**
     * Objet choisi par l'utilisateur
     */
    private GameObject objectChose;

    public SpecialPopListener(AbstractPopUp popDispose, CasePopUp popup, GameObject chose){
        this.popDispose = popDispose;
        this.popup = popup;
        this.objectChose = chose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popup.setEnigmaCurrent(objectChose);
        popDispose.dispose();
    }
}

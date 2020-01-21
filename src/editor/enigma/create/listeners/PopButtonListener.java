package editor.enigma.create.listeners;


import editor.enigma.create.EnigmaView;
import editor.enigma.create.EnigmaWindowListener;
import editor.view.cases.CasePopUp;
import editor.view.cases.listeners.EntityChoseListener;
import game.entity.map.MapTestScreenCell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui affiche un enigmaView lorsqu'il y a un évènement
 */
public class PopButtonListener implements ActionListener {

    /**
     * Fenetre du popUp de la case
     */
    private CasePopUp frame;

    private MapTestScreenCell cell;

    private EntityChoseListener observer;

    public PopButtonListener(CasePopUp frame, MapTestScreenCell cell, EntityChoseListener observer){
        this.frame = frame;
        this.cell = cell;
        this.observer = observer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        EnigmaView enigmaView = new EnigmaView(frame,cell,observer);
        enigmaView.setVisible(true);
        enigmaView.addWindowListener(new EnigmaWindowListener(frame));
    }
}

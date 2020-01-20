package editor.enigma.create.listeners;


import editor.enigma.create.enigma.EnigmaView;
import editor.enigma.create.enigma.EnigmaWindowListener;
import editor.view.cases.CasePopUp;
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

    public PopButtonListener(CasePopUp frame,MapTestScreenCell cell){
        this.frame = frame;
        this.cell = cell;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        EnigmaView enigmaView = new EnigmaView(frame,cell);
        enigmaView.setVisible(true);
        enigmaView.addWindowListener(new EnigmaWindowListener(frame));
    }
}

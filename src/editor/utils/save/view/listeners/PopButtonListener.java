package editor.utils.save.view.listeners;


import editor.utils.save.view.cases.CasePopUp;
import editor.utils.save.view.cases.enigma.EnigmaView;
import editor.utils.save.view.cases.enigma.EnigmaWindowListener;
import game.entity.MapLibgdxCell;

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

    private MapLibgdxCell cell;

    public PopButtonListener(CasePopUp frame,MapLibgdxCell cell){
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

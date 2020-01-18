package editor.utils.save.view.cases.enigma;

import api.entity.interfaces.EnigmaContainer;
import editor.enigma.Enigma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui gère la navigation entre les différents états de la fenetre de création d'énigmes
 * @see EnigmaView
 */
public class NavigationEnigmaListener implements ActionListener {

    private JTextField title;

    private JTextArea description;

    public NavigationEnigmaListener(JTextField title,JTextArea description){
        this.title = title;
        this.description = description;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tmp = e.getActionCommand();
        JButton button = (JButton) e.getSource();
        EnigmaPanel panel =(EnigmaPanel) button.getParent();
        EnigmaView view = panel.getFrame();

        if (tmp.equals("Ajouter un indice")){
            CardLayout layout = view.getCardLayout();
            layout.next(view.getPanel());
        }else if(tmp.equals("Ajouter une étape")){
            view.setModal(false);
            CardLayout layout = view.getCardLayout();
            layout.next(view.getPanel());
            layout.next(view.getPanel());
        }else if (tmp.equals("Sauvegarder Enigme")){
            EnigmaContainer entity = (EnigmaContainer) view.getCell().getEntity();
            Enigma eng = view.getEnigma();
            eng.setDescription(description.getText());
            eng.setTitle(title.getText());
            entity.addEnigma(eng);
            view.dispose();
            view.getPopUp().setVisible(true);
        }else{
            view.setModal(false);
            CardLayout layout = view.getCardLayout();
            layout.next(view.getPanel());
            layout.next(view.getPanel());
            layout.next(view.getPanel());
        }
    }
}

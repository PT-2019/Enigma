package editor.bibliotheque;

import editor.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  classe ChoixObjet
 *
 *  @author Jorys-Micke ALAÏS
 *  @author Louka DOZ
 *  @author Loic SENECAT
 *  @author Quentin RAMSAMY-AGEORGES
 *
 *  @see editor.bibliotheque.Menu
 *
 *  @version 1.0 14 novembre 2019
 */
public class ChoixObjet implements ActionListener {
    private JPanel panneau;
    private CardLayout card;

    public ChoixObjet(JPanel pane, CardLayout pages){
        this.panneau=pane;
        this.card=pages;

    }

    /**
     * Cet méthode permet de gerer l'affichage de la partie gauche du menu via les boutons grace au card layout
     *
     * @see Menu
     * @param actionEvent
     *
     * @since 1.0 14 novembre 2019
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String msg =  actionEvent.getActionCommand();

       this.card.show(this.panneau, Utility.stringToEnum(msg, MenuCategories.values()).name);

        this.panneau.revalidate();
    }
}

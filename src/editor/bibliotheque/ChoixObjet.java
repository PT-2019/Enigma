package editor.bibliotheque;

import org.lwjgl.Sys;

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
 *  @see Menu
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
     * @since 1.0 14 novembre2019
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object msg =  actionEvent.getActionCommand();

        if (msg.equals("Personnages")){
            card.show(this.panneau,"Personnages");
        }else if(msg.equals("Décors")){
            card.show(this.panneau,"Décors");
        }else if(msg.equals("Murs")){
            card.show(this.panneau,"Murs");
        } else if (msg.equals("Salles")) {
            card.show(this.panneau,"Salles");
        }

        this.panneau.revalidate();
    }
}

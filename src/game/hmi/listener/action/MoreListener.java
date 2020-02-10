package game.hmi.listener.action;

import game.hmi.content.SeeMore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire du bouton "voir plus"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MoreListener implements ActionListener {
    /**
     * Elément contenant toutes les informations de la partie
     */
    private SeeMore more;

    /**
     * @param more Elément contenant toutes les informations de la partie
     */
    public MoreListener(SeeMore more){
        this.more = more;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.more.show(true);
    }
}

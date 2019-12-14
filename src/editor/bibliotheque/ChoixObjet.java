package editor.bibliotheque;

import editor.datas.EntitiesCategories;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observateur des boutons de sélection de catégorie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0 14 décembre 2019
 * @see editor.bibliotheque.MenuScreen
 * @since 1.0 14 novembre 2019
 */
public class ChoixObjet implements ActionListener {

	private JPanel panneau;
	private CardLayout card;

	public ChoixObjet(JPanel pane, CardLayout pages) {
		this.panneau = pane;
		this.card = pages;
	}

	/**
	 * Cet méthode permet de gerer l'affichage de la partie gauche du menu via
	 * les boutons grace au card layout
	 *
	 * @param actionEvent clic sur une des catégories
	 * @see MenuScreen
	 * @since 1.0 14 novembre 2019
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String msg = actionEvent.getActionCommand();

		for (EntitiesCategories category : EntitiesCategories.values()) {
			if (msg.equals(category.name)) {
				this.card.show(this.panneau, category.name());
				this.panneau.revalidate();
				return;
			}
		}
	}
}

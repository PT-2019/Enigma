package editor.menus.name;

import common.entities.GameObject;
import common.entities.types.Living;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.popup.cases.CasePopUp;
import game.EnigmaGame;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vue pour ajouter du contenu a un object.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
public class AddNameView extends AbstractPopUpView {
	private static final String INPUT = NeedToBeTranslated.INPUT_NAME;
	private static final String TITLE = NeedToBeTranslated.TITLE_NAME;
	private static final String SUBMIT = NeedToBeTranslated.SAVE;
	private static final String NAME_SAVED = NeedToBeTranslated.NAME_SAVED;
	private static final int PADDING = 10;

	/*
				 LABEL : SAISIR DU CONTENU
		|---------------------------------------|
		|                                       |
		|                                       |
		|              TEXT_AREA                |
		|                                       |
		|                                       |
		|---------------------------------------|
				| BUTTON: valider|
		 */

	/**
	 * La classe de base des vues de la popup
	 *
	 * @param popUp  parent
	 * @param object object implémente content
	 */
	AddNameView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);


		Living entity;
		if (object == null) entity = (Living) popUp.getCell().getEntity();
		else entity = (Living) object;

		//components

		EnigmaPanel back = new EnigmaPanel(new BorderLayout());
		//input label
		EnigmaLabel input = new EnigmaLabel(INPUT);
		input.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		input.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		//input field
		EnigmaTextArea field = new EnigmaTextArea();
		field.setText(entity.getName());
		field.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		field.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		//submit button
		EnigmaButton submit = new EnigmaButton(SUBMIT);
		submit.addActionListener(new SubmitListener(this, entity, field));
		//panel pour que submit ne prenne pas tout l'espace
		EnigmaPanel tmp = new EnigmaPanel(new GridBagLayout());
		tmp.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		tmp.add(submit);

		//addToContainer
		back.add(input, BorderLayout.NORTH);
		JScrollPane jScrollPane = field.setScrollBar();
		jScrollPane.setBorder(new EmptyBorder(0, PADDING, 0, PADDING));
		back.add(jScrollPane, BorderLayout.CENTER);
		back.add(tmp, BorderLayout.SOUTH);

		this.add(back);
	}

	@Override
	public void clean() {
	}

	/**
	 * Validation de la saisie
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 5.0 29/01/2020
	 * @since 5.0 29/01/2020
	 */
	private static class SubmitListener implements ActionListener {
		private final AddNameView parent;
		private final Living entity;
		private final EnigmaTextArea field;

		SubmitListener(AddNameView parent, Living entity, EnigmaTextArea field) {
			this.parent = parent;
			this.entity = entity;
			this.field = field;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//ajout du contenu
			this.entity.setName(this.field.getText());
			this.parent.dispose();//supprime fenêtre
			EnigmaGame.getCurrentScreen().showToast(NAME_SAVED);
			this.parent.popUp.setVisible(true);
		}
	}
}

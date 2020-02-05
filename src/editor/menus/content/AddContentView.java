package editor.menus.content;

import common.entities.GameObject;
import common.entities.types.Content;
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
public class AddContentView extends AbstractPopUpView {
	private static final String INPUT = NeedToBeTranslated.INPUT_CONTENT;
	private static final String TITLE = NeedToBeTranslated.TITLE_CONTENT;
	private static final String SUBMIT = NeedToBeTranslated.SAVE;
	private static final String CONTENT_SAVED = NeedToBeTranslated.CONTENT_SAVED;
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
	AddContentView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);


		Content entity;
		if (object == null) entity = (Content) popUp.getCell().getEntity();
		else entity = (Content) object;

		//components

		EnigmaPanel back = new EnigmaPanel(new BorderLayout());
		//input label
		EnigmaLabel input = new EnigmaLabel(INPUT);
		input.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		input.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		//input field
		EnigmaTextArea field = new EnigmaTextArea();
		field.setText(entity.getContent());
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
		private final AddContentView parent;
		private final Content entity;
		private final EnigmaTextArea field;

		SubmitListener(AddContentView parent, Content entity, EnigmaTextArea field) {
			this.parent = parent;
			this.entity = entity;
			this.field = field;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//ajout du contenu
			this.entity.addContent(this.field.getText());
			this.parent.dispose();//supprime fenêtre
			EnigmaGame.getCurrentScreen().showToast(CONTENT_SAVED);
			this.parent.popUp.setVisible(true);
		}
	}
}

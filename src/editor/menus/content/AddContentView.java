package editor.menus.content;

import common.entities.GameObject;
import common.entities.types.Content;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import data.NeedToBeTranslated;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.EditorAction;
import editor.bar.edition.actions.EditorActionFactory;
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
	 * Instance du menu
	 */
	private static AddContentView instance;

	/**
	 * entité dans laquelle on ajoute du contenu
	 */
	private final Content entity;
	/**
	 * Champ de saisie
	 */
	private final EnigmaTextArea field;

	/**
	 * La classe de base des vues de la popup
	 *
	 * @param popUp  parent
	 * @param object object implémente content
	 */
	AddContentView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);

		//instance
		instance = this;

		//récupère la bonne entité
		if (object == null) this.entity = (Content) popUp.getCell().getEntity();
		else this.entity = (Content) object;

		//components

		EnigmaPanel back = new EnigmaPanel(new BorderLayout());
		//input label
		EnigmaLabel input = new EnigmaLabel(INPUT);
		input.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		input.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		//input field
		this.field = new EnigmaTextArea();
		this.field.setText(this.entity.getContent());
		this.field.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.field.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		//submit button
		EnigmaButton submit = new EnigmaButton(SUBMIT);
		submit.addActionListener(new SubmitListener(this));
		//panel pour que submit ne prenne pas tout l'espace
		EnigmaPanel tmp = new EnigmaPanel(new GridBagLayout());
		tmp.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		tmp.add(submit);

		//addToContainer
		back.add(input, BorderLayout.NORTH);
		JScrollPane jScrollPane = this.field.setScrollBar();
		jScrollPane.setBorder(new EmptyBorder(0, PADDING, 0, PADDING));
		back.add(jScrollPane, BorderLayout.CENTER);
		back.add(tmp, BorderLayout.SOUTH);

		this.add(back);
	}

	/**
	 * Instance du menu
	 *
	 * @return Instance du menu
	 */
	public static AddContentView getInstance() {
		return instance;
	}

	@Override
	public void clean() {
	}

	@Override
	public void invalidateDrawable() {
		this.field.setText(this.entity.getContent());
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

		SubmitListener(AddContentView parent) {
			this.parent = parent;
			this.entity = parent.entity;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String oldContent = this.entity.getContent();

			//ajout du contenu
			this.entity.setContent(this.parent.field.getText());

			//ajout à l'historique
			EditorAction setContent = EditorActionFactory.actionWithinAMenu(ActionTypes.SET_CONTENT, this.entity, oldContent);
			ActionsManager.getInstance().add(setContent);

			this.parent.dispose();//supprime fenêtre
			EnigmaGame.getCurrentScreen().showToast(CONTENT_SAVED);
			this.parent.popUp.setVisible(true);
		}
	}
}

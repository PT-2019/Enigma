package editor.menus.name;

import com.badlogic.gdx.Gdx;
import common.entities.GameObject;
import common.entities.types.Living;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextField;
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
import java.awt.Dimension;
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
	private static final int PADDING = 10, WIDTH = AbstractPopUpView.WIDTH / 2;

	/*
				 LABEL : SAISIR NOM
		|---------------------------------------|
		|              TEXT_FIELD               |
		|---------------------------------------|
				| BUTTON: valider|
		 */

	/**
	 * instance du menu
	 */
	private static AddNameView instance;

	/**
	 * Champ de saisie du nom
	 */
	private final EnigmaTextField field;

	/**
	 * L'entité
	 */
	private final Living entity;

	/**
	 * La classe de base des vues de la popup
	 *
	 * @param popUp  parent
	 * @param object object implémente content
	 */
	AddNameView(CasePopUp popUp, GameObject object) {
		super(TITLE, popUp);

		//instance du menu
		instance = this;

		//récupère la bonne entité
		if (object == null) this.entity = (Living) popUp.getCell().getEntity();
		else this.entity = (Living) object;

		//components

		EnigmaPanel back = new EnigmaPanel(new BorderLayout());
		//input label
		EnigmaLabel input = new EnigmaLabel(INPUT);
		input.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		input.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		//input field
		this.field = new EnigmaTextField();
		this.field.setText(this.entity.getName());
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
		EnigmaPanel fieldP = new EnigmaPanel(new BorderLayout());
		fieldP.add(this.field, BorderLayout.CENTER);
		//fieldP.setBorder(new EmptyBorder(0, PADDING, 0, PADDING));
		JScrollPane sc = new JScrollPane(fieldP);
		sc.setBorder(new EmptyBorder(0, PADDING, 0, PADDING));
		back.add(sc, BorderLayout.CENTER);
		back.add(tmp, BorderLayout.SOUTH);

		this.setMinimumSize(new Dimension(WIDTH, 1));
		this.add(back);
		this.pack();
		this.setLocation(Gdx.graphics.getWidth() / 2 - getWidth() / 2, Gdx.graphics.getHeight() / 2 - getHeight() / 2);
	}

	/**
	 * instance du menu pour changer de nom
	 *
	 * @return instance
	 */
	public static AddNameView getInstance() {
		return instance;
	}

	@Override
	public void clean() {
	}

	@Override
	public void invalidateDrawable() {
		this.field.setText(this.entity.getName());
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

		SubmitListener(AddNameView parent) {
			this.parent = parent;
			this.entity = parent.entity;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//ajout du contenu
			String name = this.parent.field.getText();
			String old = this.entity.getName();
			if (name.isEmpty() || name.isBlank()) {
				this.entity.setName(this.entity.getKey());//nom de base
			} else {
				this.entity.setName(name);
			}
			this.parent.dispose();//supprime fenêtre
			EnigmaGame.getCurrentScreen().showToast(NAME_SAVED);

			//ajout à l'historique
			EditorAction setName = EditorActionFactory.actionWithinAMenu(
					ActionTypes.SET_NAME, this.entity, old
			);
			ActionsManager.getInstance().add(setName);

			//up du nom
			this.parent.popUp.invalidateDrawable();
			this.parent.popUp.setVisible(true);
		}
	}
}

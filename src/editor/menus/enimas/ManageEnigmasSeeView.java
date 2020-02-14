package editor.menus.enimas;

import common.enigmas.Advice;
import common.enigmas.Enigma;
import common.enigmas.condition.Condition;
import common.enigmas.operation.Operation;
import common.entities.GameObject;
import common.entities.types.EnigmaContainer;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaLabelUI;
import common.utils.Logger;
import data.NeedToBeTranslated;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.actions.EditorActionFactory;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.Drawable;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Vue de l'item sélectionné
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public class ManageEnigmasSeeView extends AbstractSubPopUpView implements Drawable {

	private static final String TITLE = "Consulter les informations de l'énigme";

	private static final String SEPARATOR = "------------";
	private static final String NONE = "Aucune";
	private static final String OPERATIONS = "Opérations";
	private static final String CONDITIONS = "Conditions";
	private static final String CLUES = "Indices";

	/**
	 * Object contenant l'énigme
	 */
	private final EnigmaContainer object;

	/**
	 * Menu extra
	 */
	private EnigmaPanel extra;

	/**
	 * Vue de l'item sélectionné
	 *
	 * @param parent parent
	 * @param object Object contenant l'énigme
	 */
	ManageEnigmasSeeView(AbstractPopUpView parent, GameObject object) {
		super(TITLE, parent, true);
		this.object = (EnigmaContainer) object;
	}

	void setChecked(Enigma checked) {
		Logger.printDebugALL("Enigma#See", checked.toLongString());

		this.content.removeAll();
		this.extra = new EnigmaPanel(new GridLayout(1, 1));

		EnigmaLabelUI ui = new EnigmaLabelUI();
		ui.setAllBackgrounds(Color.WHITE);
		ui.setAllForegrounds(Color.BLACK);

		int row = 0, c;

		String name = checked.getTitle();
		String desc = checked.getDescription();
		Iterator<Advice> allAdvices = checked.getAllAdvices();
		Iterator<Condition> allConditions = checked.getAllConditions();
		Iterator<Operation> allOperations = checked.getAllOperations();

		extra.add(new EnigmaLabel("Nom:" + name, ui));
		row++;
		extra.add(new EnigmaLabel("Description:" + desc, ui));
		row++;
		extra.add(new EnigmaLabel(SEPARATOR + " " + CLUES + " " + SEPARATOR, ui));
		row++;

		//Affichage des indices
		c = 0;
		while (allAdvices.hasNext()) {
			Advice a = allAdvices.next();
			extra.add(new EnigmaLabel(a.getEnigmaElementReadablePrint(), ui));
			c++;
		}

		if (c != 0) {
			row += c;
			c = 0;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		extra.add(new EnigmaLabel(SEPARATOR + " " + CONDITIONS + " " + SEPARATOR, ui));
		row++;

		//Affichage des conditions
		while (allConditions.hasNext()) {
			Condition a = allConditions.next();
			extra.add(new EnigmaLabel(a.getEnigmaElementReadablePrint(), ui));
			c++;
		}

		if (c != 0) {
			row += c;
			c = 0;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		extra.add(new EnigmaLabel(SEPARATOR + " " + OPERATIONS + " " + SEPARATOR, ui));
		row++;

		//Affichage des opérations
		while (allOperations.hasNext()) {
			Operation a = allOperations.next();
			extra.add(new EnigmaLabel(a.getEnigmaElementReadablePrint(), ui));
			c++;
		}

		if (c != 0) {
			row += c;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		((GridLayout) this.extra.getLayout()).setRows(row);

		JScrollPane panelS = new JScrollPane(this.extra);
		panelS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		EnigmaPanel deleteP = new EnigmaPanel(new GridBagLayout());
		EnigmaButton delete = new EnigmaButton(NeedToBeTranslated.DELETE);
		delete.addActionListener(new DeleteEnigma(checked, this));
		final int margin = 5;
		deleteP.setBorder(BorderFactory.createEmptyBorder(margin, 0, margin, 0));
		deleteP.add(delete);

		this.content.add(panelS, BorderLayout.CENTER);
		this.content.add(deleteP, BorderLayout.SOUTH);
		this.content.revalidate();
	}

	@Override
	public Container getDrawable() {
		return this.extra;
	}

	@Override
	public void invalidateDrawable() {
		//close and invalidate list
		this.parent.getCardLayout().show(this.parent.getPanel(), ManageEnigmasView.MENU);
		this.parent.invalidateDrawable();
	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}

	/**
	 * Listener de la suppression d'une énigme.
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 09/02/2020
	 * @since 6.0 09/02/2020
	 */
	private static class DeleteEnigma implements ActionListener {
		private final Enigma checked;
		private final AbstractPopUpView parent;
		private EnigmaContainer entity;

		DeleteEnigma(Enigma checked, ManageEnigmasSeeView view) {
			this.checked = checked;
			this.parent = view.parent;
			this.entity = view.object;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.entity.removeEnigma(this.checked);
			//ajout à l'historique
			ActionsManager.getInstance().add(
					EditorActionFactory.actionWithinAMenu(ActionTypes.REMOVE_ENIGMA, this.entity, this.checked)
			);
			//EnigmaGame.getCurrentScreen().showToast(NeedToBeTranslated.REMOVE_ENIGMA);
			this.parent.invalidateDrawable();
			this.parent.getCardLayout()
					.show(this.parent.getPanel(), ManageEnigmasView.MENU);
		}
	}
}

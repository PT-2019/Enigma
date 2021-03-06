package editor.menus.enimas.create;

import api.ui.base.ResetComponent;
import common.enigmas.Enigma;
import common.entities.GameObject;
import common.entities.types.EnigmaContainer;
import common.hud.EnigmaButton;
import common.map.MapTestScreenCell;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.enimas.ManageEnigmasView;
import editor.popup.cases.CasePopUp;
import editor.popup.listeners.CaseListener;
import game.dnd.DragAndDropBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vue du menu d'ajout d'une énigme
 * Element principal dans la création de l'enigme, contient toute les informations nécessaire
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 6.0 02/02/2020
 */
public class ManageEnigmasAddView extends AbstractSubPopUpView {

	/**
	 * Textes à traduire
	 */
	public static final String TITLE = NeedToBeTranslated.CREATE_ENIGMA;
	/**
	 * CardLayout
	 */
	public static final String MENU = "menu";
	static final String SAVE = NeedToBeTranslated.SAVE_ENIGMA;
	static final String ADD_CLUE = NeedToBeTranslated.ADD_CLUE;
	static final String ADD_COND = NeedToBeTranslated.ADD_COND;
	static final String ADD_OP = NeedToBeTranslated.ADD_OP;
	static final String CLUE = "clue";
	static final String OPERATION = "operation";
	static final String CONDITION = "condition";

	/**
	 * Object auquel on ajoute l'énigme
	 */
	private final GameObject object;

	/**
	 * Listener de la "fermeture"
	 */
	private final CustomBackToMenu customBackToMenu;
	/**
	 * énigme que l'utilisateur va créer
	 */
	private Enigma enigma;
	/**
	 * cardLayout
	 */
	private CardLayout layout;
	/**
	 * Composant principal de la fenêtre
	 */
	private JPanel panel;
	private CasePopUp popUp;
	private MapTestScreenCell cell;
	/**
	 * true si on est sur le menu sinon false
	 */
	private boolean onMenu;

	/**
	 * Vue du menu d'ajout d'une énigme
	 *
	 * @param parent parent
	 * @param object object contenant l'énigme
	 */
	public ManageEnigmasAddView(ManageEnigmasView parent, GameObject object) {
		super(TITLE, parent, true);
		this.object = object;

		EnigmaButton prev = this.menuPopUp.getPrev();
		prev.removeActionListener(this.menuPopUp.getBackToMenu());
		this.customBackToMenu = new CustomBackToMenu(this, parent);
		prev.addActionListener(this.customBackToMenu);

		//attributs
		this.layout = new CardLayout();
		this.panel = new JPanel();
		this.panel.setLayout(this.layout);
		this.popUp = parent.getPopUp();
		this.enigma = new Enigma();
		this.cell = parent.getPopUp().getCell();
		this.onMenu = false;

		//layout
		ConditionPanel condition = new ConditionPanel(parent, this);
		parent.getPopUp().getObserver().addObserver(condition);
		OperationPanel operation = new OperationPanel(parent, this);
		parent.getPopUp().getObserver().addObserver(operation);
		CluePanel clue = new CluePanel(parent, this);
		this.panel.add(new ManageEnigmaAddMenu(parent, this), MENU);
		this.panel.add(clue, CLUE);
		this.panel.add(operation, OPERATION);
		this.panel.add(condition, CONDITION);

		this.add(this.panel);
	}

	@Override
	public void clean() {
		//on remet le menu de création de base
		this.layout.show(this.panel, MENU);
		this.clearChildren();
		this.onMenu = true;
		//forPopup
		CaseListener.setAvailable(null);
		DragAndDropBuilder.setForPopup(null);
	}

	/**
	 * Appelle clean sur les enfants
	 */
	private void clearChildren() {
		//clean des composants
		for (Component components : this.panel.getComponents()) {
			if (!(components instanceof ResetComponent)) continue;
			((ResetComponent) components).clean();
		}
	}

	/**
	 * Change la card lue
	 *
	 * @param name  nom d'une card
	 * @param title titre du sous-menu
	 * @since 6.1
	 */
	public void setCard(String name, String title) {
		this.onMenu = name.equals(MENU);
		this.layout.show(this.panel, name);
		this.menuPopUp.setTitle(title);
	}

	@Override
	public void initComponent() {
	}

	/**
	 * Ferme cette fenêtre
	 *
	 * @since 6.1
	 */
	public void close() {
		this.onMenu = true;
		this.customBackToMenu.actionPerformed(null);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public Enigma getEnigma() {
		return this.enigma;
	}

	public CasePopUp getPopUp() {
		return this.popUp;
	}

	public MapTestScreenCell getCell() {
		return this.cell;
	}

	public EnigmaContainer getEntity() {
		return (EnigmaContainer) this.object;
	}

	/**
	 * Retour au menu
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 02/02/2020
	 * @since 6.0 02/02/2020
	 */
	private static class CustomBackToMenu implements ActionListener {

		private final ManageEnigmasAddView addView;
		private final AbstractPopUpView parent;

		CustomBackToMenu(ManageEnigmasAddView addView, ManageEnigmasView parent) {
			this.addView = addView;
			this.parent = parent;
		}

		@Override
		public void actionPerformed(@Nullable ActionEvent e) {
			//si on est sur le menu de création, on quitte pour revenir à la liste
			if (this.addView.onMenu) {
				//reset du menu
				this.addView.clean();

				//on met le parent
				CardLayout layout = this.parent.getCardLayout();
				layout.show(this.parent.getPanel(), MENU);

				//reset de l'énigme
				this.addView.enigma = new Enigma();

				//up du parent
				this.parent.invalidateDrawable();

				//Reset l'éditor dans son état avant ouverture
				DragAndDropBuilder.setForPopup(null);
			} else {
				this.addView.clearChildren();
				//sinon retourne sur le menu
				this.addView.setCard(MENU, ManageEnigmasAddView.TITLE);
			}
		}
	}
}

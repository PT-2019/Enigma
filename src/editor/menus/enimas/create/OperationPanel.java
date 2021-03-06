package editor.menus.enimas.create;

import api.utils.Observer;
import api.utils.Utility;
import common.entities.GameObject;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.SelectionsModes;
import editor.menus.enimas.create.listeners.OperationListener;
import editor.popup.listeners.CaseListener;
import game.dnd.DragAndDropBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Interface graphique de la création des opérations
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0
 */
public class OperationPanel extends AbstractSubPopUpView implements Observer<GameObject> {

	public static final String ASK_OP = NeedToBeTranslated.ASK_OP;
	public static final String NOT_AVAILABLE_OPERATION = NeedToBeTranslated.NOT_AVAILABLE_OPERATION;
	public static final String TITLE = NeedToBeTranslated.ADD_OPERATION;
	//todo: !!!!
	public static final String BUT_MUS = NeedToBeTranslated.CHOSE_MUSIC;
	public static final String BUT_SOUND = NeedToBeTranslated.CHOSE_SOUND;
	/**
	 * Textes
	 */
	private static final String ASK_SELECT = NeedToBeTranslated.ASK_SELECT;
	private static final String INVALID_ENTITY = NeedToBeTranslated.INVALID_ENTITY;
	private static final String SUBMIT = NeedToBeTranslated.SUBMIT;
	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	private final EnigmaLabel entityName, selection;
	/**
	 * Groupe des bouton de choix de l'opération
	 */
	private ButtonGroup groups;
	/**
	 * Observateur de ce menu
	 */
	private OperationListener listener;

	private MusicPanel[] musicPanel;

	OperationPanel(AbstractPopUpView parent, ManageEnigmasAddView addView) {
		super("", parent, false);

		this.groups = new ButtonGroup();
		this.listener = new OperationListener(parent, this, addView);
		this.musicPanel = new MusicPanel[2];
		int index = 0;
		EnigmaPanel panel = new EnigmaPanel();
		GridBagLayout gb = new GridBagLayout();
		panel.setLayout(gb);
		GridBagConstraints gbc = new GridBagConstraints();
		int x = 0, y = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;

		for (Operations op : Operations.values()) {
			JRadioButton r = new JRadioButton(op.value);
			r.setToolTipText(op.name());
			r.setName(op.name());
			//on ajoute les boutons au groupe
			groups.add(r);

			if (op == Operations.SOUND) {
				this.musicPanel[index] = new MusicPanel(OperationPanel.BUT_SOUND, r, this, op);
				panel.add(musicPanel[index], gbc);
				index++;
			} else if (op == Operations.MAIN_MUSIC) {
				this.musicPanel[index] = new MusicPanel(OperationPanel.BUT_MUS, r, this, op);
				panel.add(musicPanel[index], gbc);
				index++;
			} else {
				//ajoute les boutons au panneau
				panel.add(r, gbc);
			}
			y++;
			gbc.gridy = y;
			//listener pour les boutons
			r.addItemListener(this.listener);
		}

		EnigmaPanel p2 = new EnigmaPanel();
		p2.setLayout(new GridBagLayout());

		EnigmaButton submit = new EnigmaButton(SUBMIT);
		submit.addActionListener(listener);
		selection = new EnigmaLabel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 0, 0, 0);
		p2.add(selection, gbc);

		entityName = new EnigmaLabel(ASK_OP);
		entityName.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		p2.add(entityName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 0, 0);
		p2.add(submit, gbc);

		this.content.setLayout(new BorderLayout());
		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.content.add(panelS, BorderLayout.CENTER);
		this.content.add(p2, BorderLayout.SOUTH);
	}

	/**
	 * Reset de toutes les informations pour la prochaine utilisation
	 *
	 * @since 5.0
	 */
	@Override
	public void clean() {
		this.selection.setText("");
		this.listener.clean();
		this.entityName.setText(ASK_OP);
		this.groups.clearSelection();
		for (MusicPanel p : this.musicPanel) {
			p.remove();
		}
		DragAndDropBuilder.setForPopup(null);
	}

	@Override
	public void initComponent() {
	}

	/**
	 * Met a jour le panneau des opérations, si par exemple une entité a étée sélectionnée
	 *
	 * @param object une entité
	 * @since 4.0
	 */
	@Override
	public void update(@Nullable GameObject object) {
		if (CaseListener.isNotAvailable(this)) return;

		this.listener.setGameObject(object);
		JRadioButton currentButton = this.listener.getCurrentButton();

		String msg = "";
		Operations operations = null;
		boolean wrong = false;

		if (currentButton == null) {
			msg += ASK_OP;
		} else {
			operations = Utility.stringToEnum(currentButton.getName(), Operations.values());
			//s'il avait sélectionné une entité, on la vérifie
			if (object != null) {
				if (!operations.isValid(object)) {
					object = null;
					wrong = true;
				}
			}
		}

		if (operations == Operations.END_GAME)
			msg = "";

		if (object == null && wrong) {
			msg += INVALID_ENTITY + " ";
			msg += operations.restrict;
			this.entityName.setText(msg);
		} else if (object == null && operations != null) {
			if (operations.menuDrag.contains(SelectionsModes.SPECIAL)) {
				this.entityName.setText(operations.restrict);
			} else if (operations == Operations.END_GAME) {
				this.entityName.setText("");
			} else this.entityName.setText(ASK_SELECT + " (" + operations.menuDrag.msg + ")");
		} else if (object != null) {
			msg += object.getReadableName() + " (id=" + object.getID() + ")";
			this.entityName.setText(msg);
		} else {
			this.entityName.setText(msg);
		}
	}

	public EnigmaLabel getEntityName() {
		return this.entityName;
	}
}
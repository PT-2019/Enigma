package editor.popup.cases;

import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Container;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.popup.cases.listeners.EntityChoseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Selection d'un item
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 13/02/2020
 * @since 6.0 13/02/2020
 */
public class ChooseItemView extends AbstractPopUpView {

	/**
	 * Textes
	 */
	private static final String SELECT_ENTITY = NeedToBeTranslated.SELECT_ENTITY;
	private static final String TITLE = NeedToBeTranslated.ITEM_LIST;
	private static final String NO_ITEMS = NeedToBeTranslated.NO_ITEM;

	private final EnigmaButton use;
	private final Container entity;
	private ButtonGroup groups;
	private SelectItem listener;
	private Item selected = null;

	ChooseItemView(CasePopUp parent, GameObject entity) {
		super(TITLE, parent);
		this.entity = (Container) entity;
		this.setModal(true);

		this.groups = new ButtonGroup();
		this.listener = new SelectItem(parent, this);

		this.use = new EnigmaButton(SELECT_ENTITY);
		this.use.addActionListener(this.listener);

		this.initComponent();
	}

	@Override
	public void clean() {

	}

	public void initComponent() {
		//Récupération des items
		ArrayList<Item> items = this.entity.getItems();

		//Création de la liste
		EnigmaPanel list = new EnigmaPanel(new GridLayout(items.size(), 1));
		boolean first = true;
		for (Item item : items) {
			JCheckBox r = new JCheckBox(item.getReadableName() + " (id=" + item.getID() + ")");
			//on ajoute les boutons au groupe
			r.setToolTipText(item.getReadableName());
			this.groups.add(r);
			r.setName(item.getID() + "");
			//ajoute les boutons au panneau
			list.add(r);
			//listener pour les boutons
			r.addItemListener(this.listener);
			if (first) {
				r.setSelected(true);
				first = false;
			}
		}

		//vide
		if (items.size() == 0) {
			list.setLayout(new GridBagLayout());
			EnigmaLabel empty = new EnigmaLabel(NO_ITEMS);
			empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			list.add(empty);
			this.use.setVisible(false);
		} else {
			this.use.setVisible(true);
		}

		//scroll
		JScrollPane listScrollable = new JScrollPane(list);
		listScrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		//mise en page
		this.setLayout(new BorderLayout());
		EnigmaLabel title = new EnigmaLabel(TITLE);
		title.setVerticalAlignment(EnigmaLabel.CENTER);
		title.setHorizontalAlignment(EnigmaLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		this.add(title, BorderLayout.NORTH);
		this.add(listScrollable, BorderLayout.CENTER);
		this.add(this.use, BorderLayout.SOUTH);
		this.revalidate();
	}

	/**
	 * Listener de la sélection d'un item
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 10/02/2020
	 * @since 6.0 10/02/2020
	 */
	private static final class SelectItem implements ItemListener, ActionListener {

		private final CasePopUp parent;
		private final ChooseItemView chooseItemView;

		/**
		 * Listener de la sélection d'un item
		 *
		 * @param parent         parent
		 * @param chooseItemView vue d'un item
		 */
		private SelectItem(CasePopUp parent, ChooseItemView chooseItemView) {
			this.parent = parent;
			this.chooseItemView = chooseItemView;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			for (Item item : chooseItemView.entity.getItems()) {
				if (item.getID() == Integer.parseInt(((JCheckBox) e.getSource()).getName())) {
					chooseItemView.selected = item;
					break;
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chooseItemView.selected == null) return;
			EntityChoseListener obs = this.parent.getObserver();
			obs.updateObserver(chooseItemView.selected);
		}
	}
}

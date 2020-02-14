package editor.menus.item;

import api.utils.Observer;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Container;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.ShowCardLayout;
import game.dnd.DragAndDropBuilder;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Liste des objects contenus dans un object.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public class AddItemListView extends AbstractSubPopUpView implements Observer<GameObject> {

	private static final String TITLE = NeedToBeTranslated.ITEM_LIST;
	private static final String SEE_ENTITY = NeedToBeTranslated.SEE_ITEM;
	private static final String ADD_ENTITY =  NeedToBeTranslated.ADD_ITEM;
	private static final String NO_ITEMS = NeedToBeTranslated.NO_ITEM;

	private final EnigmaButton see;
	private ButtonGroup groups;
	private ItemListener listener;

	/**
	 * Liste des objects contenus dans un object.
	 *
	 * @param parent  parent
	 * @param seeView vue d'un item
	 */
	AddItemListView(AbstractPopUpView parent, AddItemSeeView seeView) {
		super(TITLE, parent, false);
		this.groups = new ButtonGroup();
		this.listener = new SelectItem(parent, seeView);

		this.see = new EnigmaButton(SEE_ENTITY);
		this.see.addActionListener(new ShowCardLayout(AddItemView.SEE, parent));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.footer.add(this.see, gbc);
		EnigmaButton add = new EnigmaButton(ADD_ENTITY);
		add.addActionListener(new ShowCardLayout(AddItemView.ADD, parent));
		add.addActionListener(e -> DragAndDropBuilder.setForPopup(((AddItemView) parent).getAddItemAddView()));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.footer.add(add, gbc);

		this.initComponent();
	}

	@Override
	public void onShow() {
		this.clean();
		this.initComponent();
	}

	@Override
	public void clean() {
		this.content.removeAll();
		this.infoLabel.setText("");
		this.groups.clearSelection();
	}

	@Override
	public void initComponent() {
		Container container = (Container) this.parent.getPopUp().getCell().getEntity();
		ArrayList<Item> items = container.getItems();
		EnigmaPanel panel = new EnigmaPanel(new GridLayout(items.size(), 1));
		boolean first = true;
		for (Item item : items) {
			JCheckBox r = new JCheckBox(item.getReadableName() + " (id=" + item.getID() + ")");
			//on ajoute les boutons au groupe
			r.setToolTipText(item.getReadableName());
			this.groups.add(r);
			r.setName(item.getID() + "");
			//ajoute les boutons au panneau
			panel.add(r);
			if (first) {
				r.setSelected(true);
				first = false;
			}
			//listener pour les boutons
			r.addItemListener(this.listener);
		}

		if (items.size() == 0) {
			panel.setLayout(new GridBagLayout());
			EnigmaLabel empty = new EnigmaLabel(NO_ITEMS);
			empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			panel.add(empty);
			this.see.setVisible(false);
		} else {
			this.see.setVisible(true);
		}

		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.content.add(panelS, BorderLayout.CENTER);
		this.revalidate();
	}

	@Override
	public void update(GameObject object) {
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
	private static final class SelectItem implements ItemListener {

		private final AbstractPopUpView parent;
		private final AddItemSeeView seeView;

		/**
		 * Listener de la sélection d'un item
		 *
		 * @param parent  parent
		 * @param seeView vue d'un item
		 */
		private SelectItem(AbstractPopUpView parent, AddItemSeeView seeView) {
			this.parent = parent;
			this.seeView = seeView;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			Container container = (Container) this.parent.getPopUp().getCell().getEntity();
			for (Item item : container.getItems()) {
				if (item.getID() == Integer.parseInt(((JCheckBox) e.getSource()).getName())) {
					this.seeView.setChecked(item);
					break;
				}
			}
		}
	}
}

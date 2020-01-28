package editor.view.listeners.available.view.item;

import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Container;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.listeners.available.view.AbstractPopUpView;
import editor.view.listeners.available.view.AbstractSubPopUpView;
import editor.view.listeners.available.view.ShowCardLayout;
import game.utils.DragAndDropBuilder;

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


public class AddItemListView extends AbstractSubPopUpView {

	private static final String TITLE = "Liste des objects";
	private static final String SEE_ENTITY = "Voir entité sélectionnée";
	private static final String ADD_ENTITY = "Ajouter entité";
	private static final String NO_ITEMS = "Aucun item";

	private final EnigmaButton see, add;
	private ButtonGroup groups;
	private ItemListener listener;

	public AddItemListView(AbstractPopUpView parent, AddItemSeeView seeView) {
		super(TITLE, parent, false);
		this.groups = new ButtonGroup();
		this.listener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Container container = (Container) parent.getPopUp().getCell().getEntity();
				for (Item item :  container.getItems()) {
					if(item.getID() == Integer.parseInt(((JCheckBox)e.getSource()).getName())){
						seeView.setChecked(item);
						break;
					}
				}
			}
		};

		see = new EnigmaButton(SEE_ENTITY);
		see.addActionListener(new ShowCardLayout(AddItemView.SEE, parent));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.footer.add(see, gbc);
		add = new EnigmaButton(ADD_ENTITY);
		add.addActionListener(new ShowCardLayout(AddItemView.ADD, parent));
		add.addActionListener(e -> {DragAndDropBuilder.setForPopup(((AddItemView)parent).getAddItemAddView());});
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
	public void onHide() {
	}

	@Override
	public void onShow() {
		//this.clean();
		//this.initComponent();
	}

	@Override
	public void update(GameObject object) {
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
		EnigmaPanel panel = new EnigmaPanel(new GridLayout(items.size(),1));
		boolean first = true;
		for (Item item : items) {
			JCheckBox r = new JCheckBox(item.getReadableName()+" (id="+item.getID()+")");
			//on ajoute les boutons au groupe
			r.setToolTipText(item.getReadableName());
			groups.add(r);
			r.setName(item.getID()+"");
			//ajoute les boutons au panneau
			panel.add(r);
			//listener pour les boutons
			r.addItemListener(this.listener);
			if(first){
				r.setSelected(true);
				first=false;
			}
		}

		if(items.size() == 0){
			panel.setLayout(new GridBagLayout());
			EnigmaLabel empty = new EnigmaLabel(NO_ITEMS);
			empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			panel.add(empty);
			see.setVisible(false);
		} else {
			see.setVisible(true);
		}

		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.content.add(panelS, BorderLayout.CENTER);
	}

	public ButtonGroup getGroup() {
		return groups;
	}
}

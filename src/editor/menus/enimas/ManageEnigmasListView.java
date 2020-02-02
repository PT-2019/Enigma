package editor.menus.enimas;

import common.enigmas.Enigma;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Container;
import common.entities.types.EnigmaContainer;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
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
import java.util.Iterator;

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
public class ManageEnigmasListView extends AbstractSubPopUpView {

	private static final String TITLE = "Liste des énigmes";
	private static final String SEE_ENIGMA = "Voir énigme sélectionnée";
	private static final String ADD_ENIGMA = "Créer énigme";
	private static final String NO_ITEMS = "Aucune énigme";

	private final EnigmaButton see;
	private final EnigmaContainer object;
	private ButtonGroup groups;
	private ItemListener listener;

	/**
	 * Liste des objects contenus dans un object.
	 * @param parent parent
	 * @param seeView vue d'un item
	 * @param object enigma container
	 */
	ManageEnigmasListView(AbstractPopUpView parent, ManageEnigmasSeeView seeView, GameObject object) {
		super(TITLE, parent, false);
		this.object = (EnigmaContainer) object;
		this.groups = new ButtonGroup();
		this.listener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				EnigmaContainer container = (EnigmaContainer) parent.getPopUp().getCell().getEntity();
				Iterator<Enigma> allEnigmas = container.getAllEnigmas();
				while(allEnigmas.hasNext()){
					Enigma enigma = allEnigmas.next();
					if (enigma.getID() == Integer.parseInt(((JCheckBox) e.getSource()).getName())) {
						seeView.setChecked(enigma);
						break;
					}

				}
			}
		};

		see = new EnigmaButton(SEE_ENIGMA);
		see.addActionListener(new ShowCardLayout(ManageEnigmasView.SEE, parent));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.footer.add(see, gbc);
		EnigmaButton add = new EnigmaButton(ADD_ENIGMA);
		add.addActionListener(new ShowCardLayout(ManageEnigmasView.ADD, parent));
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
		Iterator<Enigma> enigmas = this.object.getAllEnigmas();
		System.out.println(this.object);
		EnigmaPanel panel = new EnigmaPanel(new GridLayout(1,1));
		boolean first = true;
		int i = 0;
		while(enigmas.hasNext()){
			System.out.println(enigmas);
			Enigma e = enigmas.next();
			JCheckBox r = new JCheckBox(e.getTitle());
			//on ajoute les boutons au groupe
			r.setToolTipText(e.getDescription());
			groups.add(r);
			r.setName(e.getID() + "");
			//ajoute les boutons au panneau
			panel.add(r);
			//listener pour les boutons
			r.addItemListener(this.listener);
			if (first) {
				r.setSelected(true);
				first = false;
			}
			i++;
		}
		((GridLayout)panel.getLayout()).setRows(i);

		if (i == 0) {
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
		this.content.revalidate();
	}

	public ButtonGroup getGroup() {
		return groups;
	}
}
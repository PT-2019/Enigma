package editor.menus.enimas.viewT;

import api.utils.Observer;
import common.entities.GameObject;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;

import javax.swing.ButtonGroup;

/**
 * Menu d'ajout d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class OperationPanel extends AbstractSubPopUpView implements Observer<GameObject> {

	public static final String NOT_SELECTED = "Vous n'avez pas encore choisi d'entité";
	public static final String ASK_SELECT = "Veuillez sélectionner un objet ";
	public static final String ASK_OP = "Veuillez sélectionner une opération.";
	public static final String NOT_AVAILABLE_OPERATION = "Opération non disponible";
	private static final String INVALID_ENTITY = "Entité Invalide. ";
	private static final String TITLE = "Ajouter une Opération à l'énigme";
	/**
	 * Les informations sur l'entité sur laquelle l'opération sera faite
	 */
	//private final EnigmaLabel entityName, selection;
	/**
	 * Groupe des bouton de choix de l'opération
	 */
	private ButtonGroup groups;
	/**
	 * Observateur de ce menu
	 */
	//private OperationListener listener;

	/**
	 * Menu d'ajout d'une énigme
	 * @param parent parent
	 */
	public OperationPanel(AbstractPopUpView parent) {
		super("", parent, false);

		/*this.removeAll();//suppression du menu, pas besoin
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		this.groups = new ButtonGroup();
		this.listener = new OperationListener(parent, this);

		EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridLayout(Operations.values().length, 1));

		for (Operations op : Operations.values()) {
			JRadioButton r = new JRadioButton(op.value);
			r.setToolTipText(op.tooltip);
			r.setName(op.name());
			//on ajoute les boutons au groupe
			groups.add(r);
			//ajoute les boutons au panneau
			panel.add(r);
			//listener pour les boutons
			r.addItemListener(this.listener);
		}

		EnigmaPanel p2 = new EnigmaPanel();
		p2.setLayout(new GridBagLayout());

		EnigmaButton submit = new EnigmaButton("Valider");
		submit.addActionListener(listener);
		selection = new EnigmaLabel();
		GridBagConstraints gbc = new GridBagConstraints();
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

		this.setLayout(new BorderLayout());
		this.add(new MenuPanel(TITLE, "", parent, this), BorderLayout.NORTH);
		JScrollPane panelS = new JScrollPane(panel);
		panelS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(panelS, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);*/

	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void update(GameObject object) {

	}
}


package api.hud;

import api.hud.base.CustomComponent;
import api.hud.manager.ComboBoxManager;
import api.hud.manager.CustomComboBoxItemManager;
import api.hud.ui.CustomComboBoxUI;

import javax.swing.JPanel;
import javax.swing.MenuElement;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Une liste déroulante customizable
 * TODO: finir les commentaires, j'ai la f . l . e . m . m . e
 * (pas les override)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomComboBox extends JPanel implements CustomComponent<CustomComboBoxUI> {

	protected CustomPopupMenu popup;
	protected CustomLabel label;
	protected CustomButton button;
	protected CustomMenuItem selected;
	protected CustomComboBoxUI ui;

	public CustomComboBox() {
		this(true);
	}

	/**
	 * Crée une liste déroulante mais n'appelle pas la fonction
	 * init. Permet de changer le type des conteneurs avant qu'il ne soit
	 * trop tard
	 *
	 * @param init true pour init sinon false
	 */
	protected CustomComboBox(boolean init) {
		super();
		this.popup = new CustomPopupMenu();
		this.label = new CustomLabel();
		this.button = new CustomButton("v");
		this.selected = null;
		if (init) {
			this.init();
		}
	}

	public CustomComboBox(CustomPopupMenu popup) {
		super();
		this.setPopup(popup);
		this.label = new CustomLabel("5555555555555555555555555");
		this.button = new CustomButton("v");
		this.selected = null;

		this.init();

		this.setFirstElement();
	}

	/**
	 * Initialisation des composants de la liste déroulante
	 */
	protected void init() {
		this.addMouseListener(new ComboBoxManager(this));
		this.setOpaque(true);
		this.setComponentUI(new CustomComboBoxUI());

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 10;
		gbc.weighty = 1;
		this.add(this.label, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(this.button, gbc);

		ComboBoxManager manager = new ComboBoxManager(this);
		this.addMouseListener(manager);
		this.button.addMouseListener(manager);
		this.label.addMouseListener(manager);
	}

	public void setFirstElement() {
		if (this.popup.getComponentCount() > 0) {
			CustomMenuItem item = (CustomMenuItem) this.popup.getComponent(0);
			this.label.setText(item.getText());
			this.selected = item;
		}
	}

	public void setElementAtIndex(int index) {
		if (this.popup.getComponentCount() >= index && index >= 0) {
			CustomMenuItem item = (CustomMenuItem) this.popup.getComponent(index);
			this.label.setText(item.getText());
			this.selected = item;
		}
	}

	public CustomPopupMenu getPopup() {
		return this.popup;
	}

	public void setPopup(CustomPopupMenu popup) {
		for (MenuElement element : this.popup.getSubElements()) {
			if (!(element instanceof CustomMenuItem))
				throw new IllegalArgumentException("Les éléments du EnigmaPopupMenu doivent impérativement être des EnigmaMenuItems");
			this.setListenerToItem((CustomMenuItem) element);
		}
		this.popup = popup;
		this.setFirstElement();
	}

	public CustomLabel getLabel() {
		return this.label;
	}

	public void setLabel(CustomLabel label) {
		this.label = label;
	}

	public CustomButton getButton() {
		return this.button;
	}

	public void setButton(CustomButton button) {
		this.button = button;
	}

	public void addItem(CustomMenuItem item) {
		this.setListenerToItem(item);
		this.popup.add(item);
		if (this.popup.getComponentCount() == 1) this.setFirstElement();
	}

	private void setListenerToItem(CustomMenuItem item) {
		item.addActionListener(new CustomComboBoxItemManager(this));
	}

	public CustomMenuItem getSelected() {
		return this.selected;
	}

	public void setSelected(CustomMenuItem item) {
		this.setElementAtIndex(this.popup.getComponentIndex(item));
	}

	//ui

	@Override
	public CustomComboBoxUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomComboBoxUI ui) {
		this.ui = ui.duplicate();
		this.button.setComponentUI(this.ui.getButtonUI());
		this.label.setComponentUI(this.ui.getLabelUI());
		this.label.setFont(this.ui.getFont());
		this.popup.setComponentUI(this.ui.getPopupUI());
		this.setCursor(this.ui.getCursor());
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}


}

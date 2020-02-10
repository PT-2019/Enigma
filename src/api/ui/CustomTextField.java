package api.ui;

import api.ui.base.CustomComponent;
import api.ui.manager.CustomTextFieldManager;
import api.ui.skin.CustomTextFieldUI;

import javax.swing.JTextField;
import java.awt.Graphics;

/**
 * Un champ de texte de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 05/02/2020
 * @since 6.0 05/02/2020
 */
public class CustomTextField extends JTextField implements CustomComponent<CustomTextFieldUI> {

	private CustomTextFieldUI ui;

	/**
	 * Champ de texte vide
	 */
	public CustomTextField() {
		super();
		CustomTextFieldManager manager = new CustomTextFieldManager(this);
		this.addMouseListener(manager);
		this.addFocusListener(manager);
		this.setOpaque(true);
		this.setComponentUI(new CustomTextFieldUI());
	}

	/**
	 * Champ de texte
	 *
	 * @param text contenu
	 */
	public CustomTextField(String text) {
		super(text);
		CustomTextFieldManager manager = new CustomTextFieldManager(this);
		this.addMouseListener(manager);
		this.addFocusListener(manager);
		this.setOpaque(true);
		this.setComponentUI(new CustomTextFieldUI());
	}

	@Override
	public CustomTextFieldUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomTextFieldUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paintTextField(g, this);
	}
}

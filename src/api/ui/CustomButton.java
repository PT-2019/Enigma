package api.ui;

import api.ui.base.CustomComponent;
import api.ui.manager.CustomButtonManager;
import api.ui.skin.CustomButtonUI;

import javax.swing.JButton;

/**
 * Un bouton customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 3.0
 */
public class CustomButton extends JButton implements CustomComponent<CustomButtonUI> {

	/**
	 * L'ui du bouton
	 */
	protected CustomButtonUI ui;

	/**
	 * Crée un simple bouton
	 */
	public CustomButton() {
		super();
		this.addMouseListener(new CustomButtonManager(this));
		this.setOpaque(true);
		this.setComponentUI(new CustomButtonUI());
	}

	/**
	 * Crée un simple bouton
	 *
	 * @param title son contenu
	 */
	public CustomButton(String title) {
		super(title);
		this.addMouseListener(new CustomButtonManager(this));
		this.setOpaque(true);
		this.setComponentUI(new CustomButtonUI());
	}

	@Override
	public CustomButtonUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomButtonUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		super.setUI(this.ui);
	}

	/**
	 * Retourne si le bouton est sélectionné
	 *
	 * @return true si le bouton est sélectionné
	 */
	public boolean isSelected() {
		return this.ui.isSelected();
	}
}

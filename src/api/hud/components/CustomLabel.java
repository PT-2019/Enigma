package api.hud.components;

import api.hud.manager.CustomLabelManager;
import api.hud.ui.CustomLabelUI;

import javax.swing.JLabel;
import java.awt.Graphics;

/**
 * Un label customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public class CustomLabel extends JLabel implements CustomComponent<CustomLabelUI> {

	private CustomLabelUI ui;

	/**
	 * Crée un label
	 */
	public CustomLabel() {
		super();
		this.addMouseListener(new CustomLabelManager(this));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setComponentUI(new CustomLabelUI());
	}

	/**
	 * Crée un label
	 *
	 * @param title contenu
	 */
	public CustomLabel(String title) {
		super(title);
		this.addMouseListener(new CustomLabelManager(this));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setComponentUI(new CustomLabelUI());
	}

	@Override
	public CustomLabelUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomLabelUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}
}
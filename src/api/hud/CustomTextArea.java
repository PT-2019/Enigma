package api.hud;

import api.hud.base.CustomComponent;
import api.hud.manager.CustomTextAreaManager;
import api.hud.ui.CustomTextAreaUI;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Graphics;

/**
 * Une zone de saisie customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public class CustomTextArea extends JTextArea implements CustomComponent<CustomTextAreaUI> {

	private CustomTextAreaUI ui;

	public CustomTextArea() {
		super();
		CustomTextAreaManager manager = new CustomTextAreaManager(this);
		this.addMouseListener(manager);
		this.addFocusListener(manager);
		this.setOpaque(true);
		this.setComponentUI(new CustomTextAreaUI());
		this.setLineWrap(true);
	}

	/**
	 * Renvoi la zone de texte avec une barre de scroll
	 *
	 * @return la zone de texte avec une barre de scroll
	 */
	public JScrollPane setScrollBar() {
		JScrollPane scroll = new JScrollPane(this);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		return scroll;
	}

	@Override
	public CustomTextAreaUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomTextAreaUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paintTextArea(g, this);
	}
}
package api.ui;

import api.ui.base.CustomComponent;
import api.ui.manager.CustomPanelManager;
import api.ui.skin.CustomPanelUI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.LayoutManager;

/**
 * Un JPanel customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0 27/12/2019
 */
public class CustomPanel extends JPanel implements CustomComponent<CustomPanelUI> {

	protected CustomPanelUI ui;

	/**
	 * Un JPanel customizable
	 */
	public CustomPanel() {
		super();
		this.addMouseListener(new CustomPanelManager(this));
		this.setOpaque(true);
		this.setComponentUI(new CustomPanelUI());
	}

	/**
	 * Un JPanel customizable
	 *
	 * @param manager Layout
	 * @since 5.0
	 */
	public CustomPanel(LayoutManager manager) {
		super(manager);
		this.addMouseListener(new CustomPanelManager(this));
		this.setOpaque(true);
		this.setComponentUI(new CustomPanelUI());
	}

	@Override
	public CustomPanelUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomPanelUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}

}

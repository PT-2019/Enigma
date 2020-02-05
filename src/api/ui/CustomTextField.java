package api.ui;

import api.ui.base.CustomComponent;
import api.ui.manager.CustomTextFieldManager;
import api.ui.skin.CustomTextFieldUI;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: javadoc
 */
public class CustomTextField extends JTextField implements CustomComponent<CustomTextFieldUI> {

	private CustomTextFieldUI ui;

	public CustomTextField() {
		super();
	}

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

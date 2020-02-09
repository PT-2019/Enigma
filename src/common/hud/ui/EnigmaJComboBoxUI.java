package common.hud.ui;

import data.config.EnigmaUIValues;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * Une classe pour remplacer la combo box qui ne
 * marche pas avec la libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public final class EnigmaJComboBoxUI extends BasicComboBoxUI implements ListCellRenderer {

	private EnigmaJComboBoxUI() {
	}

	@SuppressWarnings("unchecked")
	public static void createUI(JComboBox<?> c) {
		EnigmaJComboBoxUI ui = new EnigmaJComboBoxUI();
		c.setRenderer(ui);
		c.setBackground(EnigmaUIValues.ENIGMA_LABEL_BACKGROUND);
		c.setFont(EnigmaUIValues.ENIGMA_FONT);
		c.setForeground(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
		c.setBorder(new EnigmaJComboBoxUIBorder());
		c.setUI(ui);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Component getListCellRendererComponent(JList list, Object value, int index,
	                                              boolean isSelected, boolean cellHasFocus) {
		//DefaultListCellRenderer base = new DefaultListCellRenderer();
		ListCellRenderer base = new JComboBox().getRenderer();

		Component c = base.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		//change le hover ici
		if (-1 < index && value != null) {
			list.setSelectionBackground(EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BACKGROUND);
			list.setSelectionForeground(EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND);
		}

		return c;
	}

	@Override
	protected JButton createArrowButton() {
		BasicArrowButton arrow = new BasicArrowButton(SwingConstants.SOUTH);
		arrow.setBackground(EnigmaUIValues.ENIGMA_COMBOBOX_BORDER);
		arrow.setBorder(new EnigmaJComboBoxUIBorder());
		return arrow;
	}

	/**
	 * Bordure de la combo box
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0 29/12/2019
	 * @since 4.0 29/12/2019
	 */
	private static final class EnigmaJComboBoxUIBorder implements Border {

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			int[] borders = new int[4];
			for (int i = 0; i < borders.length; i++) {
				borders[i] = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
			}
			if (c instanceof JComboBox)
				((JComponent) c).setBorder(BorderFactory.createMatteBorder(
						borders[EnigmaUIValues.TOP_BORDER],
						borders[EnigmaUIValues.LEFT_BORDER],
						borders[EnigmaUIValues.BOTTOM_BORDER],
						borders[EnigmaUIValues.RIGHT_BORDER], EnigmaUIValues.ENIGMA_BUTTON_BORDER)
				);
			if (c instanceof JButton) {
				((JComponent) c).setBorder(BorderFactory.createMatteBorder(
						borders[EnigmaUIValues.TOP_BORDER],
						borders[EnigmaUIValues.LEFT_BORDER],
						borders[EnigmaUIValues.BOTTOM_BORDER],
						borders[EnigmaUIValues.RIGHT_BORDER], EnigmaUIValues.ENIGMA_COMBOBOX_BORDER)
				);
			}
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return EnigmaUIValues.ENIGMA_COMBOX_INSET;
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}
	}
}

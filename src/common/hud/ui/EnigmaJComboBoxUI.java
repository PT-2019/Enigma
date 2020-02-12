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
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Color;
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

	/*private static final Color BORDER_COLOR_LIST = CustomColors.TILED_GRAY_DARK;
	private static final Color BORDER_COLOR_BUTTON = CustomColors.TILED_GRAY_DARK;
	private static final Color BG = CustomColors.TILED_GRAY;
	private static final Color FG = Color.BLACK;
	private static final Color HOVERED_BACKGROUND = CustomColors.TILED_GRAY_DARK;
	private static final Color HOVERED_FOREGROUND = Color.BLACK;
	private static final Color ARROW_BG = CustomColors.TILED_GRAY;
	private static final Color ARROW_FG = Color.BLACK;
	private static final int BORDER_SIZE_ARROW = 0;
	private static final int BORDER_SIZE_BOX = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;*/

	private static final Color BG = EnigmaUIValues.ENIGMA_LABEL_BACKGROUND;
	private static final Color FG = EnigmaUIValues.ENIGMA_LABEL_FOREGROUND;
	private static final Color HOVERED_BACKGROUND = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BACKGROUND;
	private static final Color HOVERED_FOREGROUND = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND;
	private static final Color ARROW_BG = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER;
	private static final Color ARROW_FG = Color.BLACK;
	private static final int BORDER_SIZE_ARROW = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
	private static final int BORDER_SIZE_BOX = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
	private static final Color BORDER_COLOR_LIST = EnigmaUIValues.ENIGMA_BUTTON_BORDER;
	private static final Color BORDER_COLOR_BUTTON = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER;


	private EnigmaJComboBoxUI() {
	}

	@SuppressWarnings("unchecked")
	public static void createUI(JComboBox<?> c) {
		EnigmaJComboBoxUI ui = new EnigmaJComboBoxUI();
		c.setRenderer(ui);
		c.setBackground(BG);
		c.setForeground(FG);
		c.setFont(EnigmaUIValues.ENIGMA_FONT);
		c.setBorder(new EnigmaJComboBoxUIBorder());
		c.setUI(ui);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Component getListCellRendererComponent(JList list, Object value, int index,
	                                              boolean isSelected, boolean cellHasFocus) {
		ListCellRenderer base = new JComboBox().getRenderer();

		Component c = base.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		//change le hover ici
		if (-1 < index && value != null) {
			list.setSelectionBackground(HOVERED_BACKGROUND);
			list.setSelectionForeground(HOVERED_FOREGROUND);
		}

		return c;
	}

	@Override
	protected JButton createArrowButton() {
		BasicArrowButton arrow = new BasicArrowButton(SwingConstants.SOUTH);
		arrow.setBackground(ARROW_BG);
		arrow.setForeground(ARROW_FG);
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
			if (c instanceof JComboBox)
				((JComponent) c).setBorder(BorderFactory.createMatteBorder(
						BORDER_SIZE_BOX,
						BORDER_SIZE_BOX,
						BORDER_SIZE_BOX,
						BORDER_SIZE_BOX, BORDER_COLOR_LIST)
				);
			if (c instanceof JButton) {
				((JComponent) c).setBorder(BorderFactory.createMatteBorder(
						BORDER_SIZE_ARROW,
						BORDER_SIZE_ARROW,
						BORDER_SIZE_ARROW,
						BORDER_SIZE_ARROW, BORDER_COLOR_BUTTON)
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

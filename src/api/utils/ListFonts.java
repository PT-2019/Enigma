package api.utils;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

/**
 * Affiche dans une fenêtre une liste des
 * polices
 */
public class ListFonts extends JComponent {
	//fonts
	private static final String[] FONTS_NAMES = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getAvailableFontFamilyNames();
	private Font[] font = new Font[FONTS_NAMES.length];

	public void paintComponent(Graphics g) {
		for (int i = 0; i < FONTS_NAMES.length; i++) {
			if (font[i] == null) {
				font[i] = new Font(FONTS_NAMES[i], Font.PLAIN, 16);
			}
			g.setFont(font[i]);
			int p = 15;
			int q = 15+ (15 * i);
			g.drawString(FONTS_NAMES[i],p,q);
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Different Fonts");
		frame.add(new JScrollPane(new ListFonts(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		frame.setSize(350, 650);
		frame.setVisible(true);
	}
}

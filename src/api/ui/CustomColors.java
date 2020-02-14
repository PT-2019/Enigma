package api.ui;

import java.awt.Color;

/**
 * Amélioration de color
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
@SuppressWarnings("unused")
public final class CustomColors extends Color {

	public static final Color LIGHT_STEEL_BLUE = new Color(176, 196, 222);
	public static final Color POWDER_BLUE = new Color(176, 224, 230);
	public static final Color LIGHT_BLUE = new Color(173, 216, 230);
	public static final Color SKY_BLUE = new Color(135, 206, 235);
	public static final Color LIGHT_SKY_BLUE = new Color(135, 206, 250);
	public static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);
	public static final Color DODGER_BLUE = new Color(30, 144, 255);
	public static final Color CORN_FLOWER_BLUE = new Color(100, 149, 237);
	public static final Color STEEL_BLUE = new Color(70, 130, 180);

	public static final Color DARK_OLIVE_GREEN = new Color(85, 107, 47);
	public static final Color OLIVE = new Color(128, 128, 0);
	public static final Color OLIVE_DRAB = new Color(107, 142, 35);
	public static final Color YELLOW_GREEN = new Color(154, 205, 50);
	public static final Color LIME_GREEN = new Color(50, 205, 50);
	public static final Color LIME = new Color(0, 255, 0);
	public static final Color LAWN_GREEN = new Color(124, 252, 0);
	public static final Color CHARTREUSE = new Color(127, 255, 0);
	public static final Color GREEN_YELLOW = new Color(173, 255, 47);

	public static final Color LIGHT_SALMON = new Color(255, 160, 122);
	public static final Color SALMON = new Color(250, 128, 114);
	public static final Color DARK_SALMON = new Color(233, 150, 122);
	public static final Color LIGHT_CORAL = new Color(240, 128, 128);
	public static final Color INDIAN_RED = new Color(205, 92, 92);
	public static final Color CRIMSON = new Color(220, 20, 60);
	public static final Color FIRE_BRICK = new Color(178, 34, 34);
	public static final Color DARK_RED = new Color(139, 0, 0);

	public static final Color LEMON_CHIFFON = new Color(255, 250, 205);
	public static final Color LIGHT_GOLDENROD_YELLOW = new Color(250, 250, 210);
	public static final Color PAPAYA_WHIP = new Color(255, 239, 213);
	public static final Color MOCCASIN = new Color(255, 228, 181);
	public static final Color PEACH_PUFF = new Color(255, 218, 185);
	public static final Color PALE_GOLDENROD = new Color(238, 232, 170);
	public static final Color KHAKI = new Color(240, 230, 140);
	public static final Color DARK_KHAKI = new Color(189, 183, 107);
	public static final Color GOLD = new Color(255, 215, 0);

	public static final Color TILED_GRAY = Color.decode("#bfbfbf");
	public static final Color TILED_GRAY_DARK = Color.decode("#6A6A6A");

	/**
	 * Crée une couleur
	 *
	 * @param r rouge
	 * @param g vert
	 * @param b bleu
	 * @param a transparence
	 */
	public CustomColors(float r, float g, float b, float a) {
		super(r, g, b, a);
	}
}

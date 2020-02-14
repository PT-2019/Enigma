package data.config;

/**
 * Usine à bordures
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class ShowedBorderFactory {

	/**
	 * Pour ne pas instancier
	 */
	private ShowedBorderFactory() {
	}

	/**
	 * Bordure supérieure
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure inférieur
	 *
	 * @return Bordures
	 */
	public static boolean[] createBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure droite
	 *
	 * @return Bordures
	 */
	public static boolean[] createRightBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure gauche
	 *
	 * @return Bordures
	 */
	public static boolean[] createLeftBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure et inférieure
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure et droite
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopRightBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure et gauche
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopLeftBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure inférieur et droite
	 *
	 * @return Bordures
	 */
	public static boolean[] createRightBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}


	/**
	 * Bordure droite et gauche
	 *
	 * @return Bordures
	 */
	public static boolean[] createRightLeftBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure inférieure et gauche
	 *
	 * @return Bordures
	 */
	public static boolean[] createLeftBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure, droite et inférieure
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopRightBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure, gauche et inférieure
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopLeftBottomBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure supérieure, gauche et droite
	 *
	 * @return Bordures
	 */
	public static boolean[] createTopRightLeftBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.TOP_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}

	/**
	 * Bordure droite, gauche et inférieure
	 *
	 * @return Bordures
	 */
	public static boolean[] createBottomRightLeftBorder() {
		boolean[] border = new boolean[4];
		border[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		border[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		return border;
	}
}

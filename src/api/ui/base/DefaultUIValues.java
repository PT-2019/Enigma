package api.ui.base;

import java.awt.Color;
import java.awt.Font;

/**
 * Les valeurs de bases des composants de l'interface.
 * <p>
 * Inspiré a 100% du jeu Enigma.
 *
 * @author Louka DOZ
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface DefaultUIValues {

	/**
	 * Police de base
	 */
	Font DEFAULT_FONT = new Font("Monospaced", Font.BOLD, 15);

	/**
	 * Afficher une bordure
	 */
	boolean SHOWED_BORDER = true;
	/**
	 * Cacher une bordure
	 */
	boolean HIDDEN_BORDER = false;
	/**
	 * Afficher toutes les bordures
	 */
	boolean[] ALL_BORDERS_SHOWED = {SHOWED_BORDER, SHOWED_BORDER, SHOWED_BORDER, SHOWED_BORDER};
	/**
	 * Cacher toutes les bordures
	 */
	boolean[] ALL_BORDER_HIDDEN = {HIDDEN_BORDER, HIDDEN_BORDER, HIDDEN_BORDER, HIDDEN_BORDER};
	/**
	 * Indices du tableau des bordures
	 */
	int TOP_BORDER = 0, RIGHT_BORDER = 1, BOTTOM_BORDER = 2, LEFT_BORDER = 3;

	/*
	 * Constantes liées au style des boutons
	 */

	Color DEFAULT_BUTTON_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_BUTTON_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_BUTTON_PRESSED_BACKGROUND = new Color(100, 100, 100);
	Color DEFAULT_BUTTON_FOREGROUND = Color.WHITE;
	Color DEFAULT_BUTTON_HOVERED_FOREGROUND = Color.CYAN;
	Color DEFAULT_BUTTON_PRESSED_FOREGROUND = Color.CYAN;
	Color DEFAULT_BUTTON_BORDER = Color.WHITE;
	Color DEFAULT_BUTTON_HOVERED_BORDER = Color.CYAN;
	Color DEFAULT_BUTTON_PRESSED_BORDER = Color.CYAN;
	int DEFAULT_BUTTON_BORDER_SIZE = 1;
	int DEFAULT_BUTTON_HOVERED_BORDER_SIZE = 1;
	int DEFAULT_BUTTON_PRESSED_BORDER_SIZE = 1;
	boolean[] DEFAULT_BUTTON_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_BUTTON_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_BUTTON_PRESSED_SHOWED_BORDERS = {true, true, true, true};
	Color DEFAULT_BUTTON_SELECTED_BACKGROUND = Color.RED;
	Color DEFAULT_BUTTON_SELECTED_HOVERED_BACKGROUND = Color.RED;
	Color DEFAULT_BUTTON_SELECTED_PRESSED_BACKGROUND = Color.RED;
	Color DEFAULT_BUTTON_SELECTED_FOREGROUND = Color.WHITE;
	Color DEFAULT_BUTTON_SELECTED_HOVERED_FOREGROUND = Color.WHITE;
	Color DEFAULT_BUTTON_SELECTED_PRESSED_FOREGROUND = Color.WHITE;
	Color DEFAULT_BUTTON_SELECTED_BORDER = Color.WHITE;
	Color DEFAULT_BUTTON_SELECTED_HOVERED_BORDER = Color.WHITE;
	Color DEFAULT_BUTTON_SELECTED_PRESSED_BORDER = Color.WHITE;
	int DEFAULT_BUTTON_SELECTED_BORDER_SIZE = 1;
	int DEFAULT_BUTTON_SELECTED_HOVERED_BORDER_SIZE = 1;
	int DEFAULT_BUTTON_SELECTED_PRESSED_BORDER_SIZE = 1;
	boolean[] DEFAULT_BUTTON_SELECTED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	Color DEFAULT_LABEL_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_LABEL_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_LABEL_PRESSED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_LABEL_FOREGROUND = Color.WHITE;
	Color DEFAULT_LABEL_HOVERED_FOREGROUND = Color.WHITE;
	Color DEFAULT_LABEL_PRESSED_FOREGROUND = Color.WHITE;
	Color DEFAULT_LABEL_BORDER = null;
	Color DEFAULT_LABEL_HOVERED_BORDER = null;
	Color DEFAULT_LABEL_PRESSED_BORDER = null;
	int DEFAULT_LABEL_BORDER_SIZE = 1;
	int DEFAULT_LABEL_HOVERED_BORDER_SIZE = 1;
	int DEFAULT_LABEL_PRESSED_BORDER_SIZE = 1;
	boolean[] DEFAULT_LABEL_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_LABEL_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_LABEL_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	/*
	 * Constantes liées au style des zones de texte
	 */

	Color DEFAULT_TEXTAREA_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_TEXTAREA_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_TEXTAREA_FOCUSED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_TEXTAREA_FOREGROUND = Color.WHITE;
	Color DEFAULT_TEXTAREA_HOVERED_FOREGROUND = Color.WHITE;
	Color DEFAULT_TEXTAREA_FOCUSED_FOREGROUND = Color.WHITE;
	Color DEFAULT_TEXTAREA_BORDER = null;
	Color DEFAULT_TEXTAREA_HOVERED_BORDER = null;
	Color DEFAULT_TEXTAREA_FOCUSED_BORDER = null;
	int DEFAULT_TEXTAREA_BORDER_SIZE = 1;
	int DEFAULT_TEXTAREA_HOVERED_BORDER_SIZE = 1;
	int DEFAULT_TEXTAREA_FOCUSED_BORDER_SIZE = 1;
	boolean[] DEFAULT_TEXTAREA_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_TEXTAREA_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_TEXTAREA_FOCUSED_SHOWED_BORDERS = {true, true, true, true};

	/*
	 * Constantes liées au style des zones des barres de menus
	 */

	Color DEFAULT_MENU_BAR_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_MENU_BAR_BORDER = null;
	int DEFAULT_MENU_BAR_BORDER_SIZE = 1;
	boolean[] DEFAULT_MENU_BAR_SHOWED_BORDERS = {false, false, false, false};

	/*
	 * Constantes liées au style des items des barres de menus
	 */

	Color DEFAULT_MENU_ITEM_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_MENU_ITEM_FOREGROUND = Color.WHITE;

	/*
	 * Constantes liées au style d'un menu
	 */

	Color DEFAULT_MENU_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_MENU_HOVERED_BACKGROUND = new Color(100, 100, 100);
	Color DEFAULT_MENU_FOREGROUND = Color.WHITE;
	Color DEFAULT_MENU_HOVERED_FOREGROUND = Color.WHITE;
	Color DEFAULT_MENU_POPUP_BACKGROUND = Color.WHITE;
	int DEFAULT_MENU_POPUP_BORDER_SIZE = 1;
	boolean[] DEFAULT_MENU_POPUP_SHOWED_BORDER = {false, false, false, false};

	/*
	 * Constantes liées au style d'un panneau
	 */

	Color DEFAULT_PANEL_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_PANEL_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_PANEL_PRESSED_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_PANEL_BORDER = null;
	Color DEFAULT_PANEL_HOVERED_BORDER = null;
	Color DEFAULT_PANEL_PRESSED_BORDER = null;
	int DEFAULT_PANEL_BORDER_SIZE = 1;
	int DEFAULT_PANEL_HOVERED_BORDER_SIZE = 1;
	int DEFAULT_PANEL_PRESSED_BORDER_SIZE = 1;
	boolean[] DEFAULT_PANEL_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_PANEL_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] DEFAULT_PANEL_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	/*
	 * Constantes liées au style d'un popup menu
	 */

	Color DEFAULT_POPUP_MENU_BACKGROUND = Color.DARK_GRAY;
	Color DEFAULT_POPUP_MENU_BORDER = null;
	int DEFAULT_POPUP_MENU_BORDER_SIZE = 1;
	boolean[] DEFAULT_POPUP_MENU_SHOWED_BORDER = {true, true, true, true};

	/*
	 * Constantes liées au style d'une liste déroulante
	 */

	Color DEFAULT_COMBOBOX_BORDER = Color.DARK_GRAY;
	int DEFAULT_COMBOBOX_BORDER_SIZE = 3;
	boolean[] DEFAULT_COMBOBOX_SHOWED_BORDERS = {true, true, true, true};
}

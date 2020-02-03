package data.config;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

/**
 * Des valeurs clef pour l'ui du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 3.0
 */
public interface EnigmaUIValues {

	Font ENIGMA_FONT = new Font("Monospaced", Font.BOLD, 15);
	boolean[] ALL_BORDERS_SHOWED = {true, true, true, true};
	boolean[] ALL_BORDER_HIDDEN = {false, false, false, false};
	boolean SHOWED_BORDER = true;
	boolean HIDDEN_BORDER = false;
	int TOP_BORDER = 0;
	int RIGHT_BORDER = 1;
	int BOTTOM_BORDER = 2;
	int LEFT_BORDER = 3;

	Color ENIGMA_BUTTON_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_BUTTON_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_BUTTON_PRESSED_BACKGROUND = new Color(100, 100, 100);
	Color ENIGMA_BUTTON_FOREGROUND = Color.WHITE;
	Color ENIGMA_BUTTON_HOVERED_FOREGROUND = Color.CYAN;
	Color ENIGMA_BUTTON_PRESSED_FOREGROUND = Color.CYAN;
	Color ENIGMA_BUTTON_BORDER = Color.WHITE;
	Color ENIGMA_BUTTON_HOVERED_BORDER = Color.CYAN;
	Color ENIGMA_BUTTON_PRESSED_BORDER = Color.CYAN;
	int ENIGMA_BUTTON_BORDER_SIZE = 1;
	int ENIGMA_BUTTON_HOVERED_BORDER_SIZE = 1;
	int ENIGMA_BUTTON_PRESSED_BORDER_SIZE = 1;
	boolean[] ENIGMA_BUTTON_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_BUTTON_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_BUTTON_PRESSED_SHOWED_BORDERS = {true, true, true, true};
	Color ENIGMA_BUTTON_SELECTED_BACKGROUND = Color.RED;
	Color ENIGMA_BUTTON_SELECTED_HOVERED_BACKGROUND = Color.RED;
	Color ENIGMA_BUTTON_SELECTED_PRESSED_BACKGROUND = Color.RED;
	Color ENIGMA_BUTTON_SELECTED_FOREGROUND = Color.WHITE;
	Color ENIGMA_BUTTON_SELECTED_HOVERED_FOREGROUND = Color.WHITE;
	Color ENIGMA_BUTTON_SELECTED_PRESSED_FOREGROUND = Color.WHITE;
	Color ENIGMA_BUTTON_SELECTED_BORDER = Color.WHITE;
	Color ENIGMA_BUTTON_SELECTED_HOVERED_BORDER = Color.WHITE;
	Color ENIGMA_BUTTON_SELECTED_PRESSED_BORDER = Color.WHITE;
	int ENIGMA_BUTTON_SELECTED_BORDER_SIZE = 1;
	int ENIGMA_BUTTON_SELECTED_HOVERED_BORDER_SIZE = 1;
	int ENIGMA_BUTTON_SELECTED_PRESSED_BORDER_SIZE = 1;
	boolean[] ENIGMA_BUTTON_SELECTED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	Color ENIGMA_LABEL_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_LABEL_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_LABEL_PRESSED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_LABEL_FOREGROUND = Color.WHITE;
	Color ENIGMA_LABEL_HOVERED_FOREGROUND = Color.WHITE;
	Color ENIGMA_LABEL_PRESSED_FOREGROUND = Color.WHITE;
	Color ENIGMA_LABEL_BORDER = null;
	Color ENIGMA_LABEL_HOVERED_BORDER = null;
	Color ENIGMA_LABEL_PRESSED_BORDER = null;
	int ENIGMA_LABEL_BORDER_SIZE = 1;
	int ENIGMA_LABEL_HOVERED_BORDER_SIZE = 1;
	int ENIGMA_LABEL_PRESSED_BORDER_SIZE = 1;
	boolean[] ENIGMA_LABEL_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_LABEL_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_LABEL_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	Color ENIGMA_TEXTAREA_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_TEXTAREA_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_TEXTAREA_FOCUSED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_TEXTAREA_FOREGROUND = Color.WHITE;
	Color ENIGMA_TEXTAREA_HOVERED_FOREGROUND = Color.WHITE;
	Color ENIGMA_TEXTAREA_FOCUSED_FOREGROUND = Color.WHITE;
	Color ENIGMA_TEXTAREA_BORDER = null;
	Color ENIGMA_TEXTAREA_HOVERED_BORDER = null;
	Color ENIGMA_TEXTAREA_FOCUSED_BORDER = null;
	int ENIGMA_TEXTAREA_BORDER_SIZE = 1;
	int ENIGMA_TEXTAREA_HOVERED_BORDER_SIZE = 1;
	int ENIGMA_TEXTAREA_FOCUSED_BORDER_SIZE = 1;
	boolean[] ENIGMA_TEXTAREA_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_TEXTAREA_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_TEXTAREA_FOCUSED_SHOWED_BORDERS = {true, true, true, true};

	Color ENIGMA_MENU_BAR_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_MENU_BAR_BORDER = null;
	int ENIGMA_MENU_BAR_BORDER_SIZE = 1;
	boolean[] ENIGMA_MENU_BAR_SHOWED_BORDERS = {false, false, false, false};

	Color ENIGMA_MENU_ITEM_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_MENU_ITEM_FOREGROUND = Color.WHITE;

	Color ENIGMA_MENU_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_MENU_HOVERED_BACKGROUND = new Color(100, 100, 100);
	Color ENIGMA_MENU_FOREGROUND = Color.WHITE;
	Color ENIGMA_MENU_HOVERED_FOREGROUND = Color.WHITE;
	Color ENIGMA_MENU_POPUP_BACKGROUND = Color.WHITE;
	int ENIGMA_MENU_POPUP_BORDER_SIZE = 1;
	boolean[] ENIGMA_MENU_POPUP_SHOWED_BORDER = {false, false, false, false};

	Color ENIGMA_PANEL_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_PANEL_HOVERED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_PANEL_PRESSED_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_PANEL_BORDER = null;
	Color ENIGMA_PANEL_HOVERED_BORDER = null;
	Color ENIGMA_PANEL_PRESSED_BORDER = null;
	int ENIGMA_PANEL_BORDER_SIZE = 1;
	int ENIGMA_PANEL_HOVERED_BORDER_SIZE = 1;
	int ENIGMA_PANEL_PRESSED_BORDER_SIZE = 1;
	boolean[] ENIGMA_PANEL_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_PANEL_HOVERED_SHOWED_BORDERS = {true, true, true, true};
	boolean[] ENIGMA_PANEL_PRESSED_SHOWED_BORDERS = {true, true, true, true};

	Color ENIGMA_POPUP_MENU_BACKGROUND = Color.DARK_GRAY;
	Color ENIGMA_POPUP_MENU_BORDER = null;
	int ENIGMA_POPUP_MENU_BORDER_SIZE = 1;
	boolean[] ENIGMA_POPUP_MENU_SHOWED_BORDER = {true, true, true, true};

	Color ENIGMA_COMBOBOX_BORDER = Color.DARK_GRAY;
	int ENIGMA_COMBOBOX_BORDER_SIZE = 3;
	boolean[] ENIGMA_COMBOBOX_SHOWED_BORDERS = {true, true, true, true};
	Insets ENIGMA_COMBOX_INSET = new Insets(4, 4, 4, 4);
}
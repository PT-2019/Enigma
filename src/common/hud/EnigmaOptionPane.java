package common.hud;

import api.ui.CustomButton;
import api.ui.CustomOptionPane;
import api.ui.CustomPanel;
import api.ui.CustomWindow;
import api.ui.base.OptionPaneStyle;
import api.ui.skin.CustomButtonUI;
import common.hud.ui.EnigmaTextAreaUI;
import data.config.EnigmaUIValues;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Un panneau de choix modal d'enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaOptionPane extends CustomOptionPane implements OptionPaneStyle {

	private EnigmaOptionPane() {
		super(new EnigmaAlert());
	}

	/**
	 * Retourne le style de base d'un bouton de l'option bane
	 *
	 * @param text contenu du bouton
	 * @return le bouton auquel le style a été appliqué
	 */
	public static CustomButton getClassicButton(String text) {
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		CustomButton b = new CustomButton(text);
		CustomButtonUI bui = b.getComponentUI();
		bui.setAllBorders(null, Color.WHITE, Color.WHITE);
		bui.setAllBackgrounds(grey, grey, new Color(50, 150, 200));
		bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, borders, borders);
		bui.setAllBordersSize(1, 1, 1);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		return b;
	}

	/**
	 * Retourne le style de base d'un champ de saisie de l'option bane
	 *
	 * @return le champ de saisie auquel le style a été appliqué
	 */
	public static EnigmaTextArea getClassicTextArea() {
		EnigmaTextArea ta = new EnigmaTextArea();
		boolean[] borders = EnigmaUIValues.ALL_BORDER_HIDDEN;
		borders[EnigmaUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		EnigmaTextAreaUI tui = ta.getComponentUI();
		tui.setAllBackgrounds(grey, grey, grey);
		tui.setAllBorders(null, Color.WHITE, Color.WHITE);
		tui.setAllBordersSize(1, 1, 1);
		tui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, borders, borders);
		return ta;
	}

	/**
	 * Affiche une fenêtre de confirmation
	 *
	 * @param parent  fenêtre parent
	 * @param size    taille popup
	 * @param message contenu de la popup
	 * @return true si confirmer a été saisi sinon false
	 * @since 4.0
	 */
	public static boolean showConfirmDialog(CustomWindow parent, Dimension size, String message) {
		return showConfirmDialog(parent, size, message, new EnigmaOptionPane());
	}

	/**
	 * Affiche une fenêtre de confirmation
	 *
	 * @param parent  parent
	 * @param message message
	 * @return true si confirmer a été saisi sinon false
	 * @since 4.0
	 */
	public static boolean showConfirmDialog(CustomWindow parent, String message) {
		return showConfirmDialog(parent, message, new EnigmaOptionPane());
	}

	/**
	 * Crée un panneau de choix et retourne la string contenue par le bouton choisi
	 *
	 * @param parent  parent
	 * @param message messages
	 * @param buttons des boutons
	 * @return la string contenue par le bouton choisi
	 * @since 4.0
	 */
	public static String showChoicesDialog(CustomWindow parent, String message, CustomButton[] buttons) {
		return showChoicesDialog(parent, message, buttons, new EnigmaOptionPane());
	}

	/**
	 * Crée un popup de saisie et retourne le message saisi
	 *
	 * @param parent  parent
	 * @param message la question qui demande une saisie
	 * @return le message saisi
	 * @since 4.0
	 */
	public static String showInputDialog(CustomWindow parent, String message) {
		return showInputDialog(parent, message, new EnigmaOptionPane());
	}

	// réécriture des méthodes static avec le bon style

	/**
	 * Affiche un popup
	 *
	 * @param parent  parent
	 * @param message message
	 * @since 4.0
	 */
	public static void showAlert(CustomWindow parent, String message) {
		showAlert(parent, message, new EnigmaOptionPane());
	}

	/**
	 * Affiche un panneau de choix
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @param buttons des boutons
	 * @return le texte du bouton choisi
	 * @since 4.0
	 */
	public static String showChoicesDialog(CustomWindow parent, Dimension size, String message, CustomButton[] buttons) {
		return showChoicesDialog(parent, size, message, buttons, new EnigmaOptionPane());
	}

	/**
	 * Affiche un popup personnalisé
	 *
	 * @param parent  parent
	 * @param message possiblement un tableau, des composants a afficher dans le popup
	 * @param title   titre de la fenêtre
	 * @param options option (ok, confirmer, ...), par exemple un tableau de string
	 * @return la position dans le tableau d'options choisie
	 */
	public static int showOptionDialog(CustomWindow parent, Object message, String title, String[] options) {
		return showOptionDialog(parent, message, title, options, new EnigmaOptionPane());
	}

	/**
	 * Crée une fenêtre de saisie
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @return la réponse saisie
	 * @since 4.0
	 */
	public static String showInputDialog(CustomWindow parent, Dimension size, String message) {
		return showInputDialog(parent, size, message, new EnigmaOptionPane());
	}

	/**
	 * Affiche un popup
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @since 4.0
	 */
	public static void showAlert(CustomWindow parent, Dimension size, String message) {
		showAlert(parent, size, message, new EnigmaOptionPane());
	}

	@Override
	public CustomButton getButtonStyle(String text) {
		return getClassicButton(text);
	}

	@Override
	public EnigmaTextArea getTextAreaStyle() {
		return getClassicTextArea();
	}

	@Override
	public CustomPanel getPanelStyle() {
		return new EnigmaPanel();
	}

	@Override
	public EnigmaLabel getLabelStyle(String content) {
		return new EnigmaLabel(content);
	}
}

package common.hud;

import api.ui.CustomAlert;
import api.ui.CustomButton;
import api.ui.CustomOptionPane;
import api.ui.CustomPanel;
import api.ui.CustomTextField;
import api.ui.CustomWindow;
import api.ui.base.DefaultUIValues;
import api.ui.base.OptionPaneStyle;
import api.ui.skin.CustomButtonUI;
import api.utils.Utility;
import api.utils.annotations.ConvenienceMethod;
import common.hud.ui.EnigmaTextAreaUI;
import common.hud.ui.EnigmaTextFieldUI;
import data.NeedToBeTranslated;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Un panneau de choix modal d'enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 */
@SuppressWarnings("unused")
public class EnigmaOptionPane extends CustomOptionPane implements OptionPaneStyle {

	private EnigmaOptionPane() {
		super(new EnigmaAlert());
	}

	protected EnigmaOptionPane(CustomAlert alert) {
		super(alert);
	}

	/**
	 * Retourne le style de base d'un bouton de l'option bane
	 *
	 * @param text contenu du bouton
	 * @return le bouton auquel le style a été appliqué
	 */
	private static CustomButton getClassicButton(String text) {
		boolean[] borders = new boolean[4];
		borders[DefaultUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		CustomButton b = new CustomButton(text);
		CustomButtonUI bui = b.getComponentUI();
		bui.setAllBorders(null, Color.WHITE, Color.WHITE);
		bui.setAllBackgrounds(grey, grey, new Color(50, 150, 200));
		bui.setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN, borders, borders);
		bui.setAllBordersSize(1, 1, 1);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		return b;
	}

	/**
	 * Retourne le style de base d'un champ de saisie de l'option bane
	 *
	 * @return le champ de saisie auquel le style a été appliqué
	 */
	private static EnigmaTextArea getClassicTextArea() {
		EnigmaTextArea ta = new EnigmaTextArea();
		boolean[] borders = DefaultUIValues.ALL_BORDER_HIDDEN;
		borders[DefaultUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		EnigmaTextAreaUI tui = ta.getComponentUI();
		tui.setAllBackgrounds(grey, grey, grey);
		tui.setAllBorders(null, Color.WHITE, Color.WHITE);
		tui.setAllBordersSize(1, 1, 1);
		tui.setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN, borders, borders);
		return ta;
	}

	/**
	 * Retourne le style de base d'un champ de saisie de l'option bane
	 *
	 * @return le champ de saisie auquel le style a été appliqué
	 */
	private static EnigmaTextField getClassicTextField() {
		EnigmaTextField ta = new EnigmaTextField();
		boolean[] borders = DefaultUIValues.ALL_BORDER_HIDDEN;
		borders[DefaultUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		EnigmaTextFieldUI tui = ta.getComponentUI();
		tui.setAllBackgrounds(grey, grey, grey);
		tui.setAllBorders(null, Color.WHITE, Color.WHITE);
		tui.setAllBordersSize(1, 1, 1);
		tui.setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN, borders, borders);
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

	/**
	 * Crée un popup de choix de map
	 *
	 * @param parent parent
	 * @return le nom de la map sélectionnée
	 * @since 5.0
	 */
	public static String showMapChoiceDialog(CustomWindow parent) {
		return showMapChoiceDialog(parent, new EnigmaOptionPane());
	}

	/**
	 * Crée un popup de choix pourla musique
	 *
	 * @param parent  parent
	 * @return le nom de la map séléctionnée
	 * @since 5.0
	 */
	public static String showMusicChoiceDialog(CustomWindow parent) {
		return showMusicChoiceDialog(parent, new EnigmaOptionPane());
	}

	/**
	 * Crée un popup de choix pour les sons
	 *
	 * @param parent  parent
	 * @return le nom de la map séléctionnée
	 * @since 5.0
	 */
	public static String showSoundChoiceDialog(CustomWindow parent) {
		return showSoundChoiceDialog(parent, new EnigmaOptionPane());
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
	 * Crée un popup de choix de map
	 *
	 * @param parent parent
	 * @param size   taille
	 * @return le nom de la map sélectionnée
	 * @since 5.0
	 */
	@ConvenienceMethod
	public static String showMapChoiceDialog(CustomWindow parent, Dimension size) {
		return showListDialog(parent, NeedToBeTranslated.ASK_SELECT_MAP, size, Utility.getAllMapName(),
				new EnigmaOptionPane());
	}

	/**
	 * Crée un popup avec une liste de choix.
	 *
	 * @param parent parent
	 * @param size   taille
	 * @return le nom du choix sélectionné
	 * @since 5.0
	 */
	public static String showListDialog(CustomWindow parent, String title, Dimension size, ArrayList<String> choices) {
		return showListDialog(parent, title, size, choices, new EnigmaOptionPane());
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

	/**
	 * Retourne le style de l'enigma option pane
	 *
	 * @return le style de l'enigma option pane
	 * @since 6.0
	 */
	public static OptionPaneStyle getStyle() {
		return new EnigmaOptionPane();
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
	public CustomTextField getTextFieldStyle() {
		return getClassicTextField();
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
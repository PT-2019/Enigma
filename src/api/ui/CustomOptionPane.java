package api.ui;

import api.ui.base.DefaultUIValues;
import api.ui.base.OptionPaneStyle;
import api.ui.manager.CustomOptionPaneButtonManager;
import api.ui.manager.CustomOptionPaneWindowManager;
import api.ui.manager.RadioButtonManager;
import api.ui.skin.CustomButtonUI;
import api.ui.skin.CustomTextAreaUI;
import api.ui.skin.CustomTextFieldUI;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

/**
 * Un panneau de choix modal customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0 28/12/2019
 */
@SuppressWarnings("unused")
public class CustomOptionPane implements OptionPaneStyle {

	/**
	 * Choix de base : confirmer
	 */
	public final static String CONFIRM = "Confirmer";
	public final static String CANCEL = "Annuler";

	/**
	 * Taille de base
	 */
	protected final static Dimension BASIC_DIMENSION = new Dimension(500, 300);

	/**
	 * Taille de base
	 */
	private final static Dimension SMALL_DIMENSION = new Dimension(300, 300);

	/**
	 * l'alert
	 */
	private CustomAlert window;
	private CustomWindow parent;
	private String answer;
	private CustomTextArea input;
	private RadioButtonManager rbm;

	/**
	 * Crée un optionPane avec le style de base
	 */
	private CustomOptionPane() {
		this.window = new CustomAlert();
		this.parent = null;
		this.input = null;
		this.answer = CANCEL;
	}

	/**
	 * Crée un option pane dans une alert
	 *
	 * @param alert l'alert
	 */
	protected CustomOptionPane(CustomAlert alert) {
		this.window = alert;
		this.parent = null;
		this.input = null;
		this.answer = CANCEL;
	}

	/**
	 * Crée un optionPane
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	private CustomOptionPane(CustomAlert window, CustomWindow parent) {
		this.window = window;
		this.parent = parent;
		this.input = null;
		this.answer = CANCEL;
	}

	/**
	 * Crée un optinoPane
	 *
	 * @param window fenêtre
	 * @param parent parent
	 * @param input  champ de saisie
	 */
	private CustomOptionPane(CustomAlert window, CustomWindow parent, CustomTextArea input) {
		this.window = window;
		this.parent = parent;
		this.input = input;
		this.answer = CANCEL;
	}

	/**
	 * Crée un optinoPane
	 *
	 * @param window fenêtre
	 * @param parent parent
	 * @param rbm    gestionnaire de radio boutons
	 */
	private CustomOptionPane(CustomAlert window, CustomWindow parent, RadioButtonManager rbm) {
		this.window = window;
		this.parent = parent;
		this.rbm = rbm;
		this.answer = CANCEL;
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
	private static CustomTextArea getClassicTextArea() {
		CustomTextArea ta = new CustomTextArea();
		boolean[] borders = DefaultUIValues.ALL_BORDER_HIDDEN;
		borders[DefaultUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		CustomTextAreaUI tui = ta.getComponentUI();
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
	private static CustomTextField getClassicTextField() {
		CustomTextField ta = new CustomTextField();
		boolean[] borders = DefaultUIValues.ALL_BORDER_HIDDEN;
		borders[DefaultUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		CustomTextFieldUI tui = ta.getComponentUI();
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
		return showConfirmDialog(parent, size, message, new CustomOptionPane());
	}

	/**
	 * Affiche une fenêtre de confirmation
	 *
	 * @param parent  fenêtre parent
	 * @param size    taille popup
	 * @param message contenu de la popup
	 * @param style   style de la popup
	 * @return true si confirmer a été saisi sinon false
	 * @since 4.1
	 */
	protected static boolean showConfirmDialog(CustomWindow parent, Dimension size, String message, OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		CustomButton[] buttons = new CustomButton[2];
		buttons[0] = style.getButtonStyle(CONFIRM);
		buttons[1] = style.getButtonStyle(CANCEL);
		for (CustomButton b : buttons) {
			b.addActionListener(new CustomOptionPaneButtonManager(optionPane));
			answersComponent.add(b);

		}
		buttons[1].getComponentUI().setPressedBackground(Color.RED);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(size);
		window.setSize(size);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();

		return optionPane.getAnswer().equals(CONFIRM);
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
		return showConfirmDialog(parent, message, new CustomOptionPane());
	}

	/**
	 * Affiche une fenêtre de confirmation
	 *
	 * @param parent  parent
	 * @param message message
	 * @param style   style
	 * @return true si confirmer a été saisi sinon false
	 * @since 4.1
	 */
	protected static boolean showConfirmDialog(CustomWindow parent, String message, OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		CustomButton[] buttons = new CustomButton[2];
		buttons[0] = style.getButtonStyle(CONFIRM);
		buttons[1] = style.getButtonStyle(CANCEL);
		for (CustomButton b : buttons) {
			b.addActionListener(new CustomOptionPaneButtonManager(optionPane));
			answersComponent.add(b);

		}
		buttons[1].getComponentUI().setPressedBackground(Color.RED);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(BASIC_DIMENSION);
		window.setSize(BASIC_DIMENSION);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer().equals(CONFIRM);
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
		return showChoicesDialog(parent, message, buttons, new CustomOptionPane());
	}

	/**
	 * Crée un panneau de choix et retourne la string contenue par le bouton choisi
	 *
	 * @param parent  parent
	 * @param message messages
	 * @param buttons des boutons
	 * @param style   style
	 * @return la string contenue par le bouton choisi
	 * @since 4.1
	 */
	protected static String showChoicesDialog(CustomWindow parent, String message, CustomButton[] buttons, OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		for (CustomButton b : buttons) {
			b.addActionListener(new CustomOptionPaneButtonManager(optionPane));
			answersComponent.add(b);
		}

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(BASIC_DIMENSION);
		window.setSize(BASIC_DIMENSION);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer();
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
		return showInputDialog(parent, message, new CustomOptionPane());
	}

	/**
	 * Crée un popup de saisie et retourne le message saisi
	 *
	 * @param parent  parent
	 * @param message la question qui demande une saisie
	 * @param style   style
	 * @return le message saisi
	 * @since 4.1
	 */
	protected static String showInputDialog(CustomWindow parent, String message, OptionPaneStyle style) {
		CustomAlert window = style.getWindow();
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomTextArea answerComponent = style.getTextAreaStyle();
		CustomPanel confirmComponent = style.getPanelStyle();
		CustomButton confirm = style.getButtonStyle(CONFIRM);
		CustomOptionPane optionPane = new CustomOptionPane(window, parent, answerComponent);

		confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(BASIC_DIMENSION);
		window.setSize(BASIC_DIMENSION);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answerComponent.setScrollBar());
		window.getContentSpace().add(confirmComponent);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer();
	}

	/**
	 * Affiche un popup
	 *
	 * @param parent  parent
	 * @param message message
	 * @since 4.0
	 */
	public static void showAlert(CustomWindow parent, String message) {
		showAlert(parent, message, new CustomOptionPane());
	}

	/**
	 * Affiche un popup
	 *
	 * @param parent  parent
	 * @param message message
	 * @param style   style
	 * @since 4.1
	 */
	protected static void showAlert(CustomWindow parent, String message, OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		CustomButton confirm = style.getButtonStyle(CONFIRM);
		confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
		answersComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(BASIC_DIMENSION);
		window.setSize(BASIC_DIMENSION);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();
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
		return showChoicesDialog(parent, size, message, buttons, new CustomOptionPane());
	}

	/**
	 * Affiche un panneau de choix
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @param buttons des boutons
	 * @param style   un style
	 * @return le texte du bouton choisi
	 * @since 4.1
	 */
	protected static String showChoicesDialog(CustomWindow parent, Dimension size, String message, CustomButton[] buttons,
	                                          OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		for (CustomButton b : buttons) {
			b.addActionListener(new CustomOptionPaneButtonManager(optionPane));
			answersComponent.add(b);
		}

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(size);
		window.setSize(size);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer();
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
		return showInputDialog(parent, size, message, new CustomOptionPane());
	}

	/**
	 * Crée une fenêtre de saisie
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @param style   style
	 * @return la réponse saisie
	 * @since 4.1
	 */
	protected static String showInputDialog(CustomWindow parent, Dimension size, String message, OptionPaneStyle style) {
		CustomAlert window = style.getWindow();
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomTextArea answerComponent = style.getTextAreaStyle();
		CustomPanel confirmComponent = style.getPanelStyle();
		CustomButton confirm = style.getButtonStyle(CONFIRM);
		CustomOptionPane optionPane = new CustomOptionPane(window, parent, answerComponent);

		confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(size);
		window.setSize(size);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answerComponent.setScrollBar());
		window.getContentSpace().add(confirmComponent);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer();
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
		showAlert(parent, size, message, new CustomOptionPane());
	}

	/**
	 * Crée un popup avec une liste de choix.
	 *
	 * @param parent parent
	 * @param size   taille
	 * @return le nom du choix sélectionné
	 * @since 6.0
	 */
	public static String showListDialog(CustomWindow parent, String title, Dimension size, ArrayList<String> choices) {
		return showListDialog(parent, title, size, choices, new CustomOptionPane());
	}

	/**
	 * Crée un popup avec une liste de choix.
	 *
	 * @param parent parent
	 * @return le nom du choix sélectionné
	 * @since 6.0
	 */
	public static String showListDialog(CustomWindow parent, String title, ArrayList<String> choices) {
		return showListDialog(parent, title, BASIC_DIMENSION, choices, new CustomOptionPane());
	}

	/**
	 * Crée un popup avec une liste de choix.
	 *
	 * @param parent parent
	 * @param size   taille
	 * @return le nom du choix sélectionné
	 * @since 6.0
	 */
	protected static String showListDialog(CustomWindow parent, String title, Dimension size,
	                                       ArrayList<String> choices, OptionPaneStyle style) {
		final int MIN = 5;

		CustomAlert window = style.getWindow();
		CustomLabel titleComponent = style.getLabelStyle(title);
		CustomPanel confirmComponent = style.getPanelStyle();
		CustomButton confirm = style.getButtonStyle(CONFIRM);
		CustomPanel mapsComponent = style.getPanelStyle();
		JScrollPane scroll = new JScrollPane(mapsComponent);
		RadioButtonManager rbm = new RadioButtonManager();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent, rbm);
		GridBagConstraints gbc = new GridBagConstraints();

		confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(size);
		window.setSize(size);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		scroll.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		CustomButtonUI bui = style.getButtonStyle("").ui;
		bui.setAllSelectedBackgrounds(Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		bui.setAllBackgrounds(bui.getBackground(), Color.LIGHT_GRAY, Color.LIGHT_GRAY);
		bui.setAllBorders(null, null, null);
		bui.setAllSelectedBorders(null, null, null);
		bui.setAllForegrounds(Color.WHITE, Color.BLACK, Color.BLACK);
		bui.setAllSelectedForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 0;

		if (choices.size() <= MIN) {
			mapsComponent.setLayout(new GridLayout(MIN, 1));
		} else {
			mapsComponent.setLayout(new GridLayout(choices.size(), 1));
		}

		for (String s : choices) {
			CustomButton b = new CustomButton(s);
			b.setComponentUI(bui);
			mapsComponent.add(b);
			rbm.add(b);
		}

		window.getContentSpace().setLayout(new GridBagLayout());
		window.getContentSpace().add(titleComponent, gbc);
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		window.getContentSpace().add(scroll, gbc);
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weighty = 0;
		window.getContentSpace().add(confirmComponent, gbc);
		window.setModal(true);

		optionPane.start();
		return optionPane.getAnswer();
	}

	/**
	 * Affiche un popup
	 *
	 * @param parent  parent
	 * @param size    taille
	 * @param message message
	 * @param style   style
	 * @since 4.1
	 */
	protected static void showAlert(CustomWindow parent, Dimension size, String message, OptionPaneStyle style) {
		CustomLabel questionComponent = style.getLabelStyle(message);
		CustomPanel answersComponent = style.getPanelStyle();
		CustomAlert window = style.getWindow();
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		CustomButton confirm = style.getButtonStyle(CONFIRM);
		confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
		answersComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(size);
		window.setSize(size);
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);
		window.setModal(true);

		optionPane.start();
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
		return showOptionDialog(parent, message, title, options, false, new CustomOptionPane());
	}

	/**
	 * Affiche un popup personnalisé
	 *
	 * @param parent         parent
	 * @param message        possiblement un tableau, des composants a afficher dans le popup
	 * @param title          titre de la fenêtre
	 * @param options        option (ok, confirmer, ...), par exemple un tableau de string
	 * @param preventDefault ne pas appliquer le style par défaut
	 * @param style          style à appliquer
	 * @return la position dans le tableau d'options choisie
	 */
	protected static int showOptionDialog(CustomWindow parent, Object message, String title, String[] options,
	                                      boolean preventDefault, OptionPaneStyle style) {
		CustomAlert window = style.getWindow();
		window.setTitle(title);

		//Création optionPane
		CustomOptionPane optionPane = new CustomOptionPane(window, parent);

		CustomPanel answersComponent = style.getPanelStyle();
		for (String o : options) {
			CustomButton confirm = style.getButtonStyle(o);
			confirm.addActionListener(new CustomOptionPaneButtonManager(optionPane));
			answersComponent.add(confirm);
		}

		CustomPanel questionComponent = style.getPanelStyle();
		if (message instanceof Object[]) {
			Object[] messageA = (Object[]) message;
			questionComponent.setLayout(new GridLayout());
			int sum = 0;
			if (!preventDefault) {
				for (int i = 0; i < messageA.length; i++, sum++) {
					Object o = messageA[i];
					if (o instanceof CustomTextArea) {
						((CustomTextArea) o).setComponentUI(style.getTextAreaStyle().getComponentUI());
					} else if (o instanceof CustomLabel) {
						((CustomLabel) o).setComponentUI(style.getLabelStyle("").getComponentUI());
					} else if (o instanceof CustomButton) {
						((CustomButton) o).setComponentUI(style.getButtonStyle("").getComponentUI());
					} else if (o instanceof CustomTextField) {
						((CustomTextField) o).setComponentUI(style.getTextFieldStyle().getComponentUI());
					}
					questionComponent.add((Component) o);
				}
				((GridLayout) questionComponent.getLayout()).setRows(sum);
				((GridLayout) questionComponent.getLayout()).setColumns(1);
			} else {
				//ajoute juste les composants
				questionComponent.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				for (int i = 0; i < messageA.length; i++, sum++) {
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.weightx = 1;
					gbc.gridwidth = 1;
					gbc.gridx = 0;
					gbc.insets = new Insets(5, 5, 5, 5);
					gbc.gridy = i;
					questionComponent.add((Component) messageA[i], gbc);
				}
			}
		}

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.pack();
		window.setLocation(CustomAlert.CENTER);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new CustomOptionPaneWindowManager(optionPane));

		//ajout des composants à la fenêtre
		window.getContentSpace().setLayout(new BorderLayout());
		window.getContentSpace().add(questionComponent, BorderLayout.CENTER);
		window.getContentSpace().add(answersComponent, BorderLayout.SOUTH);
		window.setModal(true);

		optionPane.start();

		for (int i = 0; i < options.length; i++) {
			if (optionPane.getAnswer().equals(options[i]))
				return i;
		}

		return -1;
	}

	@Override
	public CustomTextArea getTextAreaStyle() {
		return getClassicTextArea();
	}

	@Override
	public CustomButton getButtonStyle(String text) {
		return getClassicButton(text);
	}

	@Override
	public CustomPanel getPanelStyle() {
		return new CustomPanel();
	}

	@Override
	public CustomLabel getLabelStyle(String content) {
		return new CustomLabel(content);
	}

	@Override
	public CustomTextField getTextFieldStyle() {
		return getClassicTextField();
	}

	/**
	 * Affiche le popup
	 */
	public void start() {
		if (this.window != null)
			this.window.setVisible(true);
	}

	/**
	 * Ferme le popup
	 */
	public void close() {
		if (this.parent != null)
			this.parent.setAlwaysOnTop(false);

		if (this.window != null)
			this.window.dispose();
	}

	/**
	 * Retourne la réponse saisie
	 *
	 * @return la réponse saisie
	 */
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * Définit la réponse saisie
	 *
	 * @param answer la réponse saisie
	 */
	public void answer(String answer) {
		if (this.input != null) this.answer = this.input.getText();
		else if (this.rbm != null) {

			if (this.rbm.getSelectedButton() != null)
				this.answer = this.rbm.getSelectedButton().getText();
			else
				this.answer = CANCEL;
		} else
			this.answer = answer;
		this.close();
	}

	/**
	 * Ferme le popup
	 */
	public void cancel() {
		this.answer = CANCEL;
		this.close();
	}

	/**
	 * Retourne la fenêtre du popup
	 *
	 * @return la fenêtre du popup
	 */
	public CustomAlert getWindow() {
		return this.window;
	}
}

package editor.hud;

import editor.hud.managers.OptionPaneButtonManager;
import editor.hud.managers.OptionPaneWindowManager;
import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaTextAreaUI;
import editor.hud.ui.EnigmaUIValues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * TODO: comment EnigmaOptionPane and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaOptionPane {

	public final static String CONFIRM = "Confirmer";
	public final static String CANCEL = "Annuler";
	private String answer;
	private volatile boolean haveAnswered;
	private Window window;
	private Window parent;

	public EnigmaOptionPane(Window window, Window parent) {
		this.haveAnswered = false;
		this.answer = CANCEL;
		this.window = window;
		this.parent = parent;
	}

	public EnigmaOptionPane(Window parent, String message) {
		this.haveAnswered = false;
		this.answer = CANCEL;
		this.parent = parent;
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(0, 0);

		EnigmaButton[] buttons = new EnigmaButton[2];
		buttons[0] = getClassicButton(CONFIRM);
		buttons[1] = getClassicButton(CANCEL);
		for (EnigmaButton b : buttons) {
			b.addActionListener(new OptionPaneButtonManager(this));
			answersComponent.add(b);

		}
		buttons[1].getButtonUI().setPressedBackground(Color.RED);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(this));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		this.window = window;
	}

	public static EnigmaButton getClassicButton(String text) {
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		EnigmaButton b = new EnigmaButton(text);
		EnigmaButtonUI bui = b.getButtonUI();
		bui.setAllBorders(null, Color.WHITE, Color.WHITE);
		bui.setAllBackgrounds(grey, grey, new Color(50, 150, 200));
		bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, borders, borders);
		bui.setAllBordersSize(1, 1, 1);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		return b;
	}

	public static EnigmaTextArea getClassicTextArea() {
		EnigmaTextArea ta = new EnigmaTextArea();
		boolean[] borders = EnigmaUIValues.ALL_BORDER_HIDDEN;
		borders[EnigmaUIValues.BOTTOM_BORDER] = true;
		Color grey = new Color(100, 100, 100);
		EnigmaTextAreaUI tui = ta.getTextAreaUI();
		tui.setAllBackgrounds(grey, grey, grey);
		tui.setAllBorders(null, Color.WHITE, Color.WHITE);
		tui.setAllBordersSize(1, 1, 1);
		tui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, borders, borders);
		return ta;
	}

	public static boolean showConfirmDialog(Window parent, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(0, 0);//TODO: o0,. ???
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		EnigmaButton[] buttons = new EnigmaButton[2];
		buttons[0] = getClassicButton(CONFIRM);
		buttons[1] = getClassicButton(CANCEL);
		for (EnigmaButton b : buttons) {
			b.addActionListener(new OptionPaneButtonManager(optionPane));
			answersComponent.add(b);

		}
		buttons[1].getButtonUI().setPressedBackground(Color.RED);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		System.out.println(optionPane.getAnswer().equals(CONFIRM));
		return optionPane.getAnswer().equals(CONFIRM);
	}

	public static String showPersonalizedDialog(Window parent, String message, EnigmaButton[] buttons) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(0, 0);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		for (EnigmaButton b : buttons) {
			b.addActionListener(new OptionPaneButtonManager(optionPane));
			answersComponent.add(b);
		}

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return optionPane.getAnswer();
	}

	public static String showInputDialog(Window parent, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaTextArea answerComponent = getClassicTextArea();
		EnigmaPanel confirmComponent = new EnigmaPanel();
		EnigmaButton confirm = getClassicButton(CONFIRM);
		Window window = new Window(0, 0);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answerComponent.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return answerComponent.getText();
	}

	public static String showPersonalizedInputDialog(Window parent, String message, EnigmaTextArea area) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel confirmComponent = new EnigmaPanel();
		EnigmaButton confirm = getClassicButton(CONFIRM);
		Window window = new Window(0, 0);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(area.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return area.getText();
	}

	public static String showPersonalizedInputDialog(Window parent, String message, EnigmaTextArea area, EnigmaButton confirmButton) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel confirmComponent = new EnigmaPanel();
		Window window = new Window(0, 0);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirmButton.setText(CONFIRM);
		confirmButton.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirmButton);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(area.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return area.getText();
	}

	public static void showAlert(Window parent, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(0, 0);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		EnigmaButton confirm = getClassicButton(CONFIRM);
		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		answersComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		Dimension dim = new Dimension(300, 200);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(dim);
		window.setSize(dim);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
	}

	public static boolean showConfirmDialog(Window parent, Dimension windowSize, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		EnigmaButton[] buttons = new EnigmaButton[2];
		buttons[0] = getClassicButton(CONFIRM);
		buttons[1] = getClassicButton(CANCEL);
		for (EnigmaButton b : buttons) {
			b.addActionListener(new OptionPaneButtonManager(optionPane));
			answersComponent.add(b);

		}
		buttons[1].getButtonUI().setPressedBackground(Color.RED);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return optionPane.getAnswer().equals(CONFIRM);
	}

	public static String showPersonalizedDialog(Window parent, Dimension windowSize, String message, EnigmaButton[] buttons) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		for (EnigmaButton b : buttons) {
			b.addActionListener(new OptionPaneButtonManager(optionPane));
			answersComponent.add(b);
		}

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return optionPane.getAnswer();
	}

	public static String showInputDialog(Window parent, Dimension windowSize, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaTextArea answerComponent = getClassicTextArea();
		EnigmaPanel confirmComponent = new EnigmaPanel();
		EnigmaButton confirm = getClassicButton(CONFIRM);
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answerComponent.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return answerComponent.getText();
	}

	public static String showPersonalizedInputDialog(Window parent, Dimension windowSize, String message, EnigmaTextArea area) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel confirmComponent = new EnigmaPanel();
		EnigmaButton confirm = getClassicButton(CONFIRM);
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(area.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return area.getText();
	}

	public static String showPersonalizedInputDialog(Window parent, Dimension windowSize, String message, EnigmaTextArea area, EnigmaButton confirmButton) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel confirmComponent = new EnigmaPanel();
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		confirmButton.setText(CONFIRM);
		confirmButton.addActionListener(new OptionPaneButtonManager(optionPane));
		confirmComponent.add(confirmButton);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(3, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(area.setScrollBar());
		window.getContentSpace().add(confirmComponent);

		optionPane.show();
		optionPane.waitForAnswer();
		return area.getText();
	}

	public static void showAlert(Window parent, Dimension windowSize, String message) {
		EnigmaLabel questionComponent = new EnigmaLabel(message);
		EnigmaPanel answersComponent = new EnigmaPanel();
		Window window = new Window(windowSize.width, windowSize.height);
		EnigmaOptionPane optionPane = new EnigmaOptionPane(window, parent);

		EnigmaButton confirm = getClassicButton(CONFIRM);
		confirm.addActionListener(new OptionPaneButtonManager(optionPane));
		answersComponent.add(confirm);

		parent.setAlwaysOnTop(true);
		window.setAlwaysOnTop(true);
		window.setMinimumSize(windowSize);
		window.setSize(windowSize);
		window.setLocation(Window.CENTER);
		window.setResizable(false);
		window.showMinimizeButton(false);
		window.setWindowBackground(Color.DARK_GRAY);
		window.showBorder(Color.WHITE, 1);
		window.addWindowListener(new OptionPaneWindowManager(optionPane));

		window.getContentSpace().setLayout(new GridLayout(2, 1));
		window.getContentSpace().add(questionComponent);
		window.getContentSpace().add(answersComponent);

		optionPane.show();
		optionPane.waitForAnswer();
	}

	public void show() {
		this.window.setVisible(true);
		System.out.println("-----------");
	}

	public void close() {
		this.parent.setAlwaysOnTop(false);
		this.window.dispose();
	}

	public String getAnswer() {
		return this.answer;
	}

	public boolean haveAnswered() {
		return this.haveAnswered;
	}

	public void setHaveAnswered(boolean haveAnswered) {
		this.haveAnswered = haveAnswered;
	}

	public void setHaveAnswered(boolean haveAnswered, String answer) {
		this.haveAnswered = haveAnswered;
		this.answer = answer;
	}

	public void waitForAnswer() {
		while (!this.haveAnswered) System.out.flush();
	}
}

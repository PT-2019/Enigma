package editor.utils.enigmaOptionPane;


import editor.utils.EnigmaButton;
import editor.utils.EnigmaLabel;
import editor.utils.EnigmaPanel;
import editor.utils.EnigmaTextArea;
import editor.utils.alert.Alert;
import editor.utils.ui.EnigmaButtonUI;
import editor.utils.ui.EnigmaTextAreaUI;
import editor.utils.ui.EnigmaUIValues;
import editor.window.Window;

import java.awt.*;

public class EnigmaOptionPane {

    private Alert window;
    private Window parent;
    private String answer;
    private EnigmaTextArea input;

    public final static String CONFIRM = "Confirmer";
    public final static String CANCEL = "Annuler";
    private final static Dimension BASIC_DIMENSION = new Dimension(500,300);

    public EnigmaOptionPane(Alert window, Window parent){
        this.window = window;
        this.parent = parent;
        this.answer = CANCEL;
    }

    public EnigmaOptionPane(Alert window, Window parent, EnigmaTextArea input){
        this.window = window;
        this.parent = parent;
        this.input = input;
        this.answer = CANCEL;
    }

    private static EnigmaButton getClassicButton(String text){
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.BOTTOM_BORDER] = true;
        Color grey = new Color(100,100,100);
        EnigmaButton b = new EnigmaButton(text);
        EnigmaButtonUI bui = b.getButtonUI();
        bui.setAllBorders(null,Color.WHITE,Color.WHITE);
        bui.setAllBackgrounds(grey,grey,new Color(50,150,200));
        bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,borders,borders);
        bui.setAllBordersSize(1,1,1);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        return b;
    }

    private static EnigmaTextArea getClassicTextArea(){
        EnigmaTextArea ta = new EnigmaTextArea();
        boolean[] borders = EnigmaUIValues.ALL_BORDER_HIDDEN;
        borders[EnigmaUIValues.BOTTOM_BORDER] = true;
        Color grey = new Color(100,100,100);
        EnigmaTextAreaUI tui =  ta.getTextAreaUI();
        tui.setAllBackgrounds(grey,grey,grey);
        tui.setAllBorders(null,Color.WHITE,Color.WHITE);
        tui.setAllBordersSize(1,1,1);
        tui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,borders,borders);
        return ta;
    }

    public void start(){
        if(this.window != null)
            this.window.setVisible(true);
    }

    public void close(){
        if(this.parent != null)
            this.parent.setAlwaysOnTop(false);

        if(this.window != null)
            this.window.dispose();
    }

    public String getAnswer(){
        return this.answer;
    }

    public void answer(String answer){
        if(this.input != null) this.answer = this.input.getText();
        else this.answer = answer;
        this.close();
    }

    public void cancel(){
        this.answer = CANCEL;
        this.close();
    }

    public Alert getWindow(){
        return this.window;
    }

    public static boolean showConfirmDialog(Window parent, String message){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        EnigmaButton[] buttons = new EnigmaButton[2];
        buttons[0] = getClassicButton(CONFIRM);
        buttons[1] = getClassicButton(CANCEL);
        for(EnigmaButton b: buttons){
            b.addActionListener(new OptionPaneButtonManager(optionPane));
            answersComponent.add(b);

        }
        buttons[1].getButtonUI().setPressedBackground(Color.RED);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(BASIC_DIMENSION);
        window.setSize(BASIC_DIMENSION);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer().equals(CONFIRM);
    }

    public static String showChoicesDialog(Window parent, String message, EnigmaButton[] buttons){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        for(EnigmaButton b: buttons){
            b.addActionListener(new OptionPaneButtonManager(optionPane));
            answersComponent.add(b);
        }

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(BASIC_DIMENSION);
        window.setSize(BASIC_DIMENSION);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer();
    }

    public static String showInputDialog(Window parent, String message) {
        Alert window = new Alert();
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaTextArea answerComponent = getClassicTextArea();
        EnigmaPanel confirmComponent = new EnigmaPanel();
        EnigmaButton confirm = getClassicButton(CONFIRM);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent,answerComponent);

        confirm.addActionListener(new OptionPaneButtonManager(optionPane));
        confirmComponent.add(confirm);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(BASIC_DIMENSION);
        window.setSize(BASIC_DIMENSION);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(3,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answerComponent.setScrollBar());
        window.getContentSpace().add(confirmComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer();
    }

    public static void showAlert(Window parent, String message){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        EnigmaButton confirm = getClassicButton(CONFIRM);
        confirm.addActionListener(new OptionPaneButtonManager(optionPane));
        answersComponent.add(confirm);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(BASIC_DIMENSION);
        window.setSize(BASIC_DIMENSION);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
    }

    public static boolean showConfirmDialog(Window parent, Dimension size, String message){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        EnigmaButton[] buttons = new EnigmaButton[2];
        buttons[0] = getClassicButton(CONFIRM);
        buttons[1] = getClassicButton(CANCEL);
        for(EnigmaButton b: buttons){
            b.addActionListener(new OptionPaneButtonManager(optionPane));
            answersComponent.add(b);

        }
        buttons[1].getButtonUI().setPressedBackground(Color.RED);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(size);
        window.setSize(size);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer().equals(CONFIRM);
    }

    public static String showChoicesDialog(Window parent, Dimension size, String message, EnigmaButton[] buttons){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        for(EnigmaButton b: buttons){
            b.addActionListener(new OptionPaneButtonManager(optionPane));
            answersComponent.add(b);
        }

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(size);
        window.setSize(size);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer();
    }

    public static String showInputDialog(Window parent, Dimension size, String message) {
        Alert window = new Alert();
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaTextArea answerComponent = getClassicTextArea();
        EnigmaPanel confirmComponent = new EnigmaPanel();
        EnigmaButton confirm = getClassicButton(CONFIRM);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent,answerComponent);

        confirm.addActionListener(new OptionPaneButtonManager(optionPane));
        confirmComponent.add(confirm);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(size);
        window.setSize(size);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(3,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answerComponent.setScrollBar());
        window.getContentSpace().add(confirmComponent);
        window.setModal(true);

        optionPane.start();
        return optionPane.getAnswer();
    }

    public static void showAlert(Window parent, Dimension size, String message){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        Alert window = new Alert(0,0);
        EnigmaOptionPane optionPane = new EnigmaOptionPane(window,parent);

        EnigmaButton confirm = getClassicButton(CONFIRM);
        confirm.addActionListener(new OptionPaneButtonManager(optionPane));
        answersComponent.add(confirm);

        parent.setAlwaysOnTop(true);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(size);
        window.setSize(size);
        window.setLocation(Alert.CENTER);
        window.setWindowBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addWindowListener(new OptionPaneWindowManager(optionPane));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);
        window.setModal(true);

        optionPane.start();
    }
}
package editor.utils;

import editor.utils.managers.OptionPaneButtonManager;
import editor.utils.managers.ThreadE;
import editor.utils.ui.EnigmaButtonUI;
import editor.utils.ui.EnigmaTextAreaUI;
import editor.utils.ui.EnigmaUIValues;
import editor.window.Window;

import javax.swing.*;
import java.awt.*;

public class EnigmaOptionPanem extends EnigmaOptionPane{

    private String answer;
    private volatile boolean haveAnswered;
    private JDialog window;
    private Window parent;

    public final static String CONFIRM = "Confirmer";
    public final static String CANCEL = "Annuler";

    public EnigmaOptionPanem(JDialog window, Window parent){
        super(new Window(),new Window());
        this.haveAnswered = false;
        this.answer = CANCEL;
        this.window = window;
        this.parent = parent;
    }

    public EnigmaOptionPanem(Window parent, String message){
        super(parent,message);
        this.haveAnswered = false;
        this.answer = CANCEL;
        this.parent = parent;
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaPanel answersComponent = new EnigmaPanel();
        JDialog window = new JDialog();

        EnigmaButton[] buttons = new EnigmaButton[2];
        buttons[0] = getClassicButton(CONFIRM);
        buttons[1] = getClassicButton(CANCEL);
        for(EnigmaButton b: buttons){
            b.addActionListener(new OptionPaneButtonManager(this));
            answersComponent.add(b);

        }
        buttons[1].getButtonUI().setPressedBackground(Color.RED);

        parent.setAlwaysOnTop(true);
        Dimension dim = new Dimension(300,200);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(dim);
        window.setSize(dim);
        window.setResizable(false);
        /*window.showMinimizeButton(false);
        window.setJDialogBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addJDialogListener(new OptionPaneJDialogManager(this));

        window.getContentSpace().setLayout(new GridLayout(2,1));
        window.getContentSpace().add(questionComponent);
        window.getContentSpace().add(answersComponent);*/

        this.window = window;
    }

    public void show(){
        this.window.setVisible(true);
    }

    public void close(){
        this.parent.setAlwaysOnTop(false);
        this.window.dispose();
    }

    public String getAnswer(){
        return this.answer;
    }

    public boolean haveAnswered(){
        return this.haveAnswered;
    }

    public void setHaveAnswered(boolean haveAnswered){
        this.haveAnswered = haveAnswered;
    }

    public void setHaveAnswered(boolean haveAnswered, String answer){
        this.haveAnswered = haveAnswered;
        this.answer = answer;
    }

    public void waitForAnswer(){
        while(!this.haveAnswered){
            System.out.println("ddd");}
        System.out.println("ddhhd");
    }

    public static EnigmaButton getClassicButton(String text){
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

    public static EnigmaTextArea getClassicTextArea(){
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

    public static String oui(Window parent, String message){
        String i = "uu";
        ThreadE t = new ThreadE();
        t.start();
        System.out.println("ddsfgsrvgrd "+t.getOui());
        return t.getOui();
    }

    public static String showInputDialog(Window parent, String message){
        EnigmaLabel questionComponent = new EnigmaLabel(message);
        EnigmaTextArea answerComponent = getClassicTextArea();
        EnigmaPanel confirmComponent = new EnigmaPanel();
        EnigmaButton confirm = getClassicButton(CONFIRM);
        JDialog window = new JDialog();
        EnigmaOptionPanem optionPane = new EnigmaOptionPanem(window,parent);

        confirm.addActionListener(new OptionPaneButtonManager(optionPane));
        confirmComponent.add(confirm);

        parent.setAlwaysOnTop(true);
        Dimension dim = new Dimension(300,200);
        window.setAlwaysOnTop(true);
        window.setMinimumSize(dim);
        window.setSize(dim);
        /*window.setLocation(JDialog.CENTER);
        window.setResizable(false);
        window.showMinimizeButton(false);
        window.setJDialogBackground(Color.DARK_GRAY);
        window.showBorder(Color.WHITE,1);
        window.addJDialogListener(new OptionPaneJDialogManager(optionPane));*/

        window.getContentPane().setLayout(new GridLayout(3,1));
        window.getContentPane().add(questionComponent);
        window.getContentPane().add(answerComponent.setScrollBar());
        window.getContentPane().add(confirmComponent);

        optionPane.show();
        optionPane.waitForAnswer();
        return answerComponent.getText();
    }
}

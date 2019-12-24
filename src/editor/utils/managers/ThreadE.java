package editor.utils.managers;

import editor.utils.EnigmaOptionPane;
import editor.utils.EnigmaOptionPanem;
import editor.window.Window;

import javax.swing.*;

public class ThreadE extends Thread {
    private String oui = "_";
    @Override
    public void run() {
        oui = EnigmaOptionPanem.showInputDialog(new Window(),"oui");
    }

    public String getOui() {
        System.out.println(oui+"   ---");
        return oui;
    }
}

class Run implements Runnable {
    private EnigmaOptionPane k;
    private String oui = "_";
    public Run(EnigmaOptionPane k){
        this.k = k;
    }
    @Override
    public void run() {
        k.waitForAnswer();
        oui = k.getAnswer();
    }
    public String getOui() {
        return oui;
    }
}

class RunZ implements Runnable {
    @Override
    public void run() {
        Window n = new Window();
        n.setSize(Window.HALF_SCREEN_SIZE);
        n.setVisible(true);
    }
}

class ThreadO {
    public static String oui(){
        ThreadE kl = new ThreadE();
        kl.start();
        //SwingUtilities.invokeLater(kl);
        return kl.getOui();
    }
}

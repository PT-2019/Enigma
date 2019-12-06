package desktopLauncher;

import editor.utils.Window;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowManager implements WindowListener {

    private Window window;

    public WindowManager(Window window){
        this.window = window;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {}

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(this.window, "Voulez-vous vraiment nous quitter si tôt?", "Vous nous quittez?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {}

    @Override
    public void windowIconified(WindowEvent windowEvent) {}

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {}

    @Override
    public void windowActivated(WindowEvent windowEvent) {}

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {}
}

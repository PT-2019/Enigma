package editor;

import desktopLauncher.DesktopLauncher;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AppClosingManager implements WindowListener {

    @Override
    public void windowOpened(WindowEvent windowEvent) {}

    @Override
    public void windowClosing(WindowEvent windowEvent) {}

    @Override
    public void windowClosed(WindowEvent windowEvent) {
        DesktopLauncher.closeRunningApp();
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {}

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {}

    @Override
    public void windowActivated(WindowEvent windowEvent) {}

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {}
}

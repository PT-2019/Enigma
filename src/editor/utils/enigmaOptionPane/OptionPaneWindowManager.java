package editor.utils.enigmaOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OptionPaneWindowManager implements WindowListener {

    private EnigmaOptionPane optionPane;

    public OptionPaneWindowManager(EnigmaOptionPane optionPane){
        this.optionPane = optionPane;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {}

    @Override
    public void windowClosing(WindowEvent windowEvent) {}

    @Override
    public void windowClosed(WindowEvent windowEvent) {
        this.optionPane.cancel();
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

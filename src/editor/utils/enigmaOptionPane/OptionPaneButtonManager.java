package editor.utils.enigmaOptionPane;

import editor.utils.EnigmaButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPaneButtonManager implements ActionListener {

    private EnigmaOptionPane optionPane;

    public OptionPaneButtonManager(EnigmaOptionPane optionPane){
        this.optionPane = optionPane;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.optionPane.answer(((EnigmaButton) actionEvent.getSource()).getText());
    }
}

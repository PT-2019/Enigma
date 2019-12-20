package editor.utils.managers;

import editor.utils.EnigmaButton;

interface MultipleButtonManager {

    void add(EnigmaButton b);

    void setSelected(EnigmaButton b);

    EnigmaButton[] getSelected();
}

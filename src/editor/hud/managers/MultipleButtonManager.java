package editor.hud.managers;

import editor.hud.EnigmaButton;

interface MultipleButtonManager {

    void add(EnigmaButton b);

    void setSelected(EnigmaButton b);

    EnigmaButton[] getSelected();
}

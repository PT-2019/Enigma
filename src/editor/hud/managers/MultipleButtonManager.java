package editor.hud.managers;

import editor.hud.EnigmaButton;

interface MultipleButtonManager {

	void add(EnigmaButton b);

	EnigmaButton[] getSelected();

	void setSelected(EnigmaButton b);
}

package editor.hud.managers;

import editor.hud.EnigmaButton;

/**
 * TODO: comment MultipleButtonManager and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
interface MultipleButtonManager {

	void add(EnigmaButton b);

	EnigmaButton[] getSelected();

	void setSelected(EnigmaButton b);
}

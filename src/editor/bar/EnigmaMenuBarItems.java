package editor.bar;

import java.awt.event.ActionListener;

/**
 * Tous les boutons de la barre de menus
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public enum EnigmaMenuBarItems {
	NEW("new"), OPEN("open"), SAVE("save"), EXPORT("export"),
	REDO("redo"), UNDO("undo"),
	START("start simulation"), STOP("stop simulation"),
	HIDE_CALQUES("hide calque"), SHOW_CALQUES("show calque"),
	DOC("Documentation"), SUPPORT("Support");

	public final String name;
	public final Class<? extends ActionListener> run;

	EnigmaMenuBarItems(String name) {
		this(name, null);
	}

	EnigmaMenuBarItems(String name, Class<? extends ActionListener> run) {
		this.name = name;
		this.run = run;
	}

}

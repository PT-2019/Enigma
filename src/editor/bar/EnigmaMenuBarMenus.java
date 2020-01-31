package editor.bar;

import java.util.ArrayList;
import java.util.Arrays;

import static editor.bar.EnigmaMenuBarItems.DOC;
import static editor.bar.EnigmaMenuBarItems.EXPORT;
import static editor.bar.EnigmaMenuBarItems.NEW;
import static editor.bar.EnigmaMenuBarItems.OPEN;
import static editor.bar.EnigmaMenuBarItems.REDO;
import static editor.bar.EnigmaMenuBarItems.SAVE;
import static editor.bar.EnigmaMenuBarItems.START;
import static editor.bar.EnigmaMenuBarItems.STOP;
import static editor.bar.EnigmaMenuBarItems.SUPPORT;
import static editor.bar.EnigmaMenuBarItems.UNDO;

/**
 * Les onglets de la barre de menus et leur menus
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public enum EnigmaMenuBarMenus {
	FILE("File", NEW, OPEN, SAVE, EXPORT),
	EDIT("Edit", REDO, UNDO),
	RUN("Run", START, STOP),
	HELP("Help", DOC, SUPPORT),
	;

	public final String name;
	public final ArrayList<EnigmaMenuBarItems> items;

	EnigmaMenuBarMenus(String name, EnigmaMenuBarItems... items) {
		this.name = name;
		this.items = new ArrayList<>();
		this.items.addAll(Arrays.asList(items));
	}

}

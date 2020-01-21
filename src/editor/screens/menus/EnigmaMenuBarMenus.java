package editor.screens.menus;

import java.util.ArrayList;
import java.util.Arrays;

import static editor.screens.menus.EnigmaMenuBarItems.DOC;
import static editor.screens.menus.EnigmaMenuBarItems.EXPORT;
import static editor.screens.menus.EnigmaMenuBarItems.NEW;
import static editor.screens.menus.EnigmaMenuBarItems.OPEN;
import static editor.screens.menus.EnigmaMenuBarItems.REDO;
import static editor.screens.menus.EnigmaMenuBarItems.SAVE;
import static editor.screens.menus.EnigmaMenuBarItems.START;
import static editor.screens.menus.EnigmaMenuBarItems.STOP;
import static editor.screens.menus.EnigmaMenuBarItems.SUPPORT;
import static editor.screens.menus.EnigmaMenuBarItems.UNDO;

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

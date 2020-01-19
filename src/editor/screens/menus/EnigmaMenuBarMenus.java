package editor.screens.menus;

import java.util.ArrayList;
import java.util.Arrays;

import static editor.screens.menus.EnigmaMenuBarItems.*;

public enum EnigmaMenuBarMenus {
	FILE("File", NEW, OPEN, SAVE, EXPORT),
	EDIT("Edit", REDO, UNDO),
	RUN("Run", START, STOP),
	HELP("Help",DOC, SUPPORT),
	;

	public final String name;
	public final ArrayList<EnigmaMenuBarItems> items;

	EnigmaMenuBarMenus(String name, EnigmaMenuBarItems... items){
		this.name = name;
		this.items = new ArrayList<>();
		this.items.addAll(Arrays.asList(items));
	}

}

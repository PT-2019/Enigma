package editor.bar;

import common.language.GameLanguage;
import common.language.HUDFields;

import java.util.ArrayList;
import java.util.Arrays;

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
	FILE(GameLanguage.gl.get(HUDFields.FILE),
			EnigmaMenuBarItems.NEW, EnigmaMenuBarItems.OPEN, EnigmaMenuBarItems.SAVE,
			EnigmaMenuBarItems.SAVE_AS, EnigmaMenuBarItems.IMPORT , EnigmaMenuBarItems.EXPORT),
	EDIT(GameLanguage.gl.get(HUDFields.EDIT), EnigmaMenuBarItems.REDO, EnigmaMenuBarItems.UNDO),
	RUN(GameLanguage.gl.get(HUDFields.RUN), EnigmaMenuBarItems.START, EnigmaMenuBarItems.STOP),
	HELP(GameLanguage.gl.get(HUDFields.HELP), EnigmaMenuBarItems.DOC, EnigmaMenuBarItems.SUPPORT),
	;

	public final String name;
	public final ArrayList<EnigmaMenuBarItems> items;

	EnigmaMenuBarMenus(String name, EnigmaMenuBarItems... items) {
		this.name = name;
		this.items = new ArrayList<>();
		this.items.addAll(Arrays.asList(items));
	}
}

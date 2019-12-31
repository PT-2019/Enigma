package api.enums;

import editor.screens.menus.listeners.CreateListener;
import editor.screens.menus.listeners.OpenListener;
import editor.screens.menus.listeners.SaveListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;

import static editor.utils.lang.GameLanguage.gl;

public enum Outil {

	NEW(gl.getCreate(), new ImageIcon("assets/icon/new.png"), CreateListener.class),
	OPEN(gl.getOpen(), new ImageIcon("assets/icon/open.png"), OpenListener.class),
	SAVE(gl.getSave(), new ImageIcon("assets/icon/save.png"), true, SaveListener.class),
	UNDO(gl.getUndo(), new ImageIcon("assets/icon/undo.png")),
	REDO(gl.getRedo(), new ImageIcon("assets/icon/redo.png"), true),
	SOURIS(gl.getBrush(), new ImageIcon("assets/icon/brush.png")),
	GOMME(gl.getEraser(), new ImageIcon("assets/icon/eraser.png")),
	MOVE(gl.getMove(), new ImageIcon("assets/icon/move.png"), true);

	public static ImageIcon SEPARATOR = new ImageIcon("assets/icon/sep.png");

	public final String name;
	public final Icon icon;
	public final boolean glue;
	public final Class<? extends ActionListener> actionListener;

	Outil(String name, ImageIcon icon) {
		this(name, icon, false, null);
	}

	Outil(String name, ImageIcon icon, boolean glue) {
		this(name, icon, glue, null);
	}

	Outil(String name, ImageIcon icon, Class<? extends ActionListener> actionListener) {
		this(name, icon, false, actionListener);
	}

	Outil(String name, ImageIcon icon, boolean glue, Class<? extends ActionListener> actionListener) {
		this.name = name;
		this.icon = icon;
		this.glue = glue;
		this.actionListener = actionListener;
	}
}
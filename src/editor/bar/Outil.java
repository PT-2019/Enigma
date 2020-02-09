package editor.bar;

import common.language.HUDFields;
import editor.bar.listeners.BrushListener;
import editor.bar.listeners.CreateListener;
import editor.bar.listeners.EraserListener;
import editor.bar.listeners.MoveListener;
import editor.bar.listeners.OpenListener;
import editor.bar.listeners.RedoListener;
import editor.bar.listeners.SaveListener;
import editor.bar.listeners.UndoListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

import static common.language.GameLanguage.gl;

/**
 * Outils (barre de menu, d'outils)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 3.0
 */
public enum Outil {
	NEW(gl.get(HUDFields.CREATE), new ImageIcon("assets/icon/new.png"), CreateListener.class),
	OPEN(gl.get(HUDFields.OPEN), new ImageIcon("assets/icon/open.png"), OpenListener.class),
	SAVE(gl.get(HUDFields.SAVE), new ImageIcon("assets/icon/save.png"), true, SaveListener.class),
	UNDO(gl.get(HUDFields.UNDO), new ImageIcon("assets/icon/noUndo.png"), UndoListener.class),
	REDO(gl.get(HUDFields.REDO), new ImageIcon("assets/icon/noRedo.png"), true, RedoListener.class),
	BRUSH(gl.get(HUDFields.BRUSH), new ImageIcon("assets/icon/brush.png"), BrushListener.class),
	GOMME(gl.get(HUDFields.ERASER), new ImageIcon("assets/icon/eraser.png"), EraserListener.class),
	MOVE(gl.get(HUDFields.MOVE), new ImageIcon("assets/icon/move.png"), true, MoveListener.class);

	public static ImageIcon SEPARATOR = new ImageIcon("assets/icon/sep.png");
	public static ImageIcon UNDO_OK = new ImageIcon("assets/icon/undo.png");
	public static ImageIcon REDO_OK = new ImageIcon("assets/icon/redo.png");
	public static ImageIcon UNDO_KO = new ImageIcon("assets/icon/noUndo.png");
	public static ImageIcon REDO_KO = new ImageIcon("assets/icon/noRedo.png");

	/**
	 * Nom de l'outil
	 */
	public final String name;

	/**
	 * Icône de l'outil
	 */
	public final Icon icon;

	/**
	 * Si l'icône est suivie d'un séparateur
	 */
	public final boolean glue;

	/**
	 * Action Listener associé
	 */
	public final Class<? extends ActionListener> actionListener;

	/**
	 * Créé outil
	 *
	 * @param name           nom
	 * @param icon           icône
	 * @param actionListener listener
	 */
	Outil(String name, ImageIcon icon, Class<? extends ActionListener> actionListener) {
		this(name, icon, false, actionListener);
	}

	/**
	 * Créé outil
	 *
	 * @param name           nom
	 * @param icon           icône
	 * @param glue           true = suivie d'une séparateur
	 * @param actionListener listener
	 */
	Outil(String name, ImageIcon icon, boolean glue, Class<? extends ActionListener> actionListener) {
		this.name = name;
		this.icon = icon;
		this.glue = glue;
		this.actionListener = actionListener;
	}
}
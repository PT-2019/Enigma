package api.enums;

import api.utils.annotations.Temporary;
import editor.screens.menus.listeners.BrushListener;
import editor.screens.menus.listeners.CreateListener;
import editor.screens.menus.listeners.EraserListener;
import editor.screens.menus.listeners.MoveListener;
import editor.screens.menus.listeners.OpenListener;
import editor.screens.menus.listeners.SaveListener;
import editor.utils.lang.fields.HUDFields;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

import static editor.utils.lang.GameLanguage.gl;

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
	UNDO(gl.get(HUDFields.UNDO), new ImageIcon("assets/icon/noUndo.png"), null),
	REDO(gl.get(HUDFields.REDO), new ImageIcon("assets/icon/noRedo.png"), true, null),
	BRUSH(gl.get(HUDFields.BRUSH), new ImageIcon("assets/icon/brush.png"), BrushListener.class),
	GOMME(gl.get(HUDFields.ERASER), new ImageIcon("assets/icon/eraser.png"), EraserListener.class),
	@Temporary(reason = "true normalement mais zoom désactivé")
	MOVE(gl.get(HUDFields.MOVE), new ImageIcon("assets/icon/move.png"), false, MoveListener.class);

	public static ImageIcon SEPARATOR = new ImageIcon("assets/icon/sep.png");
	public static ImageIcon REDO_OK = new ImageIcon("assets/icon/undo.png");
	public static ImageIcon UNDO_OK = new ImageIcon("assets/icon/redo.png");
	public static ImageIcon REDO_KO = new ImageIcon("assets/icon/noUndo.png");
	public static ImageIcon UNOD_KO = new ImageIcon("assets/icon/noRedo.png");

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
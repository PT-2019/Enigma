package editor.bar;

import common.language.GameLanguage;
import common.language.HUDFields;
import editor.bar.listeners.CreateListener;
import editor.bar.listeners.ExportListener;
import editor.bar.listeners.OpenListener;
import editor.bar.listeners.ReadDocumentation;
import editor.bar.listeners.RedoListener;
import editor.bar.listeners.SaveAsListener;
import editor.bar.listeners.SaveListener;
import editor.bar.listeners.StartSimulation;
import editor.bar.listeners.StopSimulation;
import editor.bar.listeners.UndoListener;

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
	NEW(GameLanguage.gl.get(HUDFields.CREATE), CreateListener.class),
	OPEN(GameLanguage.gl.get(HUDFields.OPEN), OpenListener.class),
	SAVE(GameLanguage.gl.get(HUDFields.SAVE), SaveListener.class),
	SAVE_AS(GameLanguage.gl.get(HUDFields.SAVE_AS), SaveAsListener.class),
	EXPORT(GameLanguage.gl.get(HUDFields.EXPORT), ExportListener.class),

	REDO(GameLanguage.gl.get(HUDFields.REDO), RedoListener.class, false),
	UNDO(GameLanguage.gl.get(HUDFields.UNDO), UndoListener.class, false),

	START(GameLanguage.gl.get(HUDFields.START), StartSimulation.class),
	STOP(GameLanguage.gl.get(HUDFields.STOP), StopSimulation.class, false),

	@Deprecated
	HIDE_CALQUES("hide calque", null, false),
	@Deprecated
	SHOW_CALQUES("show calque", null, false),

	DOC(GameLanguage.gl.get(HUDFields.DOC), ReadDocumentation.class),
	SUPPORT(GameLanguage.gl.get(HUDFields.SUPPORT), null, false);

	/**
	 * nom
	 */
	public final String name;
	/**
	 * Exécutable
	 */
	public final Class<? extends ActionListener> run;
	/**
	 * état de base
	 */
	public final boolean enabled;

	EnigmaMenuBarItems(String name, Class<? extends ActionListener> run) {
		this(name, run, true);
	}

	EnigmaMenuBarItems(String name, Class<? extends ActionListener> run, boolean enabled) {
		this.name = name;
		this.run = run;
		this.enabled = enabled;
	}

}

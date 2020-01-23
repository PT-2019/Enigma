package editor.screens.menus;

import api.enums.EnigmaScreens;
import com.badlogic.gdx.Gdx;
import editor.hud.EnigmaMenu;
import editor.hud.EnigmaMenuBar;
import editor.hud.EnigmaMenuItem;
import editor.hud.EnigmaWindow;
import editor.screens.menus.listeners.CreateListener;
import editor.screens.menus.listeners.OpenListener;
import editor.utils.lang.fields.HUDFields;
import game.EnigmaGame;

import static editor.utils.lang.GameLanguage.gl;

public class BarMenu extends EnigmaMenuBar {
	//1er onglet
	private EnigmaMenu file = new EnigmaMenu(gl.get(HUDFields.FILE));
	private EnigmaMenuItem create = new EnigmaMenuItem(gl.get(HUDFields.CREATE));
	private EnigmaMenuItem ouvrir = new EnigmaMenuItem(gl.get(HUDFields.OPEN));
	private EnigmaMenuItem save = new EnigmaMenuItem(gl.get(HUDFields.SAVE));
	private EnigmaMenuItem map = new EnigmaMenuItem(gl.get(HUDFields.EXPORT));
	//2eme onglet
	private EnigmaMenu edit = new EnigmaMenu(gl.get(HUDFields.EDIT));
	private EnigmaMenuItem redo = new EnigmaMenuItem(gl.get(HUDFields.REDO));
	private EnigmaMenuItem undo = new EnigmaMenuItem(gl.get(HUDFields.UNDO));
	//3eme onglet
	private EnigmaMenu run = new EnigmaMenu(gl.get(HUDFields.RUN));
	private EnigmaMenuItem runJeu = new EnigmaMenuItem(gl.get(HUDFields.START));
	private EnigmaMenuItem finJeu = new EnigmaMenuItem(gl.get(HUDFields.STOP));
	//4eme onglet
	private EnigmaMenu help = new EnigmaMenu(gl.get(HUDFields.HELP));
	private EnigmaMenuItem doc = new EnigmaMenuItem(gl.get(HUDFields.DOC));
	private EnigmaMenuItem support = new EnigmaMenuItem(gl.get(HUDFields.SUPPORT));

	public BarMenu(EnigmaWindow window) {
		this.file.add(create);
		this.file.add(ouvrir);
		this.file.add(save);
		this.file.add(map);

		this.edit.add(redo);
		this.edit.add(undo);

		this.run.add(runJeu);
		this.run.add(finJeu);

		this.help.add(doc);
		this.help.add(support);

		this.add(file);
		this.add(edit);
		this.add(run);
		this.add(help);

		//listeners

		this.create.addActionListener(new CreateListener(window));
		this.ouvrir.addActionListener(new OpenListener(window));
		this.save.addActionListener(new OpenListener(window));

		this.runJeu.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.GAME.name()));
			this.finJeu.setEnabled(true);
			this.runJeu.setEnabled(false);
		}));
		this.finJeu.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.TEST.name()));
			this.runJeu.setEnabled(true);
			this.finJeu.setEnabled(false);
		}));
		this.finJeu.setEnabled(false);
	}
}
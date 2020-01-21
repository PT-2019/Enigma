package editor.screens.menus;

import api.enums.EnigmaScreens;
import com.badlogic.gdx.Gdx;
import editor.hud.EnigmaMenu;
import editor.hud.EnigmaMenuBar;
import editor.hud.EnigmaMenuItem;
import editor.hud.EnigmaWindow;
import editor.screens.menus.listeners.CreateListener;
import editor.screens.menus.listeners.OpenListener;
import editor.utils.lang.Field;
import game.EnigmaGame;

import static editor.utils.lang.GameLanguage.gl;

public class BarMenu extends EnigmaMenuBar {
	//1er onglet
	private EnigmaMenu file = new EnigmaMenu(gl.get(Field.FILE));
	private EnigmaMenuItem create = new EnigmaMenuItem(gl.get(Field.CREATE));
	private EnigmaMenuItem ouvrir = new EnigmaMenuItem(gl.get(Field.OPEN));
	private EnigmaMenuItem save = new EnigmaMenuItem(gl.get(Field.SAVE));
	private EnigmaMenuItem map = new EnigmaMenuItem(gl.get(Field.EXPORT));
	//2eme onglet
	private EnigmaMenu edit = new EnigmaMenu(gl.get(Field.EDIT));
	private EnigmaMenuItem redo = new EnigmaMenuItem(gl.get(Field.REDO));
	private EnigmaMenuItem undo = new EnigmaMenuItem(gl.get(Field.UNDO));
	//3eme onglet
	private EnigmaMenu run = new EnigmaMenu(gl.get(Field.RUN));
	private EnigmaMenuItem runJeu = new EnigmaMenuItem(gl.get(Field.START));
	private EnigmaMenuItem finJeu = new EnigmaMenuItem(gl.get(Field.STOP));
	//4eme onglet
	private EnigmaMenu help = new EnigmaMenu(gl.get(Field.HELP));
	private EnigmaMenuItem doc = new EnigmaMenuItem(gl.get(Field.DOC));
	private EnigmaMenuItem support = new EnigmaMenuItem(gl.get(Field.SUPPORT));

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

package editor.bar;

import com.badlogic.gdx.Gdx;
import common.hud.*;
import common.hud.ui.EnigmaMenuUI;
import common.language.GameFields;
import common.language.HUDFields;
import data.EnigmaScreens;
import data.config.EnigmaUIValues;
import editor.bar.listeners.*;
import game.EnigmaGame;

import java.awt.*;

import static common.language.GameLanguage.gl;

/**
 * Barre du menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public class BarMenu extends EnigmaMenuBar {
	//1er onglet
	private EnigmaMenu file = new EnigmaMenu(gl.get(HUDFields.FILE));
	private EnigmaMenuItem create = new EnigmaMenuItem(gl.get(HUDFields.CREATE));
	private EnigmaMenuItem open = new EnigmaMenuItem(gl.get(HUDFields.OPEN));
	private EnigmaMenuItem save = new EnigmaMenuItem(gl.get(HUDFields.SAVE));
	private EnigmaMenuItem saveAs = new EnigmaMenuItem(gl.get(HUDFields.SAVE_AS));
	private EnigmaMenuItem export = new EnigmaMenuItem(gl.get(HUDFields.EXPORT));
	//2eme onglet
	private EnigmaMenu edit = new EnigmaMenu(gl.get(HUDFields.EDIT));
	private EnigmaMenuItem redo = new EnigmaMenuItem(gl.get(HUDFields.REDO));
	private EnigmaMenuItem undo = new EnigmaMenuItem(gl.get(HUDFields.UNDO));
	//3eme onglet
	private EnigmaMenu music = new EnigmaMenu(gl.get(GameFields.MUSIC));
	private EnigmaMenuItem chose = new EnigmaMenuItem(gl.get(HUDFields.CHOSE));
	//4eme onglet
	private EnigmaMenu run = new EnigmaMenu(gl.get(HUDFields.RUN));
	private EnigmaMenuItem runGame = new EnigmaMenuItem(gl.get(HUDFields.START));
	private EnigmaMenuItem finGame = new EnigmaMenuItem(gl.get(HUDFields.STOP));
	//5eme onglet
	private EnigmaMenu help = new EnigmaMenu(gl.get(HUDFields.HELP));
	private EnigmaMenuItem doc = new EnigmaMenuItem(gl.get(HUDFields.DOC));
	private EnigmaMenuItem support = new EnigmaMenuItem(gl.get(HUDFields.SUPPORT));

	public BarMenu(EnigmaWindow window) {
		EnigmaMenuUI mui = new EnigmaMenuUI();
		mui.setPopupBackground(Color.WHITE);
		mui.setPopupBorderSize(1);
		mui.setShowedPopupBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);

		this.file.setComponentUI(mui);
		this.edit.setComponentUI(mui);
		this.run.setComponentUI(mui);
		this.help.setComponentUI(mui);
		this.music.setComponentUI(mui);

		this.file.add(create);
		this.file.add(open);
		this.file.add(save);
		this.file.add(saveAs);
		this.file.add(export);

		this.edit.add(undo);
		this.edit.add(redo);

		this.music.add(chose);

		this.run.add(runGame);
		this.run.add(finGame);

		this.help.add(doc);
		this.help.add(support);

		this.add(file);
		this.add(edit);
		this.add(music);
		this.add(run);
		this.add(help);


		//listeners

		this.create.addActionListener(new CreateListener(window, this.create));
		this.open.addActionListener(new OpenListener(window, this.open));
		this.save.addActionListener(new SaveListener(window, this.save));
		this.saveAs.addActionListener(new SaveAsListener(window, this.saveAs));
		this.export.addActionListener(new ExportListener(window, this.export));
		this.undo.addActionListener(new UndoListener(window, this.undo));
		this.redo.addActionListener(new RedoListener(window, this.redo));
		this.chose.addActionListener(new ChooseListener(window));

		this.runGame.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.GAME.name()));
			this.finGame.setEnabled(true);
			this.runGame.setEnabled(false);
		}));
		this.finGame.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.TEST.name()));
			this.runGame.setEnabled(true);
			this.finGame.setEnabled(false);
		}));
		this.finGame.setEnabled(false);
	}
}

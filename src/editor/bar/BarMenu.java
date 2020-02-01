package editor.bar;

import com.badlogic.gdx.Gdx;
import common.hud.*;
import common.hud.ui.EnigmaMenuUI;
import common.hud.ui.EnigmaPopupMenuUI;
import common.language.HUDFields;
import data.EnigmaScreens;
import data.config.EnigmaUIValues;
import editor.bar.listeners.CreateListener;
import editor.bar.listeners.OpenListener;
import editor.bar.listeners.SaveAsListener;
import editor.bar.listeners.SaveListener;
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
	private EnigmaMenuItem ouvrir = new EnigmaMenuItem(gl.get(HUDFields.OPEN));
	private EnigmaMenuItem save = new EnigmaMenuItem(gl.get(HUDFields.SAVE));
	private EnigmaMenuItem saveAs = new EnigmaMenuItem(gl.get(HUDFields.SAVE_AS));
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
		EnigmaMenuUI mui = new EnigmaMenuUI();
		mui.setPopupBackground(Color.WHITE);
		mui.setPopupBorderSize(1);
		mui.setShowedPopupBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);

		this.file.setComponentUI(mui);
		this.edit.setComponentUI(mui);
		this.run.setComponentUI(mui);
		this.help.setComponentUI(mui);

		this.file.add(create);
		this.file.add(ouvrir);
		this.file.add(save);
		this.file.add(saveAs);
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
		this.save.addActionListener(new SaveListener(window));
		this.saveAs.addActionListener(new SaveAsListener(window));

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

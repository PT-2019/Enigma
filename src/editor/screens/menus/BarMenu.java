package editor.screens.menus;

import api.enums.EnigmaScreens;
import api.hud.components.CustomWindow;
import com.badlogic.gdx.Gdx;
import editor.hud.EnigmaMenu;
import editor.hud.EnigmaMenuBar;
import editor.hud.EnigmaMenuItem;
import editor.hud.EnigmaWindow;
import editor.screens.menus.listeners.CreateListener;
import editor.screens.menus.listeners.OpenListener;
import game.EnigmaGame;

public class BarMenu extends EnigmaMenuBar {
	//1er onglet
	private EnigmaMenu file = new EnigmaMenu("File");
	private EnigmaMenuItem create = new EnigmaMenuItem("Nouveau");
	private EnigmaMenuItem ouvrir = new EnigmaMenuItem("Ouvrir");
	private EnigmaMenuItem save = new EnigmaMenuItem("Sauvegarder");
	private EnigmaMenuItem map = new EnigmaMenuItem("Exporter la map");
	//2eme onglet
	private EnigmaMenu edit = new EnigmaMenu("Edit");
	private EnigmaMenuItem redo = new EnigmaMenuItem("Redo");
	private EnigmaMenuItem undo = new EnigmaMenuItem("Undo");
	//3eme onglet
	private EnigmaMenu run = new EnigmaMenu("Run");
	private EnigmaMenuItem runJeu = new EnigmaMenuItem("Lancer le jeu");
	private EnigmaMenuItem finJeu = new EnigmaMenuItem("Terminer le jeu");
	//4eme onglet
	private EnigmaMenu help = new EnigmaMenu("Help");
	private EnigmaMenuItem doc = new EnigmaMenuItem("Documentation");
	private EnigmaMenuItem support = new EnigmaMenuItem("Support");

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

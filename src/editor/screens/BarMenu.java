package editor.screens;

import api.enums.EnigmaScreens;
import com.badlogic.gdx.Gdx;
import game.EnigmaGame;

import editor.hud.EnigmaMenu;
import editor.hud.EnigmaMenuBar;
import editor.hud.EnigmaMenuItem;

public class BarMenu extends EnigmaMenuBar {
    //1er onglet
    private EnigmaMenu file = new EnigmaMenu("File");
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

	public BarMenu() {
		this.file.add(ouvrir);
		this.file.add(save);
		this.file.add(map);

		this.edit.add(redo);
		this.edit.add(undo);

		this.run.add(runJeu);
		this.run.add(finJeu);

		runJeu.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.GAME.name()));
			finJeu.setEnabled(true);
			runJeu.setEnabled(false);
		}));
		//Vérifier demande pas la fin avant le début
		finJeu.addActionListener((e -> {
			Gdx.app.postRunnable(() -> EnigmaGame.setScreen(EnigmaScreens.TEST.name()));
			runJeu.setEnabled(true);
			finJeu.setEnabled(false);
		}));
		finJeu.setEnabled(false);


		this.help.add(doc);
		this.help.add(support);

		this.add(file);
		this.add(edit);
		this.add(run);
		this.add(help);
	}
}

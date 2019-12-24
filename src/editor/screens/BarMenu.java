package editor.screens;

import api.enums.EnigmaScreens;
import api.utils.LoadGameLibgdxApplication;
import com.badlogic.gdx.Gdx;
import editor.EditorLuncher;
import editor.window.Window;
import game.EnigmaGame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarMenu extends JMenuBar {
	//1er onglet
	private JMenu file = new JMenu("File");
	private JMenuItem ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem save = new JMenuItem("Sauvegarder");
	private JMenuItem map = new JMenuItem("Exporter la map");
	//2eme onglet
	private JMenu edit = new JMenu("Edit");
	private JMenuItem redo = new JMenuItem("Redo");
	private JMenuItem undo = new JMenuItem("Undo");
	//3eme onglet
	private JMenu run = new JMenu("Run");
	private JMenuItem runJeu = new JMenuItem("Lancer le jeu");
	private JMenuItem finJeu = new JMenuItem("Terminer le jeu");
	//4eme onglet
	private JMenu help = new JMenu("Help");
	private JMenuItem doc = new JMenuItem("Documentation");
	private JMenuItem support = new JMenuItem("Support");

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

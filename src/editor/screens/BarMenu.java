package editor.screens;

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
	private JMenuItem runjeu = new JMenuItem("Lancer le jeu");
	//4eme onglet
	private JMenu help = new JMenu("Help");
	private JMenuItem a = new JMenuItem("Ouvrir");
	private JMenuItem b = new JMenuItem("Ouvrir");

	public BarMenu() {
		this.file.add(ouvrir);
		this.file.add(save);
		this.file.add(map);

		this.edit.add(redo);
		this.edit.add(undo);

		this.run.add(runjeu);

		this.help.add(a);
		this.help.add(b);

		this.add(file);
		this.add(edit);
		this.add(run);
		this.add(help);
	}
}

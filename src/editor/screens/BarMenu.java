package editor.screens;

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
    private EnigmaMenuItem runjeu = new EnigmaMenuItem("Lancer le jeu");
    //4eme onglet
    private EnigmaMenu help = new EnigmaMenu("Help");
    private EnigmaMenuItem a = new EnigmaMenuItem("Ouvrir");
    private EnigmaMenuItem b = new EnigmaMenuItem("Ouvrir");

    public BarMenu(){
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

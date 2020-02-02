package editor.bar.listeners;

import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.ImportExport;
import common.utils.Logger;
import data.config.Config;
import game.EnigmaGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Observateur de l'exportation de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class ExportListener extends MenuListener {

	public ExportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		MapData data = EnigmaGame.getCurrentScreen().getMap().getMapData();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Choisissez le dossier de destination");

		if(fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
			String exportPath = fileChooser.getSelectedFile().getAbsolutePath();
			String file = data.getMapName() + Config.EXPORT_EXTENSION;

			String[] files = fileChooser.getSelectedFile().list();
			if(files != null) {
				for (String s : files){
					if(s.equals(file)){
						EnigmaOptionPane.showAlert(this.window,"Un fichier du même nom existe déjà");
						return;
					}
				}
			}

			if(exportPath.contains("\\"))
				exportPath += "\\";
			if(exportPath.contains("/"))
				exportPath += "/";

			try {
				ImportExport.exportMap(data.getMapName(),exportPath);
			} catch (IOException e) {
				Logger.printError("ExportListener.java","export error");
				EnigmaOptionPane.showAlert(this.window,"Export raté");
			}
			//TODO: afficher ok
		}
	}
}

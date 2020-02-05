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
		//TODO: verifier si map a été sauvegardée avant d'exporter
		MapData data = EnigmaGame.getCurrentScreen().getMap().getMapData();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Choisissez le dossier de destination");

		if(fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
			String exportPath = fileChooser.getSelectedFile().getAbsolutePath();

			if(exportPath.contains("\\"))
				exportPath += "\\";
			if(exportPath.contains("/"))
				exportPath += "/";

			try {
				ImportExport.exportMap(data.getMapName(),exportPath);
			} catch (IOException |IllegalStateException e) {
				Logger.printError("ExportListener.java","export error: " + e.getMessage());

				//on efface le fichier créé car erreur
				File f = new File(exportPath + data.getMapName() + Config.MAP_EXPORT_EXTENSION);
				f.delete();
			}
			//TODO: afficher ok
		}
	}
}

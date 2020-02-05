package editor.bar.listeners;

import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.ImportExport;
import common.utils.Logger;
import data.config.Config;
import game.EnigmaGame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class ImportListener extends MenuListener {

	public ImportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String extension = Config.MAP_EXPORT_EXTENSION.replaceFirst(".","");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setDialogTitle("Choisissez le fichier à importer");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(Config.MAP_EXPORT_EXTENSION,extension));

		if(fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
			String importPath = fileChooser.getSelectedFile().getAbsolutePath();

			try {
				ImportExport.importMap(importPath);
			} catch (IOException |IllegalStateException e) {
				Logger.printError("ImportListener.java","import error: " + e.getMessage());

				EnigmaOptionPane.showAlert(this.window,"Import raté");
			}
			//TODO: afficher ok
		}
	}
}

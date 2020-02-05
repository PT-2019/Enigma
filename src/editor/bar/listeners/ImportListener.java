package editor.bar.listeners;

import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.ImportExport;
import common.utils.Logger;
import data.NeedToBeTranslated;
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
	/**
	 * Textes
	 */
	private static final String CHOOSE_FILE = NeedToBeTranslated.CHOOSE_FILE_TO_IMPORT;
	private static final String IMPORT_ENDED = NeedToBeTranslated.IMPORT_ENDED;
	private static final String IMPORT_ERROR = NeedToBeTranslated.IMPORT_ERROR;
	private static final String IMPORT_ABANDONED = NeedToBeTranslated.IMPORT_ABANDONED;

	public ImportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String extension = Config.MAP_EXPORT_EXTENSION.replaceFirst(".","");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setDialogTitle(CHOOSE_FILE);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(Config.MAP_EXPORT_EXTENSION,extension));

		if(fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
			String importPath = fileChooser.getSelectedFile().getAbsolutePath();

			try {
				ImportExport.importMap(importPath);
				EnigmaGame.getCurrentScreen().showToast(IMPORT_ENDED);
			} catch (IOException e) {
				EnigmaGame.getCurrentScreen().showToast(IMPORT_ERROR);
				Logger.printError("ImportListener.java","import error: " + e.getMessage());
			} catch (IllegalStateException e){
				EnigmaGame.getCurrentScreen().showToast(IMPORT_ABANDONED);
				Logger.printError("ImportListener.java","import error: " + e.getMessage());
			}
		}
	}
}

package editor.bar.listeners;

import api.libgdx.ui.Toast;
import common.data.MapData;
import common.enigmas.Enigma;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.ImportExport;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import game.EnigmaGame;
import game.gui.EnigmaEditorToast;
import game.screens.TestScreen;

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
	/**
	 * Textes
	 */
	private static final String CHOOSE_DESTINATION_FOLDER = NeedToBeTranslated.CHOOSE_DESTINATION_FOLDER;
	private static final String EXPORT_ENDED = NeedToBeTranslated.EXPORT_ENDED;
	private static final String EXPORT_ERROR = NeedToBeTranslated.EXPORT_ERROR;
	private static final String EXPORT_ABANDONED = NeedToBeTranslated.EXPORT_ABANDONED;

	public ExportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		MapData data = EnigmaGame.getCurrentScreen().getMap().getMapData();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(CHOOSE_DESTINATION_FOLDER);

		if(fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
			String exportPath = fileChooser.getSelectedFile().getAbsolutePath();

			if(exportPath.contains("\\"))
				exportPath += "\\";
			if(exportPath.contains("/"))
				exportPath += "/";

			try {
				//Sauvegarde avant d'exporter
				new SaveListener(this.window,this.parent).save();
				ImportExport.exportMap(data.getMapName(),exportPath);
				EnigmaGame.getCurrentScreen().showToast(EXPORT_ENDED);
			} catch (IOException e) {
				new File(exportPath + data.getMapName() + Config.MAP_EXPORT_EXTENSION).delete();
				EnigmaGame.getCurrentScreen().showToast(EXPORT_ERROR);
				Logger.printError("ExportListener.java","export error");
			}catch (IllegalStateException e) {
				new File(exportPath + data.getMapName() + Config.MAP_EXPORT_EXTENSION).delete();
				EnigmaGame.getCurrentScreen().showToast(EXPORT_ABANDONED);
				Logger.printError("ExportListener.java", "export error");
			}
		}
	}
}

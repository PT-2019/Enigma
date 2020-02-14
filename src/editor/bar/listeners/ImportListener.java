package editor.bar.listeners;

import common.hud.EnigmaWindow;
import common.save.ImportExport;
import data.NeedToBeTranslated;
import data.config.Config;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'exportation de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class ImportListener extends MenuListener {
	/**
	 * Textes
	 */
	private static final String CHOOSE_FILE = NeedToBeTranslated.CHOOSE_FILE_TO_IMPORT;

	public ImportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String extension = Config.MAP_EXPORT_EXTENSION.replaceFirst(".", "");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setDialogTitle(CHOOSE_FILE);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(Config.MAP_EXPORT_EXTENSION, extension));

		if (fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION) {
			String importPath = fileChooser.getSelectedFile().getAbsolutePath();
			ImportExport.importMap(importPath);
		}
	}
}

package editor.bar.listeners;

import api.utils.Observer;
import common.data.MapData;
import common.hud.EnigmaWindow;
import common.save.ImportExport;
import data.NeedToBeTranslated;
import game.EnigmaGame;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
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
public class ExportListener extends MenuListener implements Observer<MapLoaded> {

	/**
	 * Textes
	 */
	private static final String CHOOSE_DESTINATION_FOLDER = NeedToBeTranslated.CHOOSE_DESTINATION_FOLDER;

	public ExportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
		MapLoaded instance = MapLoaded.getInstance();
		instance.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		MapData data = EnigmaGame.getCurrentScreen().getMap().getMapData();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(CHOOSE_DESTINATION_FOLDER);

		if (fileChooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION) {
			String exportPath = fileChooser.getSelectedFile().getAbsolutePath();

			if (exportPath.contains("\\"))
				exportPath += "\\";
			if (exportPath.contains("/"))
				exportPath += "/";

			//Sauvegarde avant d'exporter
			new SaveListener(this.window, this.parent).save();
			ImportExport.exportMap(data.getMapName(), exportPath);
		}
	}

	@Override
	public void update(MapLoaded object) {
		this.parent.setEnabled(object.isMapLoaded());
	}
}

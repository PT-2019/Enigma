package editor.screens.menus.listeners;

import editor.hud.EnigmaWindow;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveListener extends MenuListener {

	private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");

	private volatile boolean finished;
	private volatile File file;

	public SaveListener(EnigmaWindow window) {
		super(window);
		//force lancement javafx
		new JFXPanel();
		this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.finished = false;


		Platform.runLater(
				() ->
				{
					FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File("./assets/map"));
					fileChooser.getExtensionFilters().addAll(extensions);

					this.file = fileChooser.showSaveDialog(null);
					this.finished = true;
				}
		);

		while (!this.finished) ; //attends le thread file chooser

		//sauvegarde
		if (this.file != null) {

		}
	}

}

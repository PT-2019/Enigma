package editor.screens.menus.listeners;

import api.enums.EnigmaScreens;
import com.badlogic.gdx.Gdx;
import editor.hud.EnigmaWindow;
import game.EnigmaGame;
import game.screen.TestScreen;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.io.File;

public class OpenListener extends MenuListener {

	private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");

	private volatile boolean finished;
	private volatile File file;

	public OpenListener(EnigmaWindow window) {
		super(window);
		//force lancement javafx
		new JFXPanel();
		this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.finished = false;

		Platform.runLater(
				() ->
				{
					FileChooser fileChooser = new FileChooser();
					fileChooser.setInitialDirectory(new File("./assets/map"));
					fileChooser.getExtensionFilters().addAll(extensions);

					this.file = fileChooser.showOpenDialog(null);
					this.finished = true;
				}
		);

		while (!this.finished) ; //attends le thread file chooser

		//relance le screen
		if (this.file != null) {
			if (((TestScreen) EnigmaGame.getCurrentScreen()).setMap(this.file.getAbsolutePath()))
				Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));
		}
	}
}

package editor.bar.listeners;

import com.badlogic.gdx.Gdx;
import data.EnigmaScreens;
import data.config.Config;
import game.EnigmaGame;
import general.hud.EnigmaWindow;
import general.save.EmptyMapGenerator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class OpenListener extends MenuListener {

	/*private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/

	//private volatile boolean finished;
	//private volatile File file;

	public OpenListener(EnigmaWindow window) {
		super(window);
		//force lancement javafx
		//new JFXPanel();
		//this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*this.finished = false;

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
		}*/

		FileNameExtensionFilter tmx = new FileNameExtensionFilter("Fichier map (*.tmx)", "tmx");
		JFileChooser fileChooser = new JFileChooser(Config.MAP_FOLDER);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(tmx);

		if (fileChooser.showOpenDialog(new EnigmaWindow()) == JFileChooser.APPROVE_OPTION) {
			//System.out.println("ouverture à "+fileChooser.getSelectedFile().getAbsolutePath());
			EmptyMapGenerator.load(fileChooser.getSelectedFile().getAbsolutePath());
			Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));
			//TODO: message ok
		}
	}
}

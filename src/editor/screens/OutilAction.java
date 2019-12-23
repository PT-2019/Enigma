package editor.screens;

import api.enums.EnigmaScreens;
import api.enums.Outil;
import com.badlogic.gdx.Gdx;
import game.EnigmaGame;
import game.screen.TestScreen;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javafx.stage.FileChooser.ExtensionFilter;

/**
 * Observateur des boutons de la barre d'outil
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0 19 décembre 2019
 * @see editor.screens.EditorScreen
 * @since 3.0 19 décembre 2019
 */
public class OutilAction implements ActionListener {

    private static final ExtensionFilter extensions = new ExtensionFilter("Fichier map .tmx",
            "*.tmx");

    private volatile boolean finished;
    private volatile File file;

    public OutilAction(){
        //force lancement javafx
        new JFXPanel();
        this.finished = false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String msg = actionEvent.getActionCommand();
        this.finished=false;

        if (msg.equals(Outil.OPEN.name)){

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

            while ( !this.finished ); //attends le thread file chooser

            //relance le screen
            if(this.file != null) {
                ((TestScreen) EnigmaGame.getCurrentScreen()).setMap(this.file.getAbsolutePath());
                Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));
            }

        }else if(msg.equals(Outil.SAVE.name)){

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

            while ( !this.finished ); //attends le thread file chooser

            //sauvegarde
            if(this.file != null) {

            }
        }
    }
}

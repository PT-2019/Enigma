package editor.bibliotheque;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import editor.datas.Outil;
import game.EnigmaGame;
import game.screen.EnigmaScreens;
import game.screen.TestScreen;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Observateur des boutons de la barre d'outil
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0 19 décembre 2019
 * @see editor.bibliotheque.MenuScreen
 * @since 3.0 19 décembre 2019
 */
public class OutilAction implements ActionListener {
    private volatile boolean finished;

    public OutilAction(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String msg = actionEvent.getActionCommand();

        if (msg.equals(Outil.OPEN.name)){
            new JFXPanel();

            finished=false;

            Platform.runLater(
                    () ->
                    {
                        FileChooser fileChooser = new FileChooser();
                        File file;

                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".tmx", "*.tmx"));

                        file = fileChooser.showOpenDialog(null);

                        finished = true;

                        TestScreen.str=file.getAbsolutePath();
                    }
            );

            while ( !finished )
            {
                // waits for FileChooser window to close
            }



            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    EnigmaGame.reload(EnigmaScreens.TEST.name());
                }
            });

        }else if(msg.equals(Outil.SAVE.name)){
            new JFXPanel();

            finished=false;

            Platform.runLater(
                    () ->
                    {
                        FileChooser fileChooser = new FileChooser();
                        File file;

                        file = fileChooser.showSaveDialog(null);

                        finished = true;
                    }
            );

            while ( !finished )
            {
                // waits for FileChooser window to close
            }
        }
    }
}

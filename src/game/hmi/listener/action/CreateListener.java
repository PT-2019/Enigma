package game.hmi.listener.action;

import common.data.GameData;
import common.save.DataSave;
import common.utils.Logger;
import data.config.Config;
import game.hmi.ContentManager;
import game.hmi.content.Create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Gestionnaire du bouton "créer"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class CreateListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(Create.getInstance().verify()){
            GameData data = Create.getInstance().getData();
            try {
                DataSave.writeGameData(data);

                if(data.isMultiPlayer())
                    ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
                else
                    ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
            } catch (IOException e) {
                Logger.printError("CreateListener.java","Erreur a la création " + e.getMessage());
                File file = new File(Config.GAME_DATA_FOLDER + data.getName() + Config.DATA_EXTENSION);
                file.delete();
            }
        }
    }
}

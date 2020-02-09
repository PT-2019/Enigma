package game.hmi.listener.action;

import common.data.GameData;
import common.enigmas.Enigma;
import common.hud.EnigmaOptionPane;
import common.utils.Logger;
import data.config.Config;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteListener implements ActionListener {
    private GameData game;

    /**
     * Textes
     */
    public final static String DELETE_ERROR = "Erreur lors de la suppression";
    public final static String DELETE_CONFIRMATION = "Supprimer ?";

    public DeleteListener(GameData game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        File file = new File(Config.GAME_DATA_FOLDER + this.game.getName() + Config.DATA_EXTENSION);
        if(EnigmaOptionPane.showConfirmDialog(EnigmaGameLauncher.getInstance().getWindow(),DELETE_CONFIRMATION)) {
            if (!file.delete()) {
                EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), DELETE_ERROR);
                Logger.printError("DeleteListener.java", "Erreur de suppression ");
            }

            if (this.game.isMultiPlayer())
                ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
            else
                ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
        }
    }
}

package game.hmi.listener.action;

import common.data.GameData;
import common.save.ImportExport;
import data.NeedToBeTranslated;
import editor.bar.listeners.SaveListener;
import game.EnigmaGameLauncher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire du bouton "exporter"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class ExportListener implements ActionListener {
    /**
     * Données de la partie
     */
    private GameData data;
    /**
     * Textes
     */
    private static final String CHOOSE_DESTINATION_FOLDER = NeedToBeTranslated.CHOOSE_DESTINATION_FOLDER;

    /**
     * @param data Données de la partie
     */
    public ExportListener(GameData data){
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle(CHOOSE_DESTINATION_FOLDER);

        if(fileChooser.showOpenDialog(EnigmaGameLauncher.getInstance().getWindow()) == JFileChooser.APPROVE_OPTION){
            String exportPath = fileChooser.getSelectedFile().getAbsolutePath();

            if(exportPath.contains("\\"))
                exportPath += "\\";
            if(exportPath.contains("/"))
                exportPath += "/";

            ImportExport.exportGame(this.data.getMapName(),this.data.getName(),exportPath);
        }
    }
}

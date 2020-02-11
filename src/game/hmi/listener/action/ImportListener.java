package game.hmi.listener.action;

import common.save.DataSave;
import common.save.ImportExport;
import data.NeedToBeTranslated;
import data.config.Config;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire du bouton "importer"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class ImportListener implements ActionListener {
    /**
     * Textes
     */
    private static final String CHOOSE_FILE = NeedToBeTranslated.CHOOSE_FILE_TO_IMPORT;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String extension = Config.GAME_EXPORT_EXTENSION.replaceFirst(".","");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle(CHOOSE_FILE);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(Config.GAME_EXPORT_EXTENSION,extension));

        if(fileChooser.showOpenDialog(EnigmaGameLauncher.getInstance().getWindow()) == JFileChooser.APPROVE_OPTION){
            String importPath = fileChooser.getSelectedFile().getAbsolutePath();
            ImportExport.importGame(importPath);
        }
    }
}

package desktopLauncher;

import editor.EditorLauncher;
import editor.api.Application;
import editor.utils.EnigmaLabel;
import editor.utils.Window;
import editor.utils.ui.EnigmaButtonUI;
import editor.utils.EnigmaButton;
import editor.utils.ui.EnigmaLabelUI;

import javax.swing.*;
import java.awt.*;

/**
 * Lance la version pc de l'application
 */
public class DesktopLauncher implements Runnable {

    private static Application RUNNING_APP;
    private static EnigmaButton PLAY_BUTTON;
    private static EnigmaButton EDIT_BUTTON;

    public DesktopLauncher(){
        RUNNING_APP = null;
        PLAY_BUTTON = new EnigmaButton();
        PLAY_BUTTON = new EnigmaButton("Jouer");
        PLAY_BUTTON.addActionListener(new LauncherManagement(new EditorLauncher()));
        
        EDIT_BUTTON = new EnigmaButton();
        EDIT_BUTTON = new EnigmaButton("Editer une map");
        EDIT_BUTTON.addActionListener(new LauncherManagement(new EditorLauncher()));
    }

    public static void startApp(Application app){
        if(RUNNING_APP == null){
            RUNNING_APP = app;
            RUNNING_APP.start();
            PLAY_BUTTON.setText("Une application est déjà en cours d'execution");
            EDIT_BUTTON.setText("Une application est déjà en cours d'execution");
        }
    }

    public static void closeRunningApp(){
        if(RUNNING_APP != null){
            RUNNING_APP = null;
            PLAY_BUTTON.setText("Jouer");
            EDIT_BUTTON.setText("Editer une map");
        }
    }

    //cette méthode contient le code de lancement de l'éditeur
    //après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
    @Override
    public void run() {

        Window window = new Window("Enigma");
        window.setSize(Window.HALF_SCREEN_SIZE);
        window.setLocation(Window.CENTER);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setWindowBackground(Color.DARK_GRAY);

        JPanel background = window.getContentSpace();
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        int inset = 50;

        EnigmaLabel info = new EnigmaLabel("ENIGMA");
        EnigmaLabelUI infoUI = new EnigmaLabelUI();
        Color infoColor = new Color(100,100,100);
        infoUI.setAllBackgrounds(infoColor,infoColor,infoColor);
        infoUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        info.setLabelUI(infoUI);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset / 2, inset / 2, inset / 2, inset / 2);
        background.add(info, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset, inset, inset, inset / 4);
        background.add(PLAY_BUTTON, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset, inset, inset, inset / 4);
        background.add(EDIT_BUTTON, gbc);

        window.setVisible(true);
    }

    /**
     * Lance la version pc de l'application
     * @param args aucun
     */
    public static void main(String[] args) {
        //appelle après initialisation de la libgdx, l'éditeur
        SwingUtilities.invokeLater(new DesktopLauncher());
    }
}
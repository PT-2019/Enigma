import editor.listener.RunEditor;

import javax.swing.SwingUtilities;

/**
 * Lance la version pc de l'application
 */
public class DesktopLuncher {

    public static void main(String[] args) {
        //appelle après initialisation de la libgdx, l'éditeur
        SwingUtilities.invokeLater(new RunEditor());
    }

}

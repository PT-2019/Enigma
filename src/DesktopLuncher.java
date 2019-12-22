import editor.EditorLuncher;

import javax.swing.*;

/**
 * Lance la version pc de l'application
 *
 * @version 1.0
 * @since 1.0
 */
public class DesktopLuncher implements Runnable {

    public static void main(String[] ignore) {
        //appelle après initialisation de la libgdx, l'éditeur
        SwingUtilities.invokeLater(new DesktopLuncher());
    }

    //cette méthode contient le code de lancement de l'éditeur
    //après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
    @Override
    public void run() {
        EditorLuncher editorLuncher = EditorLuncher.setEditor(1200, 800);
        editorLuncher.start();
    }
}

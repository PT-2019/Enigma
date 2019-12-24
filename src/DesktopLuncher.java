import editor.EditorLuncher;

import javax.swing.SwingUtilities;

/**
 * Lance la version pc de l'application
 * @version 1.0
 */
public class DesktopLuncher implements Runnable {

    //cette méthode contient le code de lancement de l'éditeur
    //après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
    @Override
    public void run() {
        EditorLuncher editorLuncher = new EditorLuncher(800, 600);
        editorLuncher.start();
    }

    public static void main(String[] ignore) {
        //appelle après initialisation de la libgdx, l'éditeur
        SwingUtilities.invokeLater(new DesktopLuncher());
    }
}

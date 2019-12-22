import editor.EditorLuncher;

import javax.swing.SwingUtilities;

/**
 * Lance la version pc de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 1.0
 */
public class DesktopLuncher implements Runnable {

	private static final int WIDTH = 1200, HEIGHT = 800;

	public static void main(String[] ignore) {
		//appelle après initialisation de la libgdx, l'éditeur
		SwingUtilities.invokeLater(new DesktopLuncher());
	}

	//cette méthode contient le code de lancement de l'éditeur
	//après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
	@Override
	public void run() {
		EditorLuncher editorLuncher = EditorLuncher.setEditor(WIDTH, HEIGHT);
		editorLuncher.start();
	}
}

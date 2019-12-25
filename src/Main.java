import api.utils.annotations.Temporary;
import editor.EditorLauncher;
import starter.DesktopLauncher;

import javax.swing.SwingUtilities;

/**
 * Lance l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class Main {

	public static void main(String[] args) {
		//appelle après initialisation de la libgdx, l'éditeur
		SwingUtilities.invokeLater(new DesktopLauncher());
		//SwingUtilities.invokeLater(new EditorFastLuncher());
	}

	@Temporary(reason = "lancer l'éditeur rapidement", since = 4.0f)
	private static class EditorFastLuncher implements Runnable {
		@Override
		public void run() {
			EditorLauncher.setEditor(1200, 800);
			EditorLauncher.getInstance().start();
		}
	}
}

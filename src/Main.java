import api.utils.annotations.Temporary;
import editor.enigma.Enigma;
import starter.DesktopLauncher;
import starter.EditorLauncher;

import javax.swing.SwingUtilities;

/**
 * Lance l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
public class Main {

	public static void main(String[] args) {
		//appelle après initialisation de la libgdx, l'éditeur
		SwingUtilities.invokeLater(DesktopLauncher.getInstance());
	}

	@Temporary(reason = "lancer l'éditeur rapidement", since = 4.1f)
	@Deprecated
	public static final class MainFast {

		/**
		 * Lance l'éditeur rapidement plutôt que de passer par le menu
		 *
		 * @param ignore ignore
		 */
		public static void main(String[] ignore) {
			//appelle après initialisation de la libgdx, l'éditeur
			SwingUtilities.invokeLater(new EditorFastLuncher());
		}

		private static class EditorFastLuncher implements Runnable {
			@Override
			public void run() {
				EditorLauncher.setEditor(1200, 800);
				EditorLauncher.getInstance().start();
			}
		}
	}
}

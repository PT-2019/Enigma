package editor.utils;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import game.EnigmaGame;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Charge l'application libgdx dans un composant SWING
 *
 * @version 2.0 28 novembre 2019
 */
public class LoadGameLibgdxApplication {

	/**
	 * Charge l'application libgdx dans un composant SWING
	 * <p>
	 * For a JFrame, container should ne {@link JFrame#getContentPane()}.
	 */
	public static void load(Container container, @NotNull JFrame frame) {
		//récupère contenu fenêtre et on la vide
		//final Container container = frame.getContentPane();
		container.removeAll();
		container.setLayout(new BorderLayout());

		//Récupère le jeu
		LwjglAWTCanvas canvas = new LwjglAWTCanvas(EnigmaGame.getInstance());
		container.add(canvas.getCanvas(), BorderLayout.CENTER);//ajoute le jeu

		//vire tous les listeners de la classe CloseWindowLibgdxApplication
		//pour éviter un conflit
		for (WindowListener windowListener : frame.getWindowListeners()) {
			if (windowListener instanceof CloseWindowLibgdxApplication)
				frame.removeWindowListener(windowListener);
		}

		//ajoute un Listener CloseWindowLibgdxApplication qui ferme l'application libgdx
		//dès que la fenêtre est fermée
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new CloseWindowLibgdxApplication(canvas));
		frame.revalidate();//met à jour
	}

	/**
	 * Ferme une fenêtre qui contient une application LIBGDX
	 *
	 * @version 1.0
	 */
	public static final class CloseWindowLibgdxApplication extends WindowAdapter {

		/**
		 * L'application Libgdx
		 */
		private final LwjglAWTCanvas application;

		/**
		 * Ferme une fenêtre qui contient une application LIBGDX
		 *
		 * @param application canvas de l'application
		 */
		public CloseWindowLibgdxApplication(LwjglAWTCanvas application) {
			this.application = application;
		}

		/**
		 * Méthode appelée si on cherche à fermer la fenêtre.
		 * <p>
		 * Si la fenêtre contient un application libgdx, elle la ferme
		 * proprement avant de quitter
		 *
		 * @param windowEvent évenement de fermeture
		 */
		@Override
		public void windowClosing(WindowEvent windowEvent) {
			if (this.application != null) {
				this.application.stop();
				this.application.exit();
			}
			System.exit(0);
		}
	}
}

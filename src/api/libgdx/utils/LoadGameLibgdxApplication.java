package api.libgdx.utils;

import api.libgdx.LibgdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowListener;

/**
 * Charge l'application libgdx dans un composant SWING
 *
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 1.0
 */
public final class LoadGameLibgdxApplication<T extends LibgdxGame> {

	/**
	 * Garde l'instance unique au moment T du chargeur
	 */
	private static LoadGameLibgdxApplication inst;

	/**
	 * le jeu qui veux être lancé
	 *
	 * @see LibgdxGame
	 * @since 4.0
	 */
	private T game;

	/**
	 * Crée une instance qui garde le jeu qui veux être lancé
	 *
	 * @param instance une instance du jeu qui veux être lancé
	 * @since 4.0
	 */
	private LoadGameLibgdxApplication(T instance) {
		this.game = instance;
	}

	/**
	 * Charge l'application libgdx dans un composant SWING
	 * <p>
	 * For a JFrame, container should ne {@link JFrame#getContentPane()}.
	 *
	 * @param container conteneur de la libgdx qui va être chargée
	 * @param frame     la fenêtre swing
	 * @since 3.0
	 */
	public static void load(Container container, @NotNull JFrame frame) {
		if (Gdx.app != null) {//si déjà lancée
			Gdx.app.postRunnable(() -> load(container, frame));
			return;
		}
		//récupère contenu fenêtre et on la vide
		//final Container container = frame.getContentPane();
		container.removeAll();
		container.setLayout(new BorderLayout());

		//Récupère le jeu
		if (inst == null) throw new IllegalStateException("pas de d'instance de libgdx game définie.");
		LwjglAWTCanvas canvas = new LwjglAWTCanvas(inst.game);
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
	 * Définit le jeu qui doit être lancé.
	 * Attention, le jeu est redéfini a chaque appel.
	 *
	 * @param game le jeu a lancer
	 * @param <T>  qui extends LibgdxGame
	 * @return l'instance du jeu
	 * @since 4.0
	 */
	public static <T extends LibgdxGame> LoadGameLibgdxApplication setGame(T game) {
		return inst = new LoadGameLibgdxApplication<>(game);
	}
}

package api.utils;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Ferme une fenêtre qui contient une application LIBGDX
 *
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 1.0
 */
public final class CloseWindowLibgdxApplication extends WindowAdapter {

	public static ArrayList<LwjglAWTCanvas> applications = new ArrayList<>();
	/**
	 * L'application Libgdx
	 */
	private final LwjglAWTCanvas application;

	/**
	 * Ferme une fenêtre qui contient une application LIBGDX
	 *
	 * @param application canvas de l'application
	 * @since 1.0
	 */
	public CloseWindowLibgdxApplication(LwjglAWTCanvas application) {
		this.application = application;
		applications.add(application);
	}

	/**
	 * Méthode appelée si on cherche à fermer la fenêtre.
	 * <p>
	 * Si la fenêtre contient un application libgdx, elle la ferme
	 * proprement avant de quitter
	 *
	 * @param windowEvent évenement de fermeture
	 * @since 1.0
	 */
	@Override
	public void windowClosing(WindowEvent windowEvent) {
		if (this.application != null && windowEvent == null) {
			applications.remove(this.application);
			this.application.stop();
			this.application.exit();
		}
	}

	public void stop() {
		if (this.application != null)
			this.application.stop();
	}

	public void exit() {
		if (this.application != null)
			this.application.exit();
	}


	public LwjglAWTCanvas getApplication() {
		return application;
	}
}
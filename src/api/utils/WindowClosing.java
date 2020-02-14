package api.utils;

import api.utils.annotations.ConvenienceClass;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Interface fonctionnelle d'un windowListener
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 13/02/2020
 * @since 6.0 13/02/2020
 */
@FunctionalInterface
@ConvenienceClass
public interface WindowClosing extends WindowListener {

	/**
	 * Appelée à la fermeture de la fenêtre
	 * .addWindowListener((WindowClosing) e1 -> { /*code*\/ });
	 *
	 * @param e event
	 */
	@Override
	void windowClosing(WindowEvent e);

	//cache toutes les autres méthodes

	default void windowOpened(WindowEvent e) {
	}

	default void windowClosed(WindowEvent e) {
	}

	default void windowIconified(WindowEvent e) {
	}

	default void windowDeiconified(WindowEvent e) {
	}

	default void windowActivated(WindowEvent e) {
	}

	default void windowDeactivated(WindowEvent e) {
	}
}

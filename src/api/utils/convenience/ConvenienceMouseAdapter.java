package api.utils.convenience;

import api.utils.annotations.ConvenienceClass;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Interface pratique d'un mouseListener
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 13/02/2020
 * @since 6.0 13/02/2020
 */
@ConvenienceClass
public interface ConvenienceMouseAdapter extends MouseListener {

	@Override
	default void mouseClicked(MouseEvent e) {
	}

	@Override
	default void mousePressed(MouseEvent e) {
	}

	@Override
	default void mouseReleased(MouseEvent e) {
	}

	@Override
	default void mouseEntered(MouseEvent e) {
	}

	@Override
	default void mouseExited(MouseEvent e) {
	}
}

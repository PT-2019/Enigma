package editor.entity.display;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment entityViewListener and write Readme.md in entity.display
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EntityViewListener implements MouseListener {

	private JPopupMenu menu;

	public EntityViewListener(JPopupMenu menu) {
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		JComponent composant = (JComponent) mouseEvent.getSource();
		composant = (JComponent) composant.getParent();
		if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
			menu.show(composant, mouseEvent.getX(), mouseEvent.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}

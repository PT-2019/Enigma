package editor.entity.view;

import com.badlogic.gdx.graphics.Camera;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * TODO: comment EntityPopMenu and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class EntityPopMenu extends JPopupMenu {

	public EntityPopMenu(RoomView v, CollisionView collision, Camera cam) {
		JMenu zoom = new JMenu("Zoom");
		JMenu eng = new JMenu("Case");
		JMenu room = new JMenu("Rooms");
		JMenuItem gameZoom = new JMenuItem("Zoom du jeu");
		JMenuItem map = new JMenuItem("Voir toute la map");
		JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
		JCheckBoxMenuItem bloc = new JCheckBoxMenuItem("Bloquante");
		this.add(zoom);
		this.add(eng);
		this.add(room);
		room.add(dips);
		zoom.add(gameZoom);
		zoom.add(map);
		eng.add(bloc);

		PopMenuListener listener = new PopMenuListener(v, cam, collision);
		dips.addItemListener(listener);
		gameZoom.addActionListener(listener);
		map.addActionListener(listener);
		bloc.addItemListener(listener);
	}

	/**
	 * Constructeur pour des test
	 *
	 * @since 4.0
	 */
	@Deprecated
	public EntityPopMenu() {
		JMenu zoom = new JMenu("Zoom");
		JMenu eng = new JMenu("Case");
		JMenu room = new JMenu("Rooms");
		JMenuItem gameZoom = new JMenuItem("Zoom du jeu");
		JMenuItem map = new JMenuItem("Voir toute la map");
		JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
		JCheckBoxMenuItem bloc = new JCheckBoxMenuItem("Bloquante");
		this.add(zoom);
		this.add(eng);
		this.add(room);
		room.add(dips);
		zoom.add(gameZoom);
		zoom.add(map);
		eng.add(bloc);
	}
}

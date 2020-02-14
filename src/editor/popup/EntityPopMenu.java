package editor.popup;

import common.hud.EnigmaWindow;
import common.map.AbstractMap;
import data.NeedToBeTranslated;
import editor.popup.listeners.CollisionListener;
import editor.popup.listeners.EnigmaStateListener;
import editor.popup.listeners.ZoomListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Popup du clic droit
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0
 */
public class EntityPopMenu extends JPopupMenu {

	/**
	 * Popup du clic droit
	 *
	 * @param map    map
	 * @param window fenêtre
	 * @since 4.0
	 */
	EntityPopMenu(AbstractMap map, EnigmaWindow window) {
		//menu
		JMenu zoom = new JMenu(NeedToBeTranslated.ZOOM);
		JMenu cursor = new JMenu(NeedToBeTranslated.CURSOR);
		JMenu render = new JMenu(NeedToBeTranslated.SHOW);

		//items
		JMenuItem zoomInGame = new JMenuItem(NeedToBeTranslated.ZOOM_IN_GAME);
		JMenuItem zoomAll = new JMenuItem(NeedToBeTranslated.ZOOM_FIT);
		JMenuItem zoomBase = new JMenuItem(NeedToBeTranslated.ZOOM_BASE);

		JMenuItem eraser = new JMenuItem(NeedToBeTranslated.ERASER);
		JMenuItem normal = new JMenuItem(NeedToBeTranslated.BRUSH);
		JMenuItem move = new JMenuItem(NeedToBeTranslated.MOVE);

		JCheckBoxMenuItem collision = new JCheckBoxMenuItem(NeedToBeTranslated.CASES_BLOCKED);

		//listeners
		//changer le zoom
		ZoomListener zoomListener = new ZoomListener(map);
		zoomInGame.addActionListener(zoomListener);
		zoomAll.addActionListener(zoomListener);
		zoomBase.addActionListener(zoomListener);

		//changer le curseur
		EnigmaStateListener enigmaStateListener = new EnigmaStateListener(window);
		eraser.addActionListener(enigmaStateListener);
		normal.addActionListener(enigmaStateListener);
		move.addActionListener(enigmaStateListener);

		//changer l'affichage
		collision.addItemListener(new CollisionListener(map));

		//add to menu
		zoom.add(zoomInGame);
		zoom.add(zoomAll);
		zoom.add(zoomBase);

		cursor.add(normal);
		cursor.add(eraser);
		cursor.add(move);

		render.add(collision);

		//addToPopMenu
		this.add(zoom);
		this.add(cursor);
		this.add(render);
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

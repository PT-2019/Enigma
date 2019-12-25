package editor.entity.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * TODO: comment PopMenuListener and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class PopMenuListener implements ItemListener, ActionListener {

	private RoomView view;

	/**
	 * Camera qui se base sur la taille de la map, permet d'avoir le point
	 * de vue de toute la map
	 */
	private Camera camera;

	/**
	 * Largeur de la caméra sans le zoom du jeu
	 */
	private float viewportWidth;

	/**
	 * Longeur camera sans zoom du jeu
	 */
	private float viewportHeight;

	private CollisionView collision;

	public PopMenuListener(RoomView v, Camera cam, CollisionView col) {
		view = v;
		camera = cam;
		collision = col;

		viewportWidth = v.getTile() * v.getMapWidth();
		viewportHeight = v.getHeightSize();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBoxMenuItem tmp = (JCheckBoxMenuItem) e.getItem();
		if (tmp.getActionCommand().equals("Afficher")) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				view.setVisible(true);
			} else {
				view.setVisible(false);
			}
		} else {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				collision.setVisible(true);
			} else {
				collision.setVisible(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Zoom du jeu")) {
			float x = camera.position.x;
			float y = camera.position.y;
			((OrthographicCamera) camera).setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			((OrthographicCamera) camera).zoom = 1;
			//pour garder la caméra a la même position
			camera.position.x = x;
			camera.position.y = y;
		} else {
			float x = camera.position.x;
			float y = camera.position.y;

			((OrthographicCamera) camera).setToOrtho(false, viewportWidth, viewportHeight);
			((OrthographicCamera) camera).zoom = 1;
			//pour garder la caméra a la même position
			camera.position.x = x;
			camera.position.y = y;
		}
	}
}

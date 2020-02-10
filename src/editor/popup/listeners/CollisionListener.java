package editor.popup.listeners;

import common.map.AbstractMap;
import data.Layer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Activation, désactivation de l'affichage du niveau de collision
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CollisionListener implements ItemListener {

	private AbstractMap map;

	public CollisionListener(AbstractMap map) {
		this.map = map;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			this.map.showLayer(Layer.COLLISION, true);
		} else {
			this.map.showLayer(Layer.COLLISION, false);
		}
	}
}

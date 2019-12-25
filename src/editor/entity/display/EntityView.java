package editor.entity.display;

import api.entity.Entity;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Image;

/**
 * TODO: comment entityView and write Readme.md in entity.display
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EntityView extends JComponent {

	private Entity entity;

	public EntityView(Entity ent) {
		this.setOpaque(true);
		entity = ent;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics graphics = g.create();

		if (this.isOpaque()) {
			graphics.setColor(this.getBackground());
			graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		Image img = entity.getTexture().getImage();

		graphics.drawImage(img, 0, 0, this);
	}
}

package editor.entities.display;

import editor.entities.interfaces.Entity;

import javax.swing.*;
import java.awt.*;

public class EntityView extends JComponent {

	private Entity entity;

	public EntityView(Entity ent){
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

		graphics.drawImage(img,0,0,this);
	}
}

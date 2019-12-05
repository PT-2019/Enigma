package editor.entities;

import editor.entities.interfaces.Entity;
import editor.entities.item.Book;
import editor.entities.display.EntityPopMenu;
import editor.entities.display.EntityView;
import editor.entities.display.EntityViewListener;
import editor.textures.Texture;

import javax.swing.*;
import java.awt.*;

public class EntityDisplayTestMain {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));

		Entity ent = new Book();

		ImageIcon img = new ImageIcon("assets/monsters/019.png");

		ent.setTexture(new Texture(5,img.getImage()));

		EntityView view = new EntityView(ent);

		EntityViewListener listener = new EntityViewListener(new EntityPopMenu());

		view.addMouseListener(listener);

		p.add(view);

		f.add(p, BorderLayout.CENTER);

		f.setVisible(true);
	}
}

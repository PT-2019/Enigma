package editor.entity;

import general.entities.Entity;
import general.entities.consumable.Book;
import general.utils.textures.Texture;

import javax.swing.*;
import java.awt.*;

@Deprecated
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

		//EntityView view = new EntityView(ent);

		//EntityViewListener listener = new EntityViewListener(new EntityPopMenu());

		//view.addMouseListener(listener);

		//p.add(view);

		f.add(p, BorderLayout.CENTER);

		f.setVisible(true);
	}
}

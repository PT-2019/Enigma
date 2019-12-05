package editor.Entity.dispose;

import javax.swing.*;
import editor.entity.dispose.EntityPopMenu;
import editor.entity.dispose.EntityView;
import editor.entity.interfaces.Entity;
import editor.entity.item.Book;
import editor.listener.EntityViewListener;
import editor.texture.Texture;

import java.awt.*;

public class TestEntityDisp {
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

package common.map.view;

import javax.swing.*;
import java.awt.*;

public class MapLauncher extends JFrame {

    public MapLauncher(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel container =(JPanel) getContentPane();
        container.setLayout(new BorderLayout());

        //LwjglAWTCanvas canvas = new LwjglAWTCanvas(new TiledTest(container));
        //container.add(canvas.getCanvas(), BorderLayout.CENTER);

        pack();
        setVisible(true);
        setSize(800, 600);
        //addWindowListener(new CloseWindowLibgdxApplication(canvas));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MapLauncher();
            }
        });
    }
}

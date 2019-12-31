package editor.entity.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CasePopUp extends JDialog implements WindowListener {

    private MapTestScreenCell cell;

    private TiledMap tileMap;

    private JButton next = new JButton();

    private JButton preview = new JButton();

    private JLabel eng = new JLabel("Gérer les énigmes");

    private JButton b3 = new JButton("Supprimer");

    private JLabel info = new JLabel();

    public CasePopUp(JComponent component, TiledMap tiledMap){
        super((JFrame)component.getRootPane().getParent());
        this.setSize(300,300);
        this.setLocation(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        this.tileMap = tiledMap;
    }

    public void display(){
        TiledMapTileLayer l = cell.getLayer();
        MapLayers layers = tileMap.getLayers();
        TiledMapTileLayer layer;

        int index = layers.getIndex(l.getName());
        int x,y;

        this.setLayout(new GridLayout(2,3));
        if (index < 3){
            next.setText(layers.get(index+1).getName());
            this.add(next);

            layer = (TiledMapTileLayer) layers.get(index+1);

            y = cell.getIndex()/layer.getWidth();
            x = cell.getIndex()%layer.getWidth();

            next.addActionListener(new CasePopListener(layer.getCell(x,y),this));
        }

        this.setTitle(l.getName());

        if (cell.getEntity() == null){
            info.setText("Aucune entité");
        }else{
            System.out.println(cell.getEntity().getClass());
            String nom = "";
            if (cell.getEntity().getClassName().equals("editor.map.Room"))
                nom = "room";
            else if(cell.getEntity().getClassName().equals("editor.entity.item.Chest"))
                nom = "coffre";
            else if(cell.getEntity().getClassName().equals("editor.entity.item.Pane"))
                nom = "Panneau";
            else if(cell.getEntity().getClassName().equals("editor.entity.item.Switch"))
                nom = "Commutateur";
            else if(cell.getEntity().getClassName().equals("editor.entity.item.Button"))
                nom = "Bouton";
            else if(cell.getEntity().getClassName().equals("editor.entity.item.Book"))
                nom = "Livre";
                info.setText(nom);
        }
        this.add(info);

        if (index > 0 ){
            preview.setText(layers.get(index-1).getName());
            this.add(preview);

            layer = (TiledMapTileLayer) layers.get(index-1);

            y = cell.getIndex()/layer.getWidth();
            x = cell.getIndex()%layer.getWidth();

            preview.addActionListener(new CasePopListener(layer.getCell(x,y),this));
        }

        this.add(eng);
        this.add(b3);
        this.setVisible(true);
    }



    public void setCell(MapTestScreenCell cell) {
        this.cell = cell;
    }

    public void clean(){
        this.remove(info);
        info = new JLabel();
        this.remove(b3);
        this.remove(next);
        next = new JButton();

        this.remove(eng);
        this.remove(preview);

        preview = new JButton();
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        clean();
        this.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}

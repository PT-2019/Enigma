package editor.map.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.datas.Layer;
import editor.entity.interfaces.Entity;
import editor.map.Case;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CasePopUp extends JDialog implements WindowListener {

    private TiledMapTileLayer.Cell cell;

    private TiledMap tileMap;

    JLabel label =new JLabel();

    JButton next = new JButton();

    JButton preview = new JButton();

    JLabel eng = new JLabel("Gérer les énigmes");

    JButton b3 = new JButton("Supprimer");

    public CasePopUp(JComponent component,TiledMap tiledMap){
        super((JFrame)component.getRootPane().getParent(),"Case Information",false);
        this.setSize(300,300);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        this.tileMap = tiledMap;
    }

    public void display(){
        TiledMapTileLayer l = ((MapLibgdxCell)cell).getLayer();
        MapLayers layers = tileMap.getLayers();

        int index = layers.getIndex(l.getName());

        this.setLayout(new GridLayout(2,3));
        if (index < 3){
            next.setText(layers.get(index+1).getName());
            this.add(next);

            TiledMapTileLayer tmp = (TiledMapTileLayer) layers.get(index+1);

            MapLibgdxCell cellTmp = (MapLibgdxCell) cell;

            System.out.println("X :"+cellTmp.getX());

            System.out.println("Y :"+cellTmp.getY());

            next.addActionListener(new CasePopListener(tmp.getCell(cellTmp.getX(),cellTmp.getY()),this));
        }

        label.setText(l.getName());
        this.add(label);

        if (index > 0 ){
            preview.setText(layers.get(index-1).getName());
            this.add(preview);
        }
        this.add(eng);
        this.add(b3);
        this.setVisible(true);
    }



    public void setCell(TiledMapTileLayer.Cell cell) {
        this.cell = cell;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        this.remove(label);
        this.remove(b3);
        this.remove(next);
        this.remove(eng);
        this.remove(preview);
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

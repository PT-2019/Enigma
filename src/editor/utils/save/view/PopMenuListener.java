package editor.utils.save.view;

import api.enums.Layer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopMenuListener implements ItemListener, ActionListener {

    /**
       Camera qui se base sur la taille de la map, permet d'avoir le point
       de vue de toute la map
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

    private TiledMap map;

    public PopMenuListener(Camera cam, TiledMap map){
        this.camera = cam;

        this.map = map;

        TiledMapTileLayer layer =(TiledMapTileLayer) map.getLayers().get(0);

        this.viewportWidth = layer.getTileWidth() * layer.getWidth();
        this.viewportHeight = layer.getTileHeight() * layer.getHeight();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            map.getLayers().get(Layer.COLLISION.name()).setVisible(true);
        }else{
            map.getLayers().get(Layer.COLLISION.name()).setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Zoom du jeu")){
            float x = camera.position.x;
            float y = camera.position.y;

            ((OrthographicCamera) camera).setToOrtho(false, viewportWidth,viewportHeight);
            ((OrthographicCamera) camera).zoom = 1;
            //pour garder la caméra a la même position
            camera.position.x = x;
            camera.position.y = y;
        }else{
            float x = camera.position.x;
            float y = camera.position.y;
            ((OrthographicCamera) camera).setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            ((OrthographicCamera) camera).zoom = 1;
            //pour garder la caméra a la même position
            camera.position.x = x;
            camera.position.y = y;
        }
    }
}

package editor.entity.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EntityPopMenu extends JPopupMenu {

    public EntityPopMenu(TiledMap maps, Camera cam){
        JMenu zoom = new JMenu("Zoom");
        JMenu eng = new JMenu("Case");
        JMenuItem gameZoom = new JMenuItem("Zoom du jeu");
        JMenuItem map = new JMenuItem("Voir toute la map");
        JCheckBoxMenuItem bloc = new JCheckBoxMenuItem("Bloquante");
        this.add(zoom);
        this.add(eng);
        zoom.add(gameZoom);
        zoom.add(map);
        eng.add(bloc);

        PopMenuListener listener = new PopMenuListener(cam,maps);
        gameZoom.addActionListener(listener);
        map.addActionListener(listener);
        bloc.addItemListener(listener);
    }

    /**
     * Constructeur pour des test
     * @since 4.0
     */
    @Deprecated
    public EntityPopMenu(){
        JMenu zoom = new JMenu("Zoom");
        JMenu eng = new JMenu("Case");
        JMenu room = new JMenu("Rooms");
        JMenuItem gameZoom = new JMenuItem("Zoom du jeu");
        JMenuItem map = new JMenuItem("Voir toute la map");
        JCheckBoxMenuItem dips = new JCheckBoxMenuItem("Afficher");
        JCheckBoxMenuItem bloc = new JCheckBoxMenuItem("Bloquante");
        this.add(zoom);
        this.add(eng);
        this.add(room);
        room.add(dips);
        zoom.add(gameZoom);
        zoom.add(map);
        eng.add(bloc);
    }
}

package editor.utils.save.view;

import com.badlogic.gdx.graphics.Camera;

import javax.swing.*;

public class EntityPopMenu extends JPopupMenu {

    public EntityPopMenu(RoomView v,CollisionView collision, Camera cam){
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

        PopMenuListener listener = new PopMenuListener(v,cam,collision);
        dips.addItemListener(listener);
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

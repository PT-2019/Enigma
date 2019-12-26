package editor.utils.save.view.cases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.entity.EntitySerializable;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Fenetre qui est afficher lorsqu'on clique sur une case
 *
 */
public class CasePopUp extends JDialog implements WindowListener {

    private JPanel navigation = new JPanel();

    private JPanel extra = new JPanel();

    private MapLibgdxCell cell;

    private TiledMap tileMap;

    private JButton next = new JButton();

    private JButton preview = new JButton();

    private JButton eng = new JButton("Gérer les énigmes");

    private JButton del = new JButton("Supprimer");

    private JLabel info = new JLabel();

    private Checkbox walkable = new Checkbox("Case bloquante");

    /**
     * Affiche la gestion de contenu pour les entités. //TODO ce fameux menu de gestion
     * @see api.entity.interfaces.Content
     */
    private JButton contentButton = new JButton("Dialogue de l'objet");

    /**
     * Affiche la gestion de dialogue //TODO ce fameux menu de gestion de dialogue
     */
    private JButton entityButton = new JButton("Gérer le dialogue");

    private JPanel passage = new JPanel();

    private JCheckBox hideRigth = new JCheckBox("Cacher room à droite");

    private JCheckBox hideLeft = new JCheckBox("Cacher room à gauche");

    public CasePopUp(JComponent component,TiledMap tiledMap){
        super((JFrame)component.getRootPane().getParent());
        this.setSize(400,300);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        this.tileMap = tiledMap;
        navigation.setLayout(new GridLayout(2,3));
        extra.setLayout(new GridLayout(1,3));
        this.setLayout(new GridLayout(2,1));
        passage.setLayout(new GridLayout(2,1));
    }

    public void display(){
        TiledMapTileLayer l = cell.getLayer();
        MapLayers layers = tileMap.getLayers();
        int index = layers.getIndex(l.getName());
        TiledMapTileLayer layer = null;
        //si il y a un objet content, ou si c'est une entitée
        boolean iscontent = false, isEntity = false, isPassage = false;

        this.setTitle(l.getName());

        if (cell.getEntity() == null){
            info.setText("Aucune entité");
        }else{
            System.out.println(cell.getEntity().getClassName());
            String nom = "";
            String className = cell.getEntity().getClassName();

            //on regarde les interfaces implémenter par l'entité
            try {
                Class c = Class.forName(className);
                Class[] interfaces = c.getInterfaces();

                for (int i = 0; i < interfaces.length; i++) {
                    if(interfaces[i].getName().equals("api.entity.interfaces.Content")){
                        iscontent = true;
                    }

                    if (interfaces[i].getName().equals("api.entity.interfaces.Entity")){
                        isEntity = true;
                    }

                    if (interfaces[i].getName().equals("api.entity.interfaces.Passage")){
                        isPassage = true;
                    }
                    System.out.println(interfaces[i]);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (className.equals("editor.map.Room"))
                nom = "room";
            else if(className.equals("editor.entity.item.Chest"))
                nom = "coffre";
            else if(className.equals("editor.entity.item.Pane"))
                nom = "Panneau";
            else if(className.equals("editor.entity.item.Switch"))
                nom = "Commutateur";
            else if(className.equals("editor.entity.item.Button"))
                nom = "Bouton";
            else if(className.equals("editor.entity.item.Book"))
                nom = "Livre";
            else if(className.equals("editor.entity.item.Door"))
                nom = "Porte";
            else if(className.equals("editor.entity.item.PressurePlate"))
                nom = "Plaque de pression";
            else if(className.equals("editor.entity.player.Player"))
                nom = "Héros";

            info.setText(nom);
        }
        displayNavBouton(index,layers,layer);

        layer = (TiledMapTileLayer) layers.get(index);
        navigation.add(eng);
        navigation.add(walkable);
        walkable.addItemListener(new PopItemListener()); //todo ce listener
        navigation.add(del);

        if (iscontent){
            extra.add(contentButton);
        }
        if (isEntity){
            extra.add(entityButton);
        }
        if (isPassage){
            //TODO un listener pour ces 2 composants
            passage.add(hideLeft);
            passage.add(hideRigth);
            extra.add(passage);
        }
        del.addActionListener(new CaseDelete(cell,layer,info));

        this.add(navigation);
        this.add(extra);
        this.setVisible(true);
    }

    public void setCell(MapLibgdxCell cell) {
        this.cell = cell;
    }

    //TODO essayer d'optimiser tout ça
    public void clean(){
        this.remove(navigation);
        this.remove(extra);

        navigation.remove(next);
        next = new JButton();

        navigation.remove(eng);
        navigation.remove(del);
        del = new JButton("Supprimer");

        navigation.remove(preview);
        preview = new JButton();

        navigation.remove(info);
        info = new JLabel();

        navigation.remove(walkable);
        walkable = new Checkbox("Case bloquante");

        extra.remove(passage);
        passage.remove(hideLeft);
        passage.remove(hideRigth);
        passage = new JPanel();


        extra.remove(contentButton);
        contentButton = new JButton("Dialogue de l'objet");
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

    public MapLibgdxCell getCell() {
        return cell;
    }

    /**
     * Méthode qui dispose les différents bouttons de naviguation
     */
    private void displayNavBouton(int index,MapLayers layers,TiledMapTileLayer layer){
        int x,y;
        if (index < 3){
            next.setText(layers.get(index+1).getName());
            navigation.add(next);

            layer = (TiledMapTileLayer) layers.get(index+1);

            y = cell.getIndex()/layer.getWidth();
            x = cell.getIndex()%layer.getWidth();
            next.addActionListener(new CasePopListener(layer.getCell(x,y),this));
        }
        navigation.add(info);
        if (index > 0 ){
            preview.setText(layers.get(index-1).getName());
            navigation.add(preview);

            layer = (TiledMapTileLayer) layers.get(index-1);

            y = cell.getIndex()/layer.getWidth();
            x = cell.getIndex()%layer.getWidth();
            preview.addActionListener(new CasePopListener(layer.getCell(x,y),this));
        }
    }
}

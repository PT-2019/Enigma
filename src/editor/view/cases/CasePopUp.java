package editor.view.cases;

import api.entity.GameObject;
import api.entity.types.EnigmaContainer;
import api.enums.Layer;
import api.enums.TypeEntite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.enigma.Enigma;

import editor.enigma.create.listeners.PopButtonListener;
import editor.view.cases.listeners.CaseDelete;
import editor.view.cases.panel.NavigationPanel;
import editor.view.listeners.PopItemListener;
import game.entity.map.MapTestScreenCell;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Iterator;

/**
 * Fenetre qui est afficher lorsqu'on clique sur une case
 *
 */
public class CasePopUp extends AbstractPopUp{
    /**
     * Panneau qui gère la navigation entre les différentes entités
     */
    private NavigationPanel navigation;

    private JPanel extra = new JPanel();

    private TiledMap tileMap;

    private JButton eng = new JButton("Gérer les énigmes");

    private JButton del = new JButton("Supprimer");

    private Checkbox walkable = new Checkbox("Case bloquante");

    /**
     * L'entité actuelle choisi par l'utilisateur
     */
    private GameObject enigmaCurrent;

    /**
     * Affiche la gestion de contenu pour les entités. //TODO ce fameux menu de gestion
     * @see api.entity.types.Content
     */
    private JButton contentButton = new JButton("Dialogue de l'objet");

    /**
     * Affiche la gestion de dialogue //TODO ce fameux menu de gestion de dialogue
     */
    private JButton entityButton = new JButton("Gérer le dialogue");

    private JPanel passage = new JPanel();

    private JCheckBox hideRigth = new JCheckBox("Cacher room à droite");

    private JCheckBox hideLeft = new JCheckBox("Cacher room à gauche");

    private JComponent component;

    public CasePopUp(JComponent component,TiledMap tiledMap){
        super((JFrame)component.getRootPane().getParent(),"",false);
        this.component = component;
        this.setSize(400,200);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.tileMap = tiledMap;
        this.setLayout(new GridLayout(2,1));
        this.initComponent();
    }

    private void initComponent(){
        navigation = new NavigationPanel(this);
        extra = new JPanel();

        eng = new JButton("Gérer les énigmes");
        del = new JButton("Supprimer");
        walkable = new Checkbox("Case bloquante");
        passage.setLayout(new GridLayout(2,1));
        navigation.setLayout(new GridLayout(1,3));
        extra.setLayout(new GridLayout(2,3));
    }

    public void display(){
        TiledMapTileLayer l = cell.getLayer();
        MapLayers layers = tileMap.getLayers();
        int index = layers.getIndex(l.getName());
        TiledMapTileLayer layer;
        TiledMapTileLayer collision = (TiledMapTileLayer) tileMap.getLayers().get(Layer.COLLISION.name());

        if (cell.getEntity() instanceof EnigmaContainer) {
            EnigmaContainer eni = (EnigmaContainer) cell.getEntity();
            if (eni != null) {
                Iterator<Enigma> it = eni.getAllEnigmas();

                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            }
        }

        //si il y a un objet content, ou si c'est une entitée
        boolean iscontent = false, isEntity = false, isPassage = false,isenigma = false;

        this.setTitle(l.getName());

        if (cell.getEntity() == null){
            navigation.setText("Aucune entité");
        }else{
            System.out.println(cell.getEntity().getClass());
            String className = cell.getEntity().getClass().getName();

            EnumMap<TypeEntite,Boolean> tmp = cell.getEntity().getImplements();

            if(tmp.get(TypeEntite.item)){
                isenigma = true;
            }

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

            navigation.setClassText(className);
        }

        navigation.displayNavBouton(index,layers);

        layer = (TiledMapTileLayer) layers.get(index);
        TiledMapTileLayer.Cell cellTmp = collision.getCell(cell.getIndex()%layer.getWidth(),cell.getIndex()/layer.getWidth());
        if (cellTmp.getTile() != null){
            walkable.setState(true);
        }
        extra.add(walkable);
        walkable.addItemListener(new PopItemListener(tileMap,cell));
        extra.add(del);

        if (iscontent){
            extra.add(contentButton);
        }
        if (isEntity){
            extra.add(entityButton);
        }
        if (isenigma){
            extra.add(eng);
            eng.addActionListener(new PopButtonListener(this,cell));
        }
        if (isPassage){
            //TODO un listener pour ces 2 composants
            passage.add(hideLeft);
            passage.add(hideRigth);
            extra.add(passage);
        }
        del.addActionListener(new CaseDelete(cell,layer,navigation.getInfo()));

        this.add(navigation);
        this.add(extra);
        this.setVisible(true);
    }

    public void setCell(MapTestScreenCell cell) {
        this.cell = cell;
    }

    /**
     * On élimine tout les composants de la fenetre puis on en créer de nouveau
     */
    //TODO essayer d'optimiser tout ça
    public void clean(){
        extra.remove(eng);
        extra.remove(del);
        extra.remove(walkable);
        extra.remove(passage);
        passage.remove(hideLeft);
        passage.remove(hideRigth);
        extra.remove(contentButton);
        this.remove(navigation);
        this.remove(extra);
        initComponent();
    }

    public TiledMap getTileMap() {
        return tileMap;
    }

    public JComponent getComponent(){
        return this.component;
    }

    public GameObject getEnigmaCurrent() {
        return enigmaCurrent;
    }

    public void setEnigmaCurrent(GameObject enigmaCurrent) {
        this.enigmaCurrent = enigmaCurrent;
    }
}

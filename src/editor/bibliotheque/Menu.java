package editor.bibliotheque;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

/**
 *  classe Menu
 *  @author Jorys-Micke ALAÏS
 *  @author Louka DOZ
 *  @author Loic SENECAT
 *  @author Quentin RAMSAMY-AGEORGES
 *
 *  @version 2.0 05 décembre 2019
 */
public class Menu extends JFrame implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {

    private JScrollPane scroll;
    private CardLayout pageObjet=new CardLayout();
    private ChoixObjet pages;
    private DragSource dragSource;

    private static final DataFlavor[] dataflavor = { null };
    private Object object;
    static {
        try {
            dataflavor[0] = new DataFlavor(
                    DataFlavor.javaJVMLocalObjectMimeType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @since 1.0 10 novembre 2019
     */
    public Menu(){
        this.setTitle("Création de l'escape Game");
        this.setSize(1500,1000);
        this.setMinimumSize(new Dimension(800,500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.setLayout(new GridBagLayout());

        //création de la zone de choix du menu d'objets
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(1,4,0,0));
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx=0;
        c3.gridy=0;
        c3.gridwidth=GridBagConstraints.REMAINDER;
        c3.gridheight=2;
        c3.fill=GridBagConstraints.BOTH;
        c3.anchor=GridBagConstraints.NORTH;
        c3.insets= new Insets(0,0,0,0);
        c3.weightx=0f;
        c3.weighty=0.01f;

        JButton b1 = new JButton("Salles");
        JButton b2 = new JButton("Décors");
        JButton b3 = new JButton("Murs");
        JButton b4 = new JButton("Personnages");

        sidebar.add(b1);
        sidebar.add(b2);
        sidebar.add(b3);
        sidebar.add(b4);

        //création d'une scrollbar pour la partie gauche
        JPanel menuChoix = new JPanel();
        scroll = new JScrollPane(menuChoix,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scroll.setPreferredSize(new Dimension(1/3*this.getWidth(),1/8*this.getHeight()));
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx=0;
        c1.gridy=2;
        c1.gridwidth=3;
        c1.gridheight=8;
        c1.fill=GridBagConstraints.BOTH;
        c1.anchor=GridBagConstraints.WEST;
        c1.insets= new Insets(0,0,0,0);
        c1.weightx=0.5f;
        c1.weighty=1.0f;

        //création des zone du menu des choix d'objets (partie gauche)
        menuChoix.setLayout(pageObjet);

        JPanel menuChoix1 = new JPanel();
        menuChoix1.setBackground(Color.RED);

        JPanel menuChoix2 = new JPanel();
        menuChoix2.setBackground(Color.YELLOW);

        JPanel menuChoix3 = new JPanel();
        menuChoix3.setBackground(Color.GREEN);

        JPanel menuChoix4 = new JPanel();
        menuChoix4.setBackground(Color.BLACK);

        menuChoix.add(menuChoix1);
        menuChoix.add(menuChoix2);
        menuChoix.add(menuChoix3);
        menuChoix.add(menuChoix4);

        pageObjet.addLayoutComponent(menuChoix1,"Salles");
        pageObjet.addLayoutComponent(menuChoix2,"Décors");
        pageObjet.addLayoutComponent(menuChoix3,"Murs");
        pageObjet.addLayoutComponent(menuChoix4,"Personnages");

        pages= new ChoixObjet(menuChoix,pageObjet);

        //création de la zone d'affichage de la map (partie droite)
        JPanel map = new JPanel();
        map.setBackground(Color.BLUE);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx=3;
        c2.gridy=2;
        c2.gridwidth=7;
        c2.gridheight=8;
        c2.fill=GridBagConstraints.BOTH;
        c2.anchor=GridBagConstraints.EAST;
        c2.insets= new Insets(0,0,0,0);
        c2.weightx=1.0f;
        c2.weighty=1.0f;

        //simulation grille map
        /*int heightMap,widthMap;
        JPanel c = new JPanel();
        c.setMinimumSize(new Dimension(32,32));
        c.setPreferredSize(new Dimension(32,32));
        c.setMaximumSize(new Dimension(32,32));
        int nbWidth = Math.round(((7/10)*this.getWidth() )/ 32);
        int nbHeight = Math.round(((8/10)*this.getHeight()) / 32);
        System.out.println(this.getWidth()+" et "+nbHeight);
        //map.setLayout(new GridLayout(nbHeight,nbWidth));
        for (int i= 0; i< nbHeight*nbWidth;i++){
            map.add(c);
        }*/

        b1.addActionListener(pages);
        b2.addActionListener(pages);
        b3.addActionListener(pages);
        b4.addActionListener(pages);

        panneau.add(sidebar,c3);
        panneau.add(map,c2);
        panneau.add(scroll,c1);
        this.add(panneau);

        //composante pour le DnD
        dragSource = new DragSource();
        DropTarget sourceDropTarget = new DropTarget(map, DnDConstants.ACTION_COPY, this);

        //Appel de la fonction de remplissage des entités de construction
        remplissage(menuChoix1);
        remplissage(menuChoix2);
        remplissage(menuChoix3);
        remplissage(menuChoix4);

        this.setVisible(true);
    }


    /**
     * méthode qui servira à remplir chaque pages avec les entités de construction
     *
     *
     * @param pane page qui sera remplit
     *
     * @since 2.0 05 décembre 2019
     */
    private void remplissage(JPanel pane){

        //affichage des entités
        pane.setLayout(new GridLayout(6,2,5,5));
        pane.setPreferredSize(new Dimension(1/3*this.getWidth(),1/8*this.getHeight()+1000));
        for (int i=0;i<12;i++){
            JPanel pan = new JPanel();
            JLabel lab = new JLabel();
            lab.setIcon(new ImageIcon("/export/home/an17/alais/IdeaProjects/Enigma/assets/players/$Lanto181.png"));
            pan.add(lab);
            pane.add(pan);
            //composante pour le DnD
            DragGestureRecognizer draggesturerecognizer = dragSource.createDefaultDragGestureRecognizer(lab, DnDConstants.ACTION_MOVE, this);
        }
    }

    // Transferable methods.
    /**
     * @param flavor
     * @return
     *
     * @since 2.0 05 décembre 2019
     */
    public Object getTransferData(DataFlavor flavor) {
        if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
            return object;
        } else {
            return null;
        }
    }

    /**
     * @return dataflavor
     *
     * @since 2.0 05 décembre 2019
     */
    public DataFlavor[] getTransferDataFlavors() {
        return dataflavor;
    }

    /**
     * @param flavor
     * @return
     *
     * @since 2.0 05 décembre 2019
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
    }

    // DragGestureListener method.
    /**
     * @param dge
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragGestureRecognized(DragGestureEvent dge) {
        dge.startDrag(null, this, this);
    }

    // DragSourceListener methods.
    /**
     * @param dsde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragDropEnd(DragSourceDropEvent dsde) {
        System.out.println("DragSourceDropEvent Fired");
    }

    /**
     * @param dsde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragEnter(DragSourceDragEvent dsde) {
        System.out.println("DragSourceDragEvent Fired");
    }

    /**
     * @param dse
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragExit(DragSourceEvent dse) {
        System.out.println("DragSourceEvent Fired");
    }

    /**
     * @param dsde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragOver(DragSourceDragEvent dsde) {
        object = dsde.getSource();
    }

    /**
     * @param dsde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dropActionChanged(DragSourceDragEvent dsde) {
        System.out.println("DragSourceDragEvent Fired");
    }

    // DropTargetListener methods.
    /**
     * @param dtde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    /**
     * @param dte
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragExit(DropTargetEvent dte) {
    }

    /**
     * @param dtde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dragOver(DropTargetDragEvent dtde) {
        dropTargetDrag(dtde);
    }

    /**
     * @param dtde
     *
     * @since 2.0 05 décembre 2019
     */
    public void dropActionChanged(DropTargetDragEvent dtde) {
        dropTargetDrag(dtde);
    }

    /**
     * @param dtde
     *
     * @since 2.0 05 décembre 2019
     */
    private void dropTargetDrag(DropTargetDragEvent dtde) {
        dtde.acceptDrag(dtde.getDropAction());
    }

    /**
     * @param dtde
     *
     * @since 2.0 05 décembre 2019
     */
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(dtde.getDropAction());
        try {
            Object source = dtde.getTransferable().getTransferData(dataflavor[0]);
            Object target = dtde.getSource();
            Component component = ((DragSourceContext) source).getComponent();
            Container oldContainer = component.getParent();
            Container newContainer = (Container) ((DropTarget) target).getComponent();
            newContainer.add(component);
            oldContainer.validate();
            oldContainer.repaint();
            newContainer.validate();
            newContainer.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dtde.dropComplete(true);
    }

}

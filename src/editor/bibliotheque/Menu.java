package editor.bibliotheque;

import editor.utils.LoadGameLibgdxApplication;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *  classe Menu
 *  @author Jorys-Micke ALAÏS
 *  @author Louka DOZ
 *  @author Loic SENECAT
 *  @author Quentin RAMSAMY-AGEORGES
 *
 *  @version 1.0
 */
public class Menu extends JFrame {

    private JScrollPane scroll;

    /**
     * @since 1.0
     */
    public Menu(){
        this.setTitle("Création de l'escape Game");
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
       // panneau.setLayout(new GridBagLayout());

        //création de la zone du menu des choix d'objets (partie gauche)
        JPanel menuChoix = new JPanel();
        menuChoix.setBackground(Color.RED);
        /*GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx=0;
        c1.gridy=2;
        c1.gridwidth=3;
        c1.gridheight=8;
        c1.fill=GridBagConstraints.BOTH;
        c1.anchor=GridBagConstraints.WEST;
        c1.insets= new Insets(0,0,0,0);
        c1.weightx=1.0f;
        c1.weighty=1.0f;*/
        //création d'une scrollbar pour la partie gauche
        scroll = new JScrollPane(menuChoix);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(300, 500));

        //création de la zone d'affichage de la map (partie droite)
	    JPanel map = new JPanel();
	    LoadGameLibgdxApplication.load(map, this);
       /* GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx=3;
        c2.gridy=2;
        c2.gridwidth=7;
        c2.gridheight=8;
        c2.fill=GridBagConstraints.BOTH;
        c2.anchor=GridBagConstraints.EAST;
        c2.insets= new Insets(0,0,0,0);
        c2.weightx=1.0f;
        c2.weighty=1.0f;*/

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

        GridBagConstraints c4 = new GridBagConstraints();
	    c4.gridx=0;
	    c4.gridy=2;
	    c4.gridwidth=10;
	    c4.gridheight=8;
	    c4.fill=GridBagConstraints.BOTH;
	    c4.anchor=GridBagConstraints.EAST;
	    c4.insets= new Insets(0,0,0,0);
	    c4.weightx=1.0f;
	    c4.weighty=1.0f;

        JButton b1 = new JButton("Personnages");
        JButton b2 = new JButton("Décors");
        JButton b3 = new JButton("Murs");
        JButton b4 = new JButton("...");

        sidebar.add(b1);
        sidebar.add(b2);
        sidebar.add(b3);
        sidebar.add(b4);

        JPanel p = new JPanel(new BorderLayout());
        p.add(scroll, BorderLayout.WEST);
        p.add(map, BorderLayout.CENTER);

        panneau.setLayout(new GridBagLayout());
        panneau.add(sidebar, c3);
        panneau.add(p, c4);
       // panneau.add(map,c2);
      //  panneau.add(scroll,c1);

        this.add(panneau);

        this.setVisible(true);
    }
}

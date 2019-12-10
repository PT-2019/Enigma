package editor.bibliotheque;

import editor.utils.LoadGameLibgdxApplication;
import editor.window.Window;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 *  classe Menu
 *  @author Jorys-Micke ALAÏS
 *  @author Louka DOZ
 *  @author Loic SENECAT
 *  @author Quentin RAMSAMY-AGEORGES
 *
 *  @version 2.0 05 décembre 2019
 */
public class MenuScreen extends JPanel {

	/**
	 * @since 1.0 10 novembre 2019
	 */
	public MenuScreen(Window parent){
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		//load entities
		//EntityFactory.loadEntities("assets/rooms.json");

		CardLayout pageObjet = new CardLayout();

		//création de la zone de choix du menu d'objets
		JPanel sideBar = this.loadSideBar();

		//création d'une scrollbar pour la partie gauche
		JPanel menuChoix = loadChoicesMenu(pageObjet);
		JScrollPane scroll = new JScrollPane(menuChoix, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);

		//création de la zone d'affichage de la map (partie droite)
		JPanel map = new JPanel();
		LoadGameLibgdxApplication.load(map, parent);

		//ajout des observateurs
		ChoixObjet choix = new ChoixObjet(menuChoix,pageObjet);
		for (Component c : sideBar.getComponents()) {
			if(c instanceof JButton){
				((JButton) c).addActionListener(choix);
			}
		}

		//ajout aux conteneurs
		JPanel content = new JPanel(new BorderLayout());
		content.add(scroll, BorderLayout.WEST);
		content.add(map, BorderLayout.CENTER);

		this.add(sideBar, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
	}

	private JPanel loadChoicesMenu(CardLayout layout) {
		//création des zone du menu des choix d'objets (partie gauche)
		JPanel menuChoix = new JPanel();
		menuChoix.setLayout(layout);

		JPanel menuChoix1 = new JPanel(), menuChoix2 = new JPanel();
		JPanel menuChoix3 = new JPanel(), menuChoix4 = new JPanel();

		menuChoix1.setBackground(Color.RED);
		menuChoix2.setBackground(Color.YELLOW);
		menuChoix3.setBackground(Color.GREEN);
		menuChoix4.setBackground(Color.BLACK);

		JPanel[] menus = new JPanel[]{menuChoix1, menuChoix2, menuChoix3, menuChoix4};
		MenuCategories[] names = MenuCategories.values();

		for (int i = 0; i < menus.length ; i++) {
			//Appel de la fonction de remplissage des entités de construction
			fill(menus[i]);
			menuChoix.add(menus[i]);
			//ajout au card Layout
			layout.addLayoutComponent(menus[i], names[i].name);
		}

		return menuChoix;
	}

	private JPanel loadSideBar() {
		//création de la zone de choix du menu d'objets
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridLayout(1,4,0,0));

		for (MenuCategories category : MenuCategories.values()) {
			sidebar.add(new JButton(category.name));
		}

		return sidebar;
	}

	/**
	 * méthode qui servira à remplir chaque pages avec les entités de construction
	 *
	 * @param pane page qui sera remplit
	 *
	 * @since 2.0 05 décembre 2019
	 */
	private void fill(JPanel pane){
		//affichage des entités
		pane.setLayout(new GridLayout(6,2,5,5));
		//pane.setPreferredSize(new Dimension(1/3*this.getWidth(),1/8*this.getHeight()+1000));
		for (int i=0;i<12;i++){
			JPanel pan = new JPanel();
			JLabel lab = new JLabel();
			lab.setIcon(new ImageIcon("assets/entities/players/$Lanto181.png"));
			pan.add(lab);
			pane.add(pan);
		}
	}
}

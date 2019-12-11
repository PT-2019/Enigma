package editor.bibliotheque;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import editor.entity.EntityFactory;
import editor.window.Window;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

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

	private DragAndDropDND dragAndDrop;

	/**
	 * @since 1.0 10 novembre 2019
	 */
	public MenuScreen(Window parent){
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		this.dragAndDrop =  new DragAndDropDND();

		//load entities
		EntityFactory.loadEntities("assets/rooms.json");

		CardLayout pageObjet = new CardLayout();

		//création de la zone de choix du menu d'objets
		JPanel sideBar = this.loadSideBar();

		//création d'une scrollbar pour la partie gauche
		JPanel menuChoix = loadChoicesMenu(pageObjet);
		JScrollPane scroll = new JScrollPane(menuChoix, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);

		//création de la zone d'affichage de la map (partie droite)
		JPanel map = new JPanel();
		//LoadGameLibgdxApplication.load(map, parent);
		map.setDropTarget(new DropTarget(map,	DnDConstants.ACTION_COPY, dragAndDrop));

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

		for (MenuCategories category : MenuCategories.values()) {
			JPanel menuCategory = new JPanel();
			//Appel de la fonction de remplissage des entités de construction
			fill(menuCategory, category);
			menuChoix.add(menuCategory);
			//ajout au card Layout
			layout.addLayoutComponent(menuCategory, category.name());
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
	 * @param name
	 * @since 2.0 05 décembre 2019
	 */
	private void fill(JPanel pane, MenuCategories name){
		Array<EntityFactory.EntitySerializable> entities = EntityFactory.getEntitiesByCategory(name);

		//affichage des entités
		pane.setLayout(new GridLayout(6,2,5,5));

		for (int i = 0; i < 12 || i < entities.size; i++) {@MagicConstant
			JPanel pan = new JPanel();
			JLabel lab = new JLabel();
			if(i < entities.size) {
				lab.setIcon(entities.get(i));
				new DragSource().createDefaultDragGestureRecognizer(lab, DnDConstants.ACTION_COPY, this.dragAndDrop);
			}
			pan.add(lab);
			pane.add(pan);
		}
	}
}

package editor.bibliotheque;

import com.badlogic.gdx.utils.Array;
import editor.datas.EntitiesCategories;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import editor.utils.LoadGameLibgdxApplication;
import editor.window.Window;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 * classe Menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0 05 décembre 2019
 */
public class MenuScreen extends JPanel {

	/**
	 * Crée l'écran de création de l'escape game
	 *
	 * @since 1.0 10 novembre 2019
	 */
	public MenuScreen(Window parent) {
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		//load entities
		EntityFactory.loadEntities("assets/rooms.json");
		EntityFactory.loadEntities("assets/items.json");
		EntityFactory.loadEntities("assets/decors.json");
		EntityFactory.loadEntities("assets/entities.json");

		//création de la zone de choix du menu d'objets
		JPanel sideBar = this.loadSideBar();

		//création de la zone d'affichage de la map (partie droite)
		JPanel map = new JPanel();
		LoadGameLibgdxApplication.load(map, parent);

		//ajout des observateurs
		ChoixObjet choix = new ChoixObjet();
		for (Component c : sideBar.getComponents()) {
			if (c instanceof JButton) {
				((JButton) c).addActionListener(choix);
			}
		}

		//ajout aux conteneurs
		this.add(sideBar, BorderLayout.NORTH);
		this.add(map, BorderLayout.CENTER);
	}

	/**
	 * Charge le menu des choix de catégories dans un JPanel
	 *
	 * @param layout layout du menu
	 * @return JPanel contenant le menu
	 * @since 3.0 14 décembre 2019
	 * @deprecated chargé via la libgdx
	 */
	@Deprecated//(since = "4.0")
	private JPanel loadChoicesMenu(CardLayout layout, DragAndDropDND dnd) {
		//création des zone du menu des choix d'objets (partie gauche)
		JPanel menuChoix = new JPanel();
		menuChoix.setLayout(layout);

		for (EntitiesCategories category : EntitiesCategories.values()) {
			JPanel menuCategory = new JPanel();
			//Appel de la fonction de remplissage des entités de construction
			fill(menuCategory, category, dnd);
			menuChoix.add(menuCategory);
			//ajout au card Layout
			layout.addLayoutComponent(menuCategory, category.name());
		}

		return menuChoix;
	}

	/**
	 * Charge la barre qui contient les éléments de la catégorie
	 *
	 * @return JPanel contenant la barre des éléments de la catégorie
	 * @since 3.0 14 décembre 2019
	 */
	private JPanel loadSideBar() {
		//création de la zone de choix du menu d'objets
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridLayout(1, 4, 0, 0));

		for (EntitiesCategories category : EntitiesCategories.values()) {
			sidebar.add(new JButton(category.name));
		}

		return sidebar;
	}

	/**
	 * méthode qui servira à remplir chaque pages avec les entités de construction
	 *
	 * @param pane page qui sera remplit
	 * @param name nom de la catégorie
	 * @since 2.0 05 décembre 2019
	 */
	@Deprecated//(since = "4.0")
	private void fill(JPanel pane, EntitiesCategories name, DragAndDropDND dnd) {
		Array<EntitySerializable> entities = EntityFactory.getEntitiesByCategory(name);

		//affichage des entités
		pane.setLayout(new GridLayout(6, 2, 5, 5));

		//on ajoute au minimum 12 cases, on met des entités dedans si on en a
		for (int i = 0; i < 12 || i < entities.size; i++) {
			@MagicConstant
			JPanel pan = new JPanel();
			EntityContainer lab = new EntityContainer();
			if (i < entities.size) {
				lab.setEntity(entities.get(i));
				//ajoute au dnd, @convenienceMethod
				lab.setDragSource(DnDConstants.ACTION_COPY, dnd);
			}
			pan.add(lab);
			pane.add(pan);
		}
	}
}

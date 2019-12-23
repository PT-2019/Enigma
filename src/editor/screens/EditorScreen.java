package editor.screens;

import api.enums.EntitiesCategories;
import api.enums.Outil;
import api.utils.LoadGameLibgdxApplication;
import com.badlogic.gdx.utils.Array;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import editor.utils.EmptyMapGenerator;
import editor.utils.dnd.ChoixObjet;
import editor.utils.dnd.DragAndDropDND;
import editor.utils.dnd.EntityContainer;
import editor.window.Window;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;

/**
 * Ecran de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 2.0 05 décembre 2019
 */
public class EditorScreen extends JPanel {

	/**
	 * Crée l'écran de création de l'escape game
	 *
	 * @param parent fenêtre swing
	 *
	 * @since 1.0 10 novembre 2019
	 */
	public EditorScreen(Window parent) {
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		//génère une map vide
		EmptyMapGenerator.generate("assets/map/EmptyTest.tmx", 50, 30);

		//charge entités
		EntityFactory.loadEntities("assets/rooms.json");
		EntityFactory.loadEntities("assets/items.json");
		EntityFactory.loadEntities("assets/decors.json");
		EntityFactory.loadEntities("assets/entities.json");

		//création de la barre d'outils
		JPanel outilBar = this.loadOutilBar();

		//création de la zone de choix du menu d'objets
		JPanel sideBar = this.loadSideBar();

		//création de la zone d'affichage de la map (partie droite)
		JPanel map = new JPanel();
		LoadGameLibgdxApplication.load(map, parent);

		//ajout des observateurs
		@Deprecated
		ChoixObjet choix = new ChoixObjet();
		for (Component c : sideBar.getComponents()) {
			if (c instanceof JButton) {
				((JButton) c).addActionListener(choix);
			}
		}

		JPanel menu = new JPanel(new BorderLayout());
		menu.add(outilBar, BorderLayout.NORTH);
		menu.add(sideBar, BorderLayout.SOUTH);

		//ajout aux conteneurs
		this.add(menu, BorderLayout.NORTH);
		this.add(map, BorderLayout.CENTER);

		parent.setJMenuBar(new BarMenu());
	}

	/**
	 * Charge le menu des choix de catégories dans un JPanel
	 *
	 * @param layout layout du menu
	 * @param dnd le gestionnaire du drag and drop
	 *
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
	 * Charge la barre d'outils
	 *
	 * @return JPanel contenant la barre d'outils
	 * @since 3.1 19 décembre 2019
	 */
	private JPanel loadOutilBar() {
		OutilAction listener = new OutilAction();

		//création de la zone de la barre d'outils
		JPanel outilBar = new JPanel();

		String[] elements = new String[]{"25%","50%","100%","125%","150%","175%","200%"};
		JComboBox<String> zoom = new JComboBox<>(elements);

		JCheckBox fit = new JCheckBox("fit");
		JCheckBox inGame = new JCheckBox("in game");

		for (Outil o : Outil.values()) {
			JButton a = new JButton(o.name);
			a.addActionListener(listener);
			outilBar.add(a);
		}

		outilBar.add(zoom);
		outilBar.add(fit);
		outilBar.add(inGame);

		return outilBar;
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
	 * @param dnd le gestionnaire du drag and drop
	 *
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

package editor;

import api.libgdx.utils.LoadGameLibgdxApplication;
import com.badlogic.gdx.utils.Array;
import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import common.utils.dnd.DragAndDropDND;
import common.utils.dnd.EntityContainer;
import data.EntitiesCategories;
import editor.bar.BarMenu;
import editor.bar.EnigmaOutilBar;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.dnd.DnDConstants;

/**
 * écran de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 2.0 05 décembre 2019
 */
public class EditorScreen extends JPanel {

	/**
	 * Libgdx map panel
	 * @since 6.0
	 */
	private final JPanel map;

	/**
	 * Barre d'outils
	 * @since 6.0
	 */
	private EnigmaPanel outilBar;

	/**
	 * Crée l'écran de création de l'escape game
	 *
	 * @param parent fenêtre swing
	 * @since 1.0 10 novembre 2019
	 */
	EditorScreen(EnigmaWindow parent) {
		this(parent, true);
	}

	/**
	 * Crée l'écran de création de l'escape game
	 *
	 * @param parent fenêtre swing
	 * @param bar    true s'il y a une barre de menus
	 * @since 4.2
	 */
	public EditorScreen(EnigmaWindow parent, boolean bar) {
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());

		//création de la barre d'outils
		EnigmaPanel outilBar = this.loadOutilBar(parent);

		//création de la zone d'affichage de la map (partie droite)
		this.map = new JPanel();
		LoadGameLibgdxApplication.load(this.map, parent);

		//ajout aux conteneurs
		this.add(outilBar, BorderLayout.NORTH);
		this.add(this.map, BorderLayout.CENTER);

		if (bar) parent.addToMenuBar(new BarMenu(parent));
	}

	/**
	 * Charge le menu des choix de catégories dans un JPanel
	 *
	 * @param layout layout du menu
	 * @param dnd    le gestionnaire du drag and drop
	 * @return JPanel contenant le menu
	 * @since 3.0 14 décembre 2019
	 * @deprecated chargé via la libgdx
	 */
	@Deprecated
	@SuppressWarnings("unused")
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
	 * @param window window
	 * @return JPanel contenant la barre d'outils
	 * @since 3.1 19 décembre 2019
	 */
	private EnigmaPanel loadOutilBar(EnigmaWindow window) {
		return this.outilBar = new EnigmaOutilBar(window);
	}

	/**
	 * Charge la barre qui contient les éléments de la catégorie
	 *
	 * @return JPanel contenant la barre des éléments de la catégorie
	 * @since 3.0 14 décembre 2019
	 * @deprecated 4.0 fait en libgdx
	 */
	@Deprecated
	@SuppressWarnings("unused")
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
	 * @param dnd  le gestionnaire du drag and drop
	 * @since 2.0 05 décembre 2019
	 */
	@Deprecated
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

	/**
	 * Activer la simulation ou la désactiver
	 * @param activate true pour activer
	 * @since 6.0
	 */
	void simulationMode(boolean activate) {
		if(activate){
			this.outilBar.setVisible(false);
		} else {
			this.outilBar.setVisible(true);
		}
	}

	/**
	 * Retourne libgdx map panel
	 * @return libgdx map panel
	 * @since 6.0
	 */
	JPanel getMap() {
		return this.map;
	}
}

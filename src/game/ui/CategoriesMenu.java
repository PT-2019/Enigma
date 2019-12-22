package game.ui;

import api.enums.EntitiesCategories;
import api.utils.InputListener;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import game.entity.EntityContainer;

/**
 * Le menu libgdx des entités par catégorie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 4.0 22/12/2019
 */
public class CategoriesMenu extends Window implements InputListener, Disposable {

	/**
	 * Largeur du menu
	 */
	public static final int WIDTH = 300;
	//TODO: a supprimer dès que le menu des catégories sera en libgdx, fix temporaire
	@SuppressWarnings("all")
	public static CategoriesMenu c;
	/**
	 * Stage du drag and drop
	 */
	private final Stage dnd;

	/**
	 * Conteneur de toutes les entités chargés
	 * pour une catégorie
	 */
	private Table container;

	/**
	 * Crée le menu libgdx des entités par catégorie
	 * avec comme catégorie par default {@link EntitiesCategories#ROOMS }
	 *
	 * @param dnd le stage du drag and drop
	 */
	public CategoriesMenu(Stage dnd) {
		//chargement du style de la fenêtre
		super("", Utility.loadSkin("assets/files/atlas/uiskin.json", "assets/files/atlas/uiskin.atlas"));

		this.setSize(WIDTH, Gdx.graphics.getHeight());

		//sauvegarde du stage
		this.dnd = dnd;

		//remplissage du menu
		this.container = new Table();
		this.add(this.container);
		loadCategory(EntitiesCategories.ROOMS);

		//tmp
		c = this;
	}

	/**
	 * Charge une catégorie
	 *
	 * @param c la catégorie à charger
	 * @see EntitiesCategories
	 */
	public void loadCategory(EntitiesCategories c) {
		container.clear();
		Array<EntitySerializable> entities = EntityFactory.getEntitiesByCategory(c);

		//on ajoute au minimum 12 cases, on met des entités dedans si on en a
		for (int i = 0; i < 12 || i < entities.size; i++) {
			if (i < entities.size)
				container.add(new EntityContainer(entities.get(i), dnd));

			if (i % 2 == 0) container.row();
		}
	}

	@Override
	public void dispose() {
	}
}

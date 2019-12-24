package game.ui;

import api.enums.EntitiesCategories;
import api.utils.CheckEventType;
import api.utils.InputListener;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
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
 * @version 4.2 24/12/2019
 * @since 4.0 22/12/2019
 */
public class CategoriesMenu extends Window implements InputListener, Disposable {

	/**
	 * Largeur du menu
	 */
	public static final int WIDTH = 300;

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
		super("Categories", Utility.loadSkin("assets/files/atlas/uiskin.json", "assets/files/atlas/uiskin.atlas"));
		this.getTitleLabel().setAlignment(Align.center);

		this.setSize(WIDTH, Gdx.graphics.getHeight());

		//sauvegarde du stage
		this.dnd = dnd;

		//container contenu catégorie
		this.container = new Table(this.getSkin());

		//ajout des catégories
		Table table = new Table(this.getSkin());
		table.add().padTop(10).row();
		EntitiesCategories[] categories = EntitiesCategories.values();
		for (int i = 0; i < categories.length; i++) {
			if (i % 3 == 0)
				table.row();
			TextButton category = new TextButton(categories[i].name, getSkin());
			TextTooltip tooltip = new TextTooltip(categories[i].name, getSkin());
			tooltip.setInstant(true);
			tooltip.setAlways(true);
			category.addListener(tooltip);
			category.addListener(new NextCategory(categories[i], this));
			table.add(category).space(10);
		}
		table.row();
		//contenu
		ScrollPane scrollPane = new ScrollPane(this.container, this.getSkin());
		scrollPane.setFadeScrollBars(false);
		scrollPane.setFlickScroll(false);
		table.add(scrollPane).expand().colspan(3).fill();

		loadCategory(EntitiesCategories.ITEMS);

		this.add(table).expand().fill();
	}

	/**
	 * Charge une catégorie
	 *
	 * @param c la catégorie à charger
	 * @see EntitiesCategories
	 */
	private void loadCategory(EntitiesCategories c) {
		this.container.clear();
		Array<EntitySerializable> entities = EntityFactory.getEntitiesByCategory(c);

		this.container.add().padTop(10).colspan(2).row();

		//on ajoute au minimum 12 cases, on met des entités dedans si on en a
		for (int i = 0; i < 12 || i < entities.size; i++) {
			if (i % 2 == 0) container.row();

			if (i < entities.size) {
				EntityContainer entity = new EntityContainer(entities.get(i), dnd);
				String hover = entities.get(i).getHover();
				if(hover != null && hover.length() > 0) {
					TextTooltip textTooltip = new TextTooltip(hover, getSkin());
					textTooltip.setAlways(true);
					textTooltip.setInstant(true);
					entity.addListener(textTooltip);
				}
				container.add(entity).minWidth(32).space(32);
			}
		}
	}

	/**
	 * Permet le passage d'une catégorie à une autre
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.1 23/12/2019
	 * @since 4.1 23/12/2019
	 */
	private static final class NextCategory implements EventListener {

		private final EntitiesCategories category;
		private final CategoriesMenu menu;

		/**
		 * Permet le passage d'une catégorie à une autre
		 *
		 * @param category la catégorie a charger
		 * @param menu     le menu des catégories
		 */
		NextCategory(EntitiesCategories category, CategoriesMenu menu) {
			this.category = category;
			this.menu = menu;
		}

		@Override
		public boolean handle(Event event) {
			if (CheckEventType.isMouseCliqued(event)) {
				this.menu.loadCategory(this.category);
			}
			return false;
		}
	}

	@Override
	public void dispose() {
	}
}

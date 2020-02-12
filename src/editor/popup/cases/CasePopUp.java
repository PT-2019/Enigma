package editor.popup.cases;

import api.utils.Utility;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import common.entities.types.Living;
import common.hud.EnigmaPanel;
import common.map.MapTestScreenCell;
import data.Layer;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.menus.OptionRunnableFactory;
import editor.popup.cases.listeners.EntityChoseListener;
import editor.popup.cases.panel.NavigationPanel;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.EnumMap;

/**
 * Fenêtre qui est afficher lorsqu'on clique sur une case
 */
public class CasePopUp extends AbstractPopUp implements Drawable {

	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 */
	private static final int WIDTH = 500, HEIGHT = 200, COL = 1, ROW = 2;
	/**
	 * Tiled Map
	 */
	private final TiledMap tileMap;
	/**
	 * Composant swing
	 */
	private final JComponent component;
	/**
	 * Observer d'entités
	 */
	private final EntityChoseListener observer;
	/**
	 * Panneau qui gère la navigation entre les différentes entités
	 * et les différents niveaux
	 */
	private NavigationPanel navigation;
	/**
	 * Panneau des informations de l'entitée
	 */
	private EnigmaPanel extra = new EnigmaPanel();
	/**
	 * Contient un Runnable pour chaque option disponible
	 */
	private EnumMap<AvailablePopUpOption, AvailableOptionRunnable> runnables;

	public CasePopUp(JComponent component, TiledMap tiledMap) {
		super((JFrame) component.getRootPane().getParent(), "", false);
		this.component = component;
		this.observer = new EntityChoseListener();
		this.tileMap = tiledMap;
		this.runnables = new EnumMap<>(AvailablePopUpOption.class);

		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH / 2, bounds.height / 2 - HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new GridLayout(ROW, COL));
		this.initComponent();
	}

	@Override
	public void initComponent() {
		this.navigation = new NavigationPanel(this);
		this.extra = new EnigmaPanel();
		for (Class<?> c : OptionRunnableFactory.getAll()) {
			AvailableOptionRunnable runnable = (AvailableOptionRunnable) Utility.instance(c, this);
			runnables.put(runnable.getOption(), runnable);
		}
		this.navigation.setLayout(new GridLayout(1, 3));
		this.extra.setLayout(new FlowLayout());
	}

	/**
	 * Méthode appelée à l'affichage du popup
	 */
	public void display() {
		TiledMapTileLayer currentLayer = cell.getLayer();
		this.setTitle(Layer.valueOf(currentLayer.getName()).name);

		//rempli avec l'entité
		GameObject entity = this.cell.getEntity();
		if (entity == null) {
			this.navigation.setText(NeedToBeTranslated.NO_ENTITY);
		} else {
			if(entity instanceof Living){
				String name = ((Living) entity).getName();
				if(name == null || name.isBlank() || name.isEmpty()){
					this.navigation.setText(entity.getReadableName());
				} else {
					this.navigation.setText(name);
				}
			} else {
				this.navigation.setText(entity.getReadableName());
			}
			fillPanel();
		}

		int index = this.tileMap.getLayers().getIndex(currentLayer.getName());
		this.navigation.displayNavBouton(index, this.tileMap.getLayers());
		this.add(navigation);
		this.add(extra);
		this.setVisible(true);
	}

	/**
	 * Rempli les options liées a l'entité sur la case
	 */
	public void fillPanel() {
		if (this.cell != null && this.cell.getEntity() != null) {
			for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
				if (AvailablePopUpOption.isAvailable(option, this.cell.getEntity())) {
					AvailableOptionRunnable runnable = runnables.get(option);
					if (runnable != null) {
						runnable.run();
					}
				}
			}
		}
	}

	/**
	 * On élimine tout les composants de la fenetre puis on en créer de nouveau
	 */
	@Override
	public void clean() {
		this.extra.removeAll();
		this.remove(navigation);
		this.remove(extra);
		initComponent();
	}

	@Override
	public MapTestScreenCell getCell() {
		return super.getCell();
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	public TiledMap getTileMap() {
		return tileMap;
	}

	public JComponent getComponent() {
		return this.component;
	}

	public EntityChoseListener getObserver() {
		return observer;
	}

	public EnigmaPanel getPanel() {
		return this.extra;
	}

	public NavigationPanel getNavigation() {
		return navigation;
	}

	@Override
	public void invalidateDrawable() {
		GameObject entity = this.cell.getEntity();
		if(entity instanceof Living){
			Living living = (Living) entity;
			String name = living.getName();
			if(name != null && !name.isBlank() && !name.isEmpty()){
				this.navigation.setText(name);
			} else {
				//TODO: fix avec getKey de Loïc
				this.navigation.setText(entity.getReadableName());
			}
		}
	}

	@Override
	public Container getDrawable() {
		return null;
	}
}

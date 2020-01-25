package editor.view.cases;

import api.enums.AvailablePopUpOption;
import api.enums.Layer;
import api.utils.Utility;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.hud.EnigmaPanel;
import editor.view.cases.listeners.EntityChoseListener;
import editor.view.cases.panel.NavigationPanel;
import editor.view.listeners.AvailableOptionRunnable;
import game.entity.map.MapTestScreenCell;
import starter.EditorLauncher;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.EnumMap;

/**
 * Fenetre qui est afficher lorsqu'on clique sur une case
 */
public class CasePopUp extends AbstractPopUp {

	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 */
	private static final int WIDTH = 500, HEIGHT = 200, COL = 1, ROW = 2;

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

	/**
	 * Tiled Map
	 */
	private final TiledMap tileMap;

	/**
	 * Composant swing
	 */
	private final JComponent component;

	/**
	 * ???
	 */
	private final EntityChoseListener observer;

	public CasePopUp(JComponent component, TiledMap tiledMap) {
		super((JFrame) component.getRootPane().getParent(), "", false);
		this.component = component;
		this.observer = new EntityChoseListener();
		this.tileMap = tiledMap;
		this.runnables = new EnumMap<>(AvailablePopUpOption.class);

		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH/2, bounds.height / 2 - HEIGHT/2);
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
		for (Class c : AvailableOptionRunnable.classes) {
			AvailableOptionRunnable runnable = (AvailableOptionRunnable) Utility.instance(c, this);
			runnables.put(runnable.getOption(), runnable);
		}
		this.navigation.setLayout(new GridLayout(1, 3));
		this.extra.setLayout(new GridLayout(2, 3));
	}

	/**
	 * Méthode appelée à l'affichage du popup
	 */
	public void display() {
		TiledMapTileLayer currentLayer = cell.getLayer();
		this.setTitle(Layer.valueOf(currentLayer.getName()).name);

		if (this.cell.getEntity() == null) {
			this.navigation.setText("Aucune entité");
		} else {
			this.navigation.setText(this.cell.getEntity().getReadableName());
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
	public void fillPanel(){
		if(this.cell != null && this.cell.getEntity() != null) {
			for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
				if (AvailablePopUpOption.isAvailable(option, this.cell.getEntity())) {
					Runnable runnable = runnables.get(option);
					if (runnable != null) runnable.run();
				}
			}
		}
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
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
}

package editor.popup.cases;

import api.utils.Utility;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import common.entities.types.Living;
import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import common.map.MapTestScreenCell;
import data.Layer;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.popup.cases.listeners.SpecialPopListener;
import editor.popup.cases.panel.NavigationPanel;
import editor.popup.listeners.CaseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Rectangle;

/**
 * PopUp qui permet de se déplacer uniquement entre les entités
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0
 */
public class SpecialPopUp extends AbstractPopUp {

	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 */
	private static final int WIDTH = 400, HEIGHT = 130, COL = 1, ROW = 2;

	/**
	 * CaseListener
	 */
	private final CaseListener caseListener;

	/**
	 * Panneau de navigation pour passer d'une entité à une autre
	 */
	private NavigationPanel navigation;

	/**
	 * PopUp qui contient la référence à l'objet choisi
	 */
	private CasePopUp popUp;

	/**
	 * Panneau du contenu (entité, utiliser)
	 */
	private EnigmaPanel panel;

	public SpecialPopUp(JComponent component, TiledMap tiledMap, CasePopUp popUp, CaseListener caseListener) {
		super((JFrame) component.getRootPane().getParent(), "", false);
		this.caseListener = caseListener;
		this.tileMap = tiledMap;
		this.popUp = popUp;

		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH / 2, bounds.height / 2 - HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(ROW, COL));
	}

	@Override
	public void display() {
		this.initComponent();
		//titre
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
		}

		//affichage du layer
		int index = this.tileMap.getLayers().getIndex(currentLayer.getName());
		this.navigation.displayNavBouton(index, this.tileMap.getLayers());

		//add
		this.add(navigation);
		this.add(panel);
		this.revalidate();
	}

	@Override
	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	@Override
	public void initComponent() {
		final int ROW = 1, COL = 3;
		this.navigation = new NavigationPanel(this);
		this.navigation.setLayout(new GridLayout(ROW, COL));
		EnigmaButton button = new EnigmaButton(NeedToBeTranslated.SELECT_ENTITY);
		button.addActionListener(new SpecialPopListener(this, this.popUp, this.cell.getEntity()));
		this.panel = new EnigmaPanel();
		this.panel.add(button);
	}

	@Override
	public void clean() {
		this.remove(this.navigation);
		this.panel.removeAll();
		this.remove(this.panel);
	}

	public CaseListener getCaseListener() {
		return this.caseListener;
	}
}

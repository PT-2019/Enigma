package editor.popup.cases;

import api.utils.Utility;
import api.utils.WindowClosing;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import common.entities.types.Container;
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

import javax.swing.JFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	 * Textes
	 */
	private static final String SELECT_ENTITY = NeedToBeTranslated.SELECT_ENTITY;
	private static final String CONTENT = NeedToBeTranslated.CONTENT;

	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 */
	private static final int WIDTH = 500, HEIGHT = 130, COL = 1, ROW = 2;

	/**
	 * utiliser l'item
	 */
	private final boolean use;
	/**
	 * voir contenu pour utiliser un item dedans
	 */
	private final boolean content;

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

	public SpecialPopUp(CasePopUp popUp, boolean use, boolean content, CaseListener caseListener) {
		super((JFrame) popUp.getComponent().getRootPane().getParent(), "", false);
		this.use = use;
		this.content = content;
		this.caseListener = caseListener;
		this.tileMap = popUp.getTileMap();
		this.popUp = popUp;

		//settings
		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH / 2, bounds.height / 2 - HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(ROW, COL));

		if((use != content)){
			this.setSize(WIDTH-100, HEIGHT);
		}
	}

	@Override
	public void display() {
		this.initComponent();
		//titre
		TiledMapTileLayer currentLayer = this.cell.getLayer();
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
		this.add(this.navigation);
		this.add(this.panel);
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
		this.panel = new EnigmaPanel(new GridBagLayout());
		boolean contentOk = this.cell.getEntity() instanceof Container;
		//si les deux
		if(contentOk && this.use == this.content){
			GridBagConstraints gbc = new GridBagConstraints();
			EnigmaButton use = new EnigmaButton(SELECT_ENTITY);
			EnigmaButton content = new EnigmaButton(CONTENT);
			content.addActionListener(new OpenContentChooseView(this.popUp, this.cell.getEntity()));
			use.addActionListener(new SpecialPopListener(this, this.popUp, this.cell.getEntity()));
			this.panel.add(use, gbc);
			gbc.insets = new Insets(0,10,0,0);
			this.panel.add(content, gbc);
		//si use seulement
		} else if(use){
			EnigmaButton use = new EnigmaButton(SELECT_ENTITY);
			use.addActionListener(new SpecialPopListener(this, this.popUp, this.cell.getEntity()));
			this.panel.add(use);
		//si content seulement
		}else if (contentOk){
			EnigmaButton content = new EnigmaButton(CONTENT);
			content.addActionListener(new OpenContentChooseView(this.popUp, this.cell.getEntity()));
			this.panel.add(content);
		}
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

	/**
	 * Ouvre un popup avec une liste d'items.
	 * Un peu être sélectionné
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 13/02/2020
	 * @since 6.0 13/02/2020
	 */
	private static final class OpenContentChooseView implements ActionListener {

		private final CasePopUp popUp;
		private final GameObject entity;

		OpenContentChooseView(CasePopUp popUp, GameObject entity) {
			this.popUp = popUp;
			this.entity = entity;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ChooseItemView chooseItemView = new ChooseItemView(this.popUp, this.entity);
			chooseItemView.addWindowListener((WindowClosing) e1 -> e1.getWindow().dispose());
			chooseItemView.setVisible(true);
		}
	}
}

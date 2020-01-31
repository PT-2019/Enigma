package editor.menus;

import api.hud.base.ResetComponent;

import javax.swing.JDialog;

/**
 * PopUp qui permet de se déplacer uniquement entre les entités
 */
public abstract class AbstractPopUpSmallView extends JDialog implements ResetComponent {

/*	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 *
	private static final int WIDTH = 400, HEIGHT = 130;

	public AbstractPopUpSmallView(String title, AbstractPopUpView parent) {
		super(EditorLauncher.getInstance().getWindow(), title, false);
		this.caseListener = caseListener;
		this.tileMap = tiledMap;
		this.popUp = popUp;

		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH/2, bounds.height / 2 - HEIGHT/2);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(ROW, COL));
	}

	public void display() {
		this.initComponent();
		TiledMapTileLayer currentLayer = cell.getLayer();
		this.setTitle(Layer.valueOf(currentLayer.getName()).name);

		if (this.cell.getEntity() == null) {
			this.navigation.setText("Aucune entité");
		} else {
			this.navigation.setText(this.cell.getEntity().getReadableName());
			//fillPanel();
		}
		int index = this.tileMap.getLayers().getIndex(currentLayer.getName());
		this.navigation.displayNavBouton(index, this.tileMap.getLayers());
		this.add(navigation);
		this.add(panel);
		this.revalidate();
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	@Override
	public void initComponent() {
		this.navigation = new NavigationPanel(this);
		this.navigation.setLayout(new GridLayout(1, 3));
		button = new EnigmaButton("Utiliser cette entité");
		button.addActionListener(new SpecialPopListener(this, popUp, cell.getEntity()));
		panel = new EnigmaPanel();
		panel.add(button);
	}

	@Override
	public void clean() {
		this.remove(navigation);
		panel.removeAll();
		this.remove(panel);
	}

	public CaseListener getCaseListener() {
		return caseListener;
	}*/
}

package editor.entity.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * TODO: comment CasePopUp and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CasePopUp extends JDialog implements WindowListener {

	JLabel label = new JLabel();
	JButton next = new JButton();
	JButton preview = new JButton();
	JLabel eng = new JLabel("Gérer les énigmes");
	JButton b3 = new JButton("Supprimer");
	private TiledMapTileLayer.Cell cell;
	private TiledMap tileMap;

	public CasePopUp(JComponent component, TiledMap tiledMap) {
		super((JFrame) component.getRootPane().getParent(), "Case Information", false);
		this.setSize(300, 300);
		this.setLocation(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.tileMap = tiledMap;
	}

	public void display() {
		TiledMapTileLayer l = ((MapLibgdxCell) cell).getLayer();
		MapLayers layers = tileMap.getLayers();
		TiledMapTileLayer layer;
		MapLibgdxCell cellTmp;
		int index = layers.getIndex(l.getName());
		int x, y;

		this.setLayout(new GridLayout(2, 3));
		if (index < 3) {
			next.setText(layers.get(index + 1).getName());
			this.add(next);

			layer = (TiledMapTileLayer) layers.get(index + 1);

			cellTmp = (MapLibgdxCell) cell;
			y = cellTmp.getIndex() / layer.getWidth();
			x = cellTmp.getIndex() % layer.getWidth();

			System.out.println(x);
			System.out.println(y);

			System.out.println(layer.getCell(x, y));

			next.addActionListener(new CasePopListener(layer.getCell(x, y), this));
		}

		label.setText(l.getName());
		this.add(label);

		if (index > 0) {
			preview.setText(layers.get(index - 1).getName());
			this.add(preview);

			layer = (TiledMapTileLayer) layers.get(index + 1);

			cellTmp = (MapLibgdxCell) cell;
			y = cellTmp.getIndex() / layer.getWidth();
			x = cellTmp.getIndex() % layer.getWidth();

			preview.addActionListener(new CasePopListener(layer.getCell(x, y), this));
		}
		this.add(eng);
		this.add(b3);
		this.setVisible(true);
	}


	public void setCell(TiledMapTileLayer.Cell cell) {
		this.cell = cell;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.remove(label);
		this.remove(b3);
		this.remove(next);
		this.remove(eng);
		this.remove(preview);
		this.setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}

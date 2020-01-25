package editor.view.cases.panel;

import api.enums.Layer;
import api.hud.ResetComponent;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.cases.AbstractPopUp;
import editor.view.cases.listeners.CasePopListener;

import java.awt.GridBagLayout;

/**
 * Panneau qui permet de naviguer à travers les différentes entités et cellule d'une case
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0
 * @since 4.0
 */
public class NavigationPanel extends EnigmaPanel implements ResetComponent {
	private EnigmaButton next;
	private EnigmaButton preview;
	private EnigmaLabel info;

	/**
	 * Panneaux pour le layout
	 */
	private EnigmaPanel prevP, nextP, normP;

	private AbstractPopUp popUp;

	public NavigationPanel(AbstractPopUp popUp) {
		this.popUp = popUp;
		this.initComponent();
	}

	/**
	 * Méthode qui dispose les différents bouttons de naviguation
	 */
	public void displayNavBouton(int index, MapLayers layers) {
		int x, y;
		TiledMapTileLayer layer;

		if (index < 3) {
			next.setText(Layer.valueOf(layers.get(index + 1).getName()).name);
			nextP.add(next);

			layer = (TiledMapTileLayer) layers.get(index + 1);

			y = popUp.getCell().getIndex() / layer.getWidth();
			x = popUp.getCell().getIndex() % layer.getWidth();
			next.addActionListener(new CasePopListener(layer.getCell(x, y), popUp));
		}else {
			nextP.add(new EnigmaLabel());
		}
		normP.add(info);
		if (index > 0) {
			preview.setText(Layer.valueOf(layers.get(index - 1).getName()).name);
			prevP.add(preview);

			layer = (TiledMapTileLayer) layers.get(index - 1);

			y = popUp.getCell().getIndex() / layer.getWidth();
			x = popUp.getCell().getIndex() % layer.getWidth();
			preview.addActionListener(new CasePopListener(layer.getCell(x, y), popUp));
		} else {
			prevP.add(new EnigmaLabel());
		}
	}

	@Override
	public void clean() {
		this.removeAll();
		prevP.removeAll();
		normP.removeAll();
		info.removeAll();
		//init
		this.initComponent();
	}

	@Override
	public void initComponent(){
		this.info = new EnigmaLabel();
		this.next = new EnigmaButton();
		this.preview = new EnigmaButton();
		this.prevP = new EnigmaPanel(new GridBagLayout());
		this.normP = new EnigmaPanel(new GridBagLayout());
		this.nextP = new EnigmaPanel(new GridBagLayout());

		this.info.setHorizontalAlignment(EnigmaLabel.CENTER);

		this.add(this.prevP);
		this.add(this.normP);
		this.add(this.nextP);
	}

	/**
	 * Définit le nom de layer
	 * @param text le nom de layer
	 */
	public void setText(String text) {
		this.info.setText(text);
	}

	/**
	 * Retourne le label contenant le nom du layer
	 * @return le label contenant le nom du layer
	 */
	public EnigmaLabel getInfo() {
		return this.info;
	}

	/**
	 * ???
	 * @param name ???
	 */
	public void setClassText(String name) {
		this.setText(name);
	}
}

package editor.menus.others;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.listeners.CaseDelete;

/**
 * Suppression d'une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class Delete implements AvailableOptionRunnable {

	private final CasePopUp parent;
	private EnigmaButton del;

	public Delete(CasePopUp parent) {
		this.parent = parent;
		this.del = new EnigmaButton("Supprimer");
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.DELETE;
	}

	@Override
	public void run() {
		//ajoute le bouton
		this.parent.getPanel().add(this.del);
		this.del.addActionListener(new CaseDelete(this.parent.getCell(),
				//récupère le niveau de l'entité a supprimer
				(TiledMapTileLayer) this.parent.getTileMap().getLayers().get(this.parent.getCell().getLayer().getName()),
				this.parent.getNavigation().getInfo(), this.parent)
		);
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {
		//ajoute le bouton
		panel.add(this.del);
		/*this.del.addActionListener(new CaseDelete(this.parent.getCell(),
				//récupère le niveau de l'entité a supprimer
				(TiledMapTileLayer)this.parent.getTileMap().getLayers().get(this.parent.getCell().getLayer().getName()),
				this.parent.getNavigation().getInfo(), this.parent)
		);*/
	}
}

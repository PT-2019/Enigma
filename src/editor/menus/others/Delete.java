package editor.menus.others;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.types.Container;
import common.hud.EnigmaButton;
import common.utils.Logger;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.EditorAction;
import editor.bar.edition.actions.EditorActionFactory;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.listeners.CaseDelete;
import game.EnigmaGame;
import game.screens.TestScreen;

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
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		//ajoute le bouton
		panel.getDrawable().add(this.del);

		this.del.addActionListener((e) -> {
			//récupère l'entité
			GameObject entity = view.getPopUp().getCell().getEntity();

			//supprimer si container
			if (object instanceof Item && entity instanceof Container) {
				//remove from map
				String s = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap().removeEntity(object);
				if (s != null) {
					//erreur
					Logger.printDebug("Delete#run", "Impossible de supprimer");
					EnigmaGame.getCurrentScreen().showToast(s);
					return;
				}
				//remove from parent
				((Container) entity).removeItem((Item) object);

				//ajout à l'historique
				EditorAction action = EditorActionFactory.actionWithinAMenu(ActionTypes.REMOVE_SUB_ENTITY, entity, object);
				ActionsManager.getInstance().add(action);

				//update
				panel.invalidateDrawable();
			} else {
				//supprime l'entitée de la case
				new CaseDelete(this.parent.getCell(),
						//récupère le niveau de l'entité a supprimer
						(TiledMapTileLayer) this.parent.getTileMap().getLayers().get(this.parent.getCell().getLayer().getName()),
						this.parent.getNavigation().getInfo(), this.parent).actionPerformed(e);
			}
		});
	}
}
